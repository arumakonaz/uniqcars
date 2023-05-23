package kz.almaty.uniqcars.service;

import kz.almaty.uniqcars.entity.pojo.*;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.UUID;

public interface IntegrationService {
    String NAME = "uniqcars_IntegrationService";

    ItemsPojoList getItems(@NotNull String type, @NotNull Integer page, String locale) throws Exception;

    ItemPojo getItemById(@NotNull UUID itemId, String locale) throws Exception;

    Boolean sendOtpCode(@NotNull String email) throws Exception;

    Boolean isOtpValid(@NotNull Integer otpCode, @NotNull String email);

    Boolean renewPassword(@NotNull UserPojo userPojo);

    CarPojoList getFavCars(String locale) throws Exception;

    CarPojoList getCars(@NotNull Integer page, String locale) throws Exception;

    CarPojoList getCars(@NotNull Integer page, String locale, String filter) throws Exception;

    PartnerPojoList getPartners(String locale) throws Exception;

    PartnerPojo getPartnerById(UUID partnerId, String locale) throws Exception;

    CarPojo getCarById(@NotNull UUID carId, String locale) throws Exception;

    UserPojo getUser();

    Boolean updateUser(UserPojo userPojo);

    Boolean createBankCard(BankCardPojo bankCardPojo);

    Boolean removeBankCard(BankCardPojo bankCardPojo);

    LocationListPojo getLocations(String locale);

    String createOrder(UUID carId, Boolean useBonus) throws Exception;

    void checkOrder(String approveLink) throws Exception;

    void sendEmail(EmailPojo emailPojo);

}