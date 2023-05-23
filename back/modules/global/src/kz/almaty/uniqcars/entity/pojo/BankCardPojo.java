package kz.almaty.uniqcars.entity.pojo;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

import java.util.Date;

@MetaClass(name = "uniqcars_BankCardPojo")
public class BankCardPojo extends BaseUuidEntity {
    private static final long serialVersionUID = -5005660293407573111L;

    @MetaProperty
    private String number;

    @MetaProperty
    private Date expireDate;

    @MetaProperty
    private String nameOnCard;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }
}