package kz.almaty.uniqcars.entity.pojo;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@MetaClass(name = "uniqcars_UserPojo")
public class UserPojo extends BaseUuidEntity {
    private static final long serialVersionUID = 1157135962531384790L;

    @MetaProperty
    private String email;

    @MetaProperty
    private Integer otpCode;

    @MetaProperty
    private String password;
    @MetaProperty
    private BigDecimal bonus;
    @MetaProperty
    private String firstName;
    @MetaProperty
    private String lastName;
    @MetaProperty
    private List<BankCardPojo> bankCards;
    @MetaProperty
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<BankCardPojo> getBankCards() {
        return bankCards;
    }

    public void setBankCards(List<BankCardPojo> bankCards) {
        this.bankCards = bankCards;
    }

    public void setOtpCode(Integer otpCode) {
        this.otpCode = otpCode;
    }

    public Integer getOtpCode() {
        return otpCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}