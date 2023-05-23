package kz.almaty.uniqcars.service;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.UserSessionSource;
import kz.almaty.uniqcars.entity.BankCard;
import kz.almaty.uniqcars.entity.UserExt;
import kz.almaty.uniqcars.entity.pojo.BankCardPojo;
import org.jsoup.helper.Validate;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(BanCardService.NAME)
public class BanCardServiceBean implements BanCardService {

    @Inject
    protected DataManager dataManager;
    @Inject
    protected UserSessionSource userSessionSource;

    @Override
    public void createBankCard(BankCardPojo bankCardPojo) {

        Validate.notNull(bankCardPojo);

        BankCard bankCard = dataManager.create(BankCard.class);
        bankCard.setNumber(bankCardPojo.getNumber());
        bankCard.setNameOnCard(bankCardPojo.getNameOnCard());
        bankCard.setExpireDate(bankCardPojo.getExpireDate());
        bankCard.setUser((UserExt) userSessionSource.getUserSession().getUser());
        dataManager.commit(bankCard);
    }

    @Override
    public void deleteBankCard(BankCardPojo bankCardPojo) {
        Validate.notNull(bankCardPojo);
        BankCard bankCard = dataManager.load(BankCard.class)
                .id(bankCardPojo.getId())
                .view("_base")
                .optional().orElse(null);

        if (bankCard == null)
            return;

        dataManager.remove(bankCard);
    }
}