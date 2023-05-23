package kz.almaty.uniqcars.entity.pojo;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@MetaClass(name = "uniqcars_ItemPojo")
public class ItemPojo extends BaseUuidEntity {
    private static final long serialVersionUID = -2314560701019858068L;


    @MetaProperty
    protected String name;

    @MetaProperty
    protected BigDecimal price;

    @MetaProperty
    protected List<ImagePojo> images;

    @MetaProperty
    protected List<AdditionalDataPojo> additionalData;

    @MetaProperty
    protected String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<AdditionalDataPojo> getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(List<AdditionalDataPojo> additionalData) {
        this.additionalData = additionalData;
    }

    public List<ImagePojo> getImages() {
        return images;
    }

    public void setImages(List<ImagePojo> images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}