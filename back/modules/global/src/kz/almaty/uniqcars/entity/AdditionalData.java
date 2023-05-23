package kz.almaty.uniqcars.entity;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table(name = "UNIQCARS_ADDITIONAL_DATA")
@Entity(name = "uniqcars_AdditionalData")
@NamePattern("%s | name,nameRu,nameKz,nameEn")
public class AdditionalData extends AbstractDictionary {
    private static final long serialVersionUID = 1540252940603799365L;

    @NotNull
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TYPE_ID")
    private DicAdditionalType type;

    @JoinTable(name = "UNIQCARS_ADDITIONAL_DATA_FILE_DESCRIPTOR_LINK",
            joinColumns = @JoinColumn(name = "ADDITIONAL_DATA_ID"),
            inverseJoinColumns = @JoinColumn(name = "FILE_DESCRIPTOR_ID"))
    @OnDelete(DeletePolicy.CASCADE)
    @ManyToMany
    private List<FileDescriptor> images;

    @Lob
    @Column(name = "VALUE_RU")
    private String valueRu;

    @Lob
    @Column(name = "VALUE_KZ")
    private String valueKz;

    @Lob
    @Column(name = "VALUE_EN")
    private String valueEn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAR_ID")
    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<FileDescriptor> getImages() {
        return images;
    }

    public void setImages(List<FileDescriptor> images) {
        this.images = images;
    }

    public DicAdditionalType getType() {
        return type;
    }

    public void setType(DicAdditionalType type) {
        this.type = type;
    }

    public String getValueRu() {
        return valueRu;
    }

    public void setValueRu(String valueRu) {
        this.valueRu = valueRu;
    }

    public String getValueKz() {
        return valueKz;
    }

    public void setValueKz(String valueKz) {
        this.valueKz = valueKz;
    }

    public String getValueEn() {
        return valueEn;
    }

    public void setValueEn(String valueEn) {
        this.valueEn = valueEn;
    }

    @MetaProperty(related = {"valueRu", "valueKz", "valueEn"})
    public String getValue() {
        if (!isBaseLangsFullLoaded("valueRu", "valueKz", "valueEn"))
            return null;

        return getCurrentValue();
    }

    public String getCurrentValue() {
        String currentLanguage = getUserSessionLanguage();
        switch (currentLanguage) {
            case "EN":
                return valueEn;
            case "KK":
                return valueKz;
            default:
                return valueRu;
        }
    }


}