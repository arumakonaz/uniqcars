package kz.almaty.uniqcars.service;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.Metadata;
import kz.almaty.uniqcars.config.LoadingConfig;
import kz.almaty.uniqcars.entity.AdditionalData;
import kz.almaty.uniqcars.entity.Car;
import kz.almaty.uniqcars.entity.ChasisType;
import kz.almaty.uniqcars.entity.Location;
import kz.almaty.uniqcars.entity.pojo.*;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.*;

@Service(CarService.NAME)
public class CarServiceBean implements CarService {

    @Inject
    protected DataManager dataManager;
    @Inject
    protected LoadingConfig loadingConfig;
    @Inject
    protected CommonService commonService;
    @Inject
    protected Metadata metadata;
    @Inject
    protected LoggerService loggerService;
    @Inject
    protected UserService userService;
    @Inject
    protected FileDescriptorService fileDescriptorService;
    @Inject
    protected LocationService locationService;

    private static final String GET_CAR_SQL = "select e from uniqcars_Car e";
    private static final String GET_UNIQUE_CAR_SQL = "select e from uniqcars_Car e where e.isFavourite = true";
    @Inject
    protected Messages messages;


    @Override
    public CarPojoList getFavCars(String locale) throws Exception {
        return getCars(GET_UNIQUE_CAR_SQL, 1, locale);
    }

    private CarPojoList getCars(String query, Integer page, String locale) throws Exception {
        try {

            if (locale != null && !locale.isEmpty())
                userService.changeUserSessionLocale(locale);

            Integer maxResults = loadingConfig.getMaxPerPage();

            Integer allCars = commonService.getCount(Car.class, query, null).intValue();

            List<Car> cars = dataManager.load(Car.class)
                    .query(query)
                    .view("car-getAllView")
                    .maxResults(maxResults)
                    .firstResult((page - 1) * maxResults)
                    .list();

            if (cars.isEmpty())
                return null;

            return carsToPojo(cars, allCars);
        } catch (Exception exception) {
            loggerService.saveLog("CarServiceBean.getItems", "page: " + page, exception);
            throw new Exception("Обратитесь к администратору");
        }
    }

    @Override
    public CarPojoList getCars(@NotNull Integer page, String locale) throws Exception {
        return getCars(GET_CAR_SQL, page, locale);
    }

    @Override
    public CarPojoList getCars(@NotNull Integer page, String locale, String filter) throws Exception {

        String finalSQL = GET_CAR_SQL;

        if (filter != null)
            finalSQL = addFilterToSQL(filter);

        return getCars(finalSQL, page, locale);

    }

    private String addSorter(String key, String ascDesc) {

        String sort = "";
        try {
            String[] keyValueName = key.split("Sort");
            sort = " order by e." + keyValueName[0] + " " + ascDesc;
            return sort;

        } catch (Exception exception) {
            loggerService.saveLog("CarService.addSorter",
                    "SQL: " + key + " ;Sort: " + ascDesc,
                    exception);

            return sort;
        }

    }

    private String addFilterToSQL(String filter) {
        StringBuilder finalSQL = new StringBuilder(GET_CAR_SQL);

        String[] splittedFilter = filter.split(",");

        finalSQL.append(" where ");
        Boolean sqlChanged = false;

        String sort = "";

        for (String singleFilter : splittedFilter) {


            String[] keyValue = singleFilter.split(":");

            if (keyValue[0].contains("Between")) {

                addBetweenClause(finalSQL, keyValue, sqlChanged);
                sqlChanged = true;
                continue;
            }

            if (!keyValue[0].contains("Sort")) {
                addEqualClause(finalSQL, keyValue, sqlChanged);
                sqlChanged = true;
                continue;
            }


            if (keyValue[0].contains("Sort")) {
                sort = addSorter(keyValue[0], keyValue[1]);
                continue;
            }

        }

        finalSQL.append(" " + sort);

        return finalSQL.toString();
    }


    private void addBetweenClause(StringBuilder finalSQL, String[] keyValue, Boolean sqlChanged) {
        if (sqlChanged)
            finalSQL.append(" and ");

        String key = keyValue[0].replace("Between", "");
        String[] betweenValues = keyValue[1].split("-");
        finalSQL.append("e." + key + " between " + betweenValues[0] + " and " + betweenValues[1]);
    }

    protected void addEqualClause(StringBuilder finalSQL, String[] keyValue, Boolean sqlChanged) {
        if (sqlChanged)
            finalSQL.append(" and ");

        String key = keyValue[0];
        String value = keyValue[1];

        finalSQL.append(String.format("e.%s = '%s'", key, value));
    }

    @Override
    public CarPojo getCarById(@NotNull UUID carId, String locale) throws Exception {

        if (locale != null && !locale.isEmpty())
            userService.changeUserSessionLocale(locale);

        try {
            Car car = dataManager.load(Car.class)
                    .id(carId)
                    .view("car-getAllView")
                    .optional().orElse(null);

            if (car == null)
                return null;

            return carToPojo(car);
        } catch (Exception exception) {
            loggerService.saveLog("CarServiceBean.getCarById", carId, exception);
            throw new Exception("Обратитесь к администратору");
        }

    }


    private Integer getFirstResult(Integer page, Integer maxResults) {
        if (page == 1)
            return 0;

        return maxResults * page - 1;
//        1 6 11
    }


    private CarPojo carToPojo(Car car) {
        List<AdditionalDataPojo> additionalDataPojos = new ArrayList<>();
        CarPojo carPojo = getCarPojoFromCar(car);

        for (AdditionalData additionalData : car.getAdditionalDatas()) {
            AdditionalDataPojo additionalDataPojo = new AdditionalDataPojo();
            additionalDataPojo.setId(additionalData.getId());
            additionalDataPojo.setName(additionalData.getType().getName());
            additionalDataPojo.setOrder(additionalData.getType().getOrder());
            additionalDataPojo.setValue(additionalData.getValue());
            additionalDataPojos.add(additionalDataPojo);
        }
        carPojo.setAdditionalData(additionalDataPojos);
        return carPojo;
    }

    private CarPojo getCarPojoFromCar(Car car) {
        CarPojo carPojo = metadata.create(CarPojo.class);
        carPojo.setId(car.getId());
        carPojo.setName(car.getName());
        carPojo.setPrice(car.getPrice());
        carPojo.setImages(fileDescriptorService.getImages(car.getImages()));
        carPojo.setDescription(car.getDescription());
        carPojo.setMileage(car.getMileage());
        carPojo.setDisplacement(car.getDisplacement());
        carPojo.setChasisType(getChasisTypeLocale(car.getChasisType()));
        carPojo.setLocation(locationService.getLocationLocale(car.getLocation()));
        carPojo.setTransmission(car.getTransmission().name());
        return carPojo;
    }

    private String getChasisTypeLocale(ChasisType chasisType) {
        if (chasisType == null)
            return null;
        return messages.getMessage(Location.class, "ChasisType." + chasisType.name());
    }

    private CarPojoList carsToPojo(List<Car> cars, Integer allCars) {

        List<CarPojo> carPojos = new ArrayList<>();

        CarPojoList carPojoList = metadata.create(CarPojoList.class);
        carPojoList.setMaxPage((int) Math.ceil((double) allCars / 5));
        cars.forEach(e -> carPojos.add(getCarPojoFromCar(e)));

        carPojoList.setCars(carPojos);

        return carPojoList;
    }
}