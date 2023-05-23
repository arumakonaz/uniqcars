package kz.almaty.uniqcars.entity;

import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Table(name = "UNIQCARS_CAR")
@Entity(name = "uniqcars_Car")
public class Car extends AbstractEntity {
    private static final long serialVersionUID = 3641999385300780532L;


    @NotNull
    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "CHASIS_TYPE")
    private String chasisType;

    @JoinTable(name = "UNIQCARS_CAR_FILE_DESCRIPTOR_LINK",
            joinColumns = @JoinColumn(name = "CAR_ID"),
            inverseJoinColumns = @JoinColumn(name = "FILE_DESCRIPTOR_ID"))
    @ManyToMany
    private List<FileDescriptor> images;

    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "car")
    private List<AdditionalData> additionalDatas;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "YEAR_")
    private Integer year;

    @Column(name = "DISPLACEMENT")
    private Double displacement;

    @Column(name = "MILEAGE")
    private Integer mileage;

    @Column(name = "TRANSMISSION")
    private String transmission;

    @Column(name = "ENGINE_TYPE")
    private String engineType;

    @Column(name = "IS_FAVOURITE")
    private Boolean isFavourite;

    public void setDisplacement(Double displacement) {
        this.displacement = displacement;
    }

    public Double getDisplacement() {
        return displacement;
    }

    public ChasisType getChasisType() {
        return chasisType == null ? null : ChasisType.fromId(chasisType);
    }

    public void setChasisType(ChasisType chasisType) {
        this.chasisType = chasisType == null ? null : chasisType.getId();
    }

    public Boolean getIsFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(Boolean isFavourite) {
        this.isFavourite = isFavourite;
    }

    public EngineType getEngineType() {
        return engineType == null ? null : EngineType.fromId(engineType);
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType == null ? null : engineType.getId();
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setLocation(Location location) {
        this.location = location == null ? null : location.getId();
    }

    public Location getLocation() {
        return location == null ? null : Location.fromId(location);
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission == null ? null : transmission.getId();
    }

    public Transmission getTransmission() {
        return transmission == null ? null : Transmission.fromId(transmission);
    }

    public List<FileDescriptor> getImages() {
        return images;
    }

    public void setImages(List<FileDescriptor> images) {
        this.images = images;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<AdditionalData> getAdditionalDatas() {
        return additionalDatas;
    }

    public void setAdditionalDatas(List<AdditionalData> additionalDatas) {
        this.additionalDatas = additionalDatas;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

}