package kz.almaty.uniqcars.service;

import kz.almaty.uniqcars.entity.pojo.CarPojo;
import kz.almaty.uniqcars.entity.pojo.CarPojoList;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.UUID;

public interface CarService {
    String NAME = "uniqcars_CarService";

    CarPojoList getFavCars(String locale) throws Exception;

    CarPojoList getCars(@NotNull Integer page, String locale) throws Exception;

    CarPojoList getCars(@NotNull Integer page, String locale, String filter) throws Exception;

    CarPojo getCarById(@NotNull UUID carId, String locale) throws Exception;
}