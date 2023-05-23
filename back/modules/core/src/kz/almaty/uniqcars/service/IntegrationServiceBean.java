package kz.almaty.uniqcars.service;

import com.haulmont.cuba.core.app.EmailerConfig;
import com.haulmont.cuba.core.global.DataManager;
import com.sun.org.apache.xpath.internal.operations.Bool;
import kz.almaty.uniqcars.entity.Car;
import kz.almaty.uniqcars.entity.pojo.*;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jsoup.helper.Validate;
import org.slf4j.Logger;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.UUID;

@Service(IntegrationService.NAME)
public class IntegrationServiceBean implements IntegrationService {

    @Inject
    protected ItemService itemService;
    @Inject
    protected UserService userService;
    @Inject
    protected Logger log;
    @Inject
    protected CarService carService;
    @Inject
    protected LoggerService loggerService;
    @Inject
    protected BanCardService banCardService;
    @Inject
    protected PartnerService partnerService;
    @Inject
    protected LocationService locationService;
    @Inject
    protected DataManager dataManager;
    @Inject
    protected PayPalService payPalService;
    @Inject
    protected NotificationService notificationService;
    @Inject
    protected EmailerConfig emailerConfig;

    @Override
    public ItemsPojoList getItems(@NotNull String type, @NotNull Integer page, String locale) throws Exception {

        Validate.notNull(type);
        Validate.notNull(page);

        return itemService.getItems(type, page, locale);
    }

    @Override
    public CarPojoList getFavCars(String locale) throws Exception {
        return carService.getFavCars(locale);
    }

    @Override
    public CarPojoList getCars(@NotNull Integer page, String locale) throws Exception {
        return carService.getCars(page, locale);
    }

    @Override
    public CarPojoList getCars(@NotNull Integer page, String locale, String filter) throws Exception {
        return carService.getCars(page, locale, filter);
    }

    @Override
    public PartnerPojoList getPartners(String locale) throws Exception {
        return partnerService.getPartners(locale);
    }

    @Override
    public PartnerPojo getPartnerById(UUID partnerId, String locale) throws Exception {
        return partnerService.getPartnerById(partnerId, locale);
    }

    @Override
    public CarPojo getCarById(@NotNull UUID carId, String locale) throws Exception {

        Validate.notNull(carId);

        return carService.getCarById(carId, locale);
    }


    @Override
    public ItemPojo getItemById(@NotNull UUID itemId, String locale) throws Exception {

        Validate.notNull(itemId);

        return itemService.getItemById(itemId, locale);
    }

    @Override
    public Boolean sendOtpCode(@NotNull String email) throws Exception {
        try {
            userService.sendPasswordToUser(email);
            return true;
        } catch (Exception exception) {
            log.error(ExceptionUtils.getMessage(exception));
            return false;
        }
    }

    @Override
    public Boolean isOtpValid(@NotNull Integer otpCode, @NotNull String email) {

        Validate.notNull(otpCode);
        Validate.notNull(email);

        try {
            return userService.isOtpCodeValid(email, otpCode);
        } catch (Exception exception) {
            log.error(ExceptionUtils.getMessage(exception));
            return false;
        }
    }

    @Override
    public Boolean renewPassword(@NotNull UserPojo userPojo) {
        try {
            userService.renewPassword(userPojo);
            return true;
        } catch (Exception exception) {
            log.error(ExceptionUtils.getMessage(exception));
            return false;
        }
    }

    @Override
    public UserPojo getUser() {
        try {
            return userService.getUser();
        } catch (Exception ex) {
            loggerService.saveLog("getUser", "", ex);
            return null;
        }
    }

    @Override
    public Boolean updateUser(UserPojo userPojo) {
        try {
            userService.updateUser(userPojo);
            return true;
        } catch (Exception ex) {
            loggerService.saveLog("getUser", userPojo.toString(), ex);
            return false;
        }
    }

    @Override
    public Boolean createBankCard(BankCardPojo bankCardPojo) {
        try {
            banCardService.createBankCard(bankCardPojo);
            return true;
        } catch (Exception ex) {
            loggerService.saveLog("getUser", "", ex);
            return false;
        }
    }

    @Override
    public Boolean removeBankCard(BankCardPojo bankCardPojo) {
        try {
            banCardService.deleteBankCard(bankCardPojo);
            return true;
        } catch (Exception ex) {
            loggerService.saveLog("getUser", "", ex);
            return false;
        }
    }

    @Override
    public LocationListPojo getLocations(String locale) {
        return locationService.getLocations(locale);
    }

    @Override
    public String createOrder(UUID carId, Boolean useBonus) throws Exception {
        Validate.notNull(useBonus);
        Validate.notNull(carId);


        Car car = dataManager.load(Car.class).id(carId)
                .optional()
                .orElse(null);
        if (car == null)
            throw new Exception("CAR NOT FOUND");

        return payPalService.createOrder(car, useBonus);
    }

    public void checkOrder(String approveLink) throws Exception {
        payPalService.checkOrder(approveLink);
    }

    @Override
    public void sendEmail(EmailPojo emailPojo) {
        String body = "<html><p><strong>Новая заявка</strong></p>\n" +
                "<p><strong>Номер телефона:</strong> ${phone}</p>\n" +
                "<p><strong>Имя:</strong> ${name}. <strong>Почта:</strong> ${email}</p>\n" +
                "<p><strong>Сообщение от пользователя:</strong> ${body}</p></html>";

        body = body.replace("${phone}", emailPojo.getPhone() != null ? emailPojo.getPhone() : "Не заполнен");
        body = body.replace("${name}", emailPojo.getName() != null ? emailPojo.getName() : "Не заполнен");
        body = body.replace("${email}", emailPojo.getEmail() != null ? emailPojo.getEmail() : "Не заполнен");
        body = body.replace("${body}", emailPojo.getBody() != null ? emailPojo.getBody() : "Не заполнен");

        String adminEmail = emailerConfig.getAdminAddress();
        notificationService.sendEmail(body, "Новая заявка в контактах", "dserikov07@gmail.com");
    }

}