package kz.almaty.uniqcars.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.annotation.Extends;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity(name = "uniqcars_UserExt")
@NamePattern("%s|login")
@DiscriminatorValue("base$UserExt")
@Extends(User.class)
public class UserExt extends User {
    private static final long serialVersionUID = 8348470475842596192L;

    @Column(name = "BONUS")
    private BigDecimal bonus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IMAGE_ID")
    private FileDescriptor image;

    @Column(name = "OTP_CODE")
    private Integer otpCode;

    @OneToMany(mappedBy = "user")
    private List<BankCard> bankCards;

    public FileDescriptor getImage() {
        return image;
    }

    public void setImage(FileDescriptor image) {
        this.image = image;
    }

    public Integer getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(Integer otpCode) {
        this.otpCode = otpCode;
    }

    public List<BankCard> getBankCards() {
        return bankCards;
    }

    public void setBankCards(List<BankCard> bankCards) {
        this.bankCards = bankCards;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }
}