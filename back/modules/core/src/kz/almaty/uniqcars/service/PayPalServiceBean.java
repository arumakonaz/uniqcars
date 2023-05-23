package kz.almaty.uniqcars.service;

import com.google.gson.GsonBuilder;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.UserSessionSource;
import kz.almaty.uniqcars.config.PaypalConfig;
import kz.almaty.uniqcars.entity.Car;
import kz.almaty.uniqcars.entity.Order;
import kz.almaty.uniqcars.entity.OrderStatus;
import kz.almaty.uniqcars.entity.UserExt;
import kz.almaty.uniqcars.entity.pojo.*;
import okhttp3.*;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service(PayPalService.NAME)
public class PayPalServiceBean implements PayPalService {

    @Inject
    protected PaypalConfig paypalConfig;
    @Inject
    protected DataManager dataManager;
    @Inject
    protected UserSessionSource userSessionSource;

    public String createOrder(Car car, Boolean useBonus) throws Exception {

        if (useBonus) {
            UserExt userExt = dataManager.load(UserExt.class)
                    .id(userSessionSource.getUserSession().getUser().getId())
                    .view("userExt-EditView")
                    .optional().orElse(null);
            BigDecimal userBonus = userExt.getBonus();

            if (userBonus != null) {
                int bonusAndPriceCompare = userBonus.compareTo(car.getPrice());
                if (bonusAndPriceCompare >= 0) {
                    userExt.setBonus(userBonus.subtract(car.getPrice()));
                    dataManager.commit(userExt);
                    return "https://uniqcars-front.herokuapp.com/approved";
                }
            }
        }

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, getOrderBody(car));
        String requestId = UUID.randomUUID().toString();
        Request request = new Request.Builder()
                .url(paypalConfig.getPaypalUrl() + "/v2/checkout/orders")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Prefer", "return=representation")
                .addHeader("PayPal-Request-Id", requestId)
                .addHeader("Authorization", "Bearer " + generateToken())
                .addHeader("Cookie", "LANG=en_US%3BKZ; cookie_check=yes; d_id=2a0d3cfda9d34d97950cfe0fc74865c61683531558407; enforce_policy=global; ts=vreXpYrS%3D1778225957%26vteXpYrS%3D1683533357%26vr%3Dfa4cec0f1870a6022c77bcf6f9fb6099%26vt%3Dfa4cec0f1870a6022c77bcf6f9fb6098%26vtyp%3Dnew; ts_c=vr%3Dfa4cec0f1870a6022c77bcf6f9fb6099%26vt%3Dfa4cec0f1870a6022c77bcf6f9fb6098; tsrce=unifiedloginnodeweb; x-cdn=fastly:LIN; x-pp-s=eyJ0IjoiMTY4MzUzMTU1ODQzNSIsImwiOiIwIiwibSI6IjAifQ")
                .build();
        Response response = client.newCall(request).execute();
        if (response.code() >= 200 && response.code() <= 299) {
            return getUrlFromResponse(car, response.body().string(), useBonus);

        } else {
            throw new Exception(response.body().string());
        }
    }

    private void saveOrderInDb(Car car, String selfLink, String approveLink, Boolean useBonus) {
        Order order = dataManager.create(Order.class);
        order.setCar(car);
        order.setStatus(OrderStatus.NEW);
        order.setSelfLink(selfLink);
        order.setApproveLink(approveLink);
        order.setUserExt((UserExt) userSessionSource.getUserSession().getUser());

        UserExt userExt = dataManager.load(UserExt.class)
                .id(order.getUserExt().getId())
                .view("userExt-EditView")
                .optional().orElse(null);

        CommitContext commitContext = new CommitContext();


        if (useBonus) {
            order.setBonus(userExt.getBonus());
            userExt.setBonus(null);
            commitContext.addInstanceToCommit(userExt);
        }

        commitContext.addInstanceToCommit(order);

        dataManager.commit(commitContext);
    }

    private String getUrlFromResponse(Car car, String body, Boolean useBonus) throws Exception {
        GsonBuilder gsonBuilder = new GsonBuilder();

        PaypalResponse response = gsonBuilder
                .create()
                .fromJson(body, PaypalResponse.class);

        PaypalLinks approveLink = response.getLinks()
                .stream()
                .filter(e -> e.getRel().equals("approve"))
                .findFirst()
                .orElse(null);

        PaypalLinks selfLink = response.getLinks()
                .stream()
                .filter(e -> e.getRel().equals("self"))
                .findFirst()
                .orElse(null);

        saveOrderInDb(car, selfLink.getHref(), approveLink.getHref(), useBonus);

        if (approveLink == null)
            throw new Exception("approve");

        return approveLink.getHref();
    }

    private String getOrderBody(Car car) {
        if (car == null)
            throw new NullPointerException("Car is null in getOrderBody");


        ApplicationContext applicationContext = new ApplicationContext(
                "https://uniqcars-front.herokuapp.com/approved",
                "https://uniqcars-front.herokuapp.com/canceled");
        List<PurchaseItem> purchaseItems = new ArrayList<>();
        PurchaseItem purchaseItem = new PurchaseItem(car.getName());

        List<PurchaseUnit> purchaseUnits = new ArrayList<>();
        PurchaseUnit purchaseUnit = new PurchaseUnit(purchaseItems);
        purchaseUnits.add(purchaseUnit);

        purchaseItems.add(purchaseItem);
        Purchase purchase = new Purchase(purchaseUnits, applicationContext);

        GsonBuilder gsonBuilder = new GsonBuilder();
        String purchaseJson = gsonBuilder.create()
                .toJson(purchase);
        return purchaseJson;

    }

    public String generateToken() throws Exception {

        Validate.notEmpty(paypalConfig.getPaypalUsername(), "paypal username empty");
        Validate.notEmpty(paypalConfig.getPaypalPassword(), "paypal password empty");
        Validate.notEmpty(paypalConfig.getPaypalUrl(), "paypal url empty");


        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "grant_type=client_credentials&ignoreCache=true&return_authn_schemes=true&return_client_metadata=true&return_unconsented_scopes=true");
        Request request = new Request.Builder()
                .url(paypalConfig.getPaypalUrl() + "/v1/oauth2/token")
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Authorization", "Basic " + getEncodedCredentials())
                .build();
        Response response = client.newCall(request).execute();

        if (response.code() == 401) {
            throw new Exception("Wrong paypal username/password");
        }
        if (response.code() != 200) {
            throw new Exception(response.body().string());
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        ResponseToken responseToken = gsonBuilder.create().fromJson(response.body().string(), ResponseToken.class);
        return responseToken.getAccess_token();
    }

    protected String getEncodedCredentials() {

        String username = paypalConfig.getPaypalUsername();
        String password = paypalConfig.getPaypalPassword();

        String credentials = username + ":" + password;
        String encoded = Base64.getEncoder().encodeToString(credentials.getBytes());

        return encoded;
    }

    @Override
    public void checkOrder(String approveLink) throws Exception {

        UserExt user = dataManager.load(UserExt.class)
                .id(userSessionSource.getUserSession().getUser().getId())
                .view("userExt-EditView")
                .optional().orElse(null);

        Order order = dataManager.load(Order.class)
                .query("select e from uniqcars_Order e where e.userExt.id =:userId " +
                        " and e.status = 'NEW' " +
                        " and e.approveLink =:approveLink")
                .parameter("approveLink", approveLink)
                .parameter("userId", user.getId())
                .view("order-carView")
                .optional().orElse(null);

        if (order == null)
            return;


        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(order.getSelfLink())
                .method("GET", null)
                .addHeader("Authorization", "Bearer " + generateToken())
                .addHeader("Cookie", "cookie_check=yes; d_id=2a0d3cfda9d34d97950cfe0fc74865c61683531558407; enforce_policy=global; ts=vreXpYrS%3D1778225957%26vteXpYrS%3D1683533357%26vr%3Dfa4cec0f1870a6022c77bcf6f9fb6099%26vt%3Dfa4cec0f1870a6022c77bcf6f9fb6098%26vtyp%3Dnew; ts_c=vr%3Dfa4cec0f1870a6022c77bcf6f9fb6099%26vt%3Dfa4cec0f1870a6022c77bcf6f9fb6098; tsrce=unifiedloginnodeweb; x-cdn=fastly:LIN; x-pp-s=eyJ0IjoiMTY4MzUzMTU1ODQzNSIsImwiOiIwIiwibSI6IjAifQ")
                .build();

        Response response = client.newCall(request).execute();

        if (response.code() >= 200 && response.code() <= 299) {


            GsonBuilder gsonBuilder = new GsonBuilder();

            PaypalResponse paypalResponse = gsonBuilder
                    .create()
                    .fromJson(response.body().string(), PaypalResponse.class);
            String status = paypalResponse.getStatus();

            if (status.equals("APPROVED")) {
                if (order.getBonus() == null) {
                    BigDecimal bonus = user.getBonus() == null ? new BigDecimal(0) : user.getBonus();
                    bonus = bonus.add(order.getCar().getPrice().multiply(new BigDecimal("0.1")));
                    user.setBonus(bonus);
                    dataManager.commit(user);
                }
                order.setStatus(OrderStatus.PAID);
                dataManager.commit(order);
            }

        } else {
            throw new Exception(response.body().string());
        }
    }

}