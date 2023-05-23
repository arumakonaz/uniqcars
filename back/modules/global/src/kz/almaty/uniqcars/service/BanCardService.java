package kz.almaty.uniqcars.service;

import kz.almaty.uniqcars.entity.pojo.BankCardPojo;

public interface BanCardService {
    String NAME = "uniqcars_BanCardService";


    void createBankCard(BankCardPojo bankCardPojo);
    void deleteBankCard(BankCardPojo bankCardPojo);
}