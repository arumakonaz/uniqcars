package kz.almaty.uniqcars.entity.pojo;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@MetaClass(name = "uniqcars_CarPojo")
public class CarPojo extends BaseUuidEntity {
    private static final long serialVersionUID = -4945227410193537430L;

    @MetaProperty
    protected String name;

    @MetaProperty
    protected BigDecimal price;

    @MetaProperty
    protected List<ImagePojo> images;
    @MetaProperty
    protected Double displacement;

    @MetaProperty
    protected List<AdditionalDataPojo> additionalData;

    @MetaProperty
    protected String description;

    @MetaProperty
    protected String location;

    @MetaProperty
    protected Integer mileage;

    @MetaProperty
    protected String transmission;

    @MetaProperty
    protected String chasisType;

    public String getChasisType() {
        return chasisType;
    }

    public void setChasisType(String chasisType) {
        this.chasisType = chasisType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
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

    public List<ImagePojo> getImages() {
        return images;
    }

    public void setImages(List<ImagePojo> images) {
        this.images = images;
    }

    public List<AdditionalDataPojo> getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(List<AdditionalDataPojo> additionalData) {
        this.additionalData = additionalData;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getDisplacement() {
        return displacement;
    }

    public void setDisplacement(Double displacement) {
        this.displacement = displacement;
    }
}