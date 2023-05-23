package kz.almaty.uniqcars.service;

import kz.almaty.uniqcars.entity.Car;

public interface PayPalService {
    String NAME = "uniqcars_PayPalService";

    public String createOrder(Car car, Boolean useBonus) throws Exception;

    void checkOrder(String approveLink) throws Exception;

}