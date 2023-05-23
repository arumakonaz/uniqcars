package kz.almaty.uniqcars.entity;

import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Table(name = "UNIQCARS_BANK_CARD")
@Entity(name = "uniqcars_BankCard")
public class BankCard extends StandardEntity {
    private static final long serialVersionUID = -953896413896606737L;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @OnDelete(DeletePolicy.UNLINK)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private UserExt user;

    @Column(name = "NUMBER_", nullable = false)
    @NotNull
    private String number;

    @Temporal(TemporalType.DATE)
    @Column(name = "EXPIRE_DATE", nullable = false)
    @NotNull
    private Date expireDate;

    @Column(name = "NAME_ON_CARD", nullable = false)
    @NotNull
    private String nameOnCard;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public UserExt getUser() {
        return user;
    }

    public void setUser(UserExt user) {
        this.user = user;
    }
}