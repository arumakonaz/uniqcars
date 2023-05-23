package kz.almaty.uniqcars.entity.pojo;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;
import kz.almaty.uniqcars.entity.ModelRangePojo;

import java.util.List;

@MetaClass(name = "uniqcars_PartnerPojo")
public class PartnerPojo extends BaseUuidEntity {
    private static final long serialVersionUID = 6615732699443260101L;

    @MetaProperty
    private String name;
    @MetaProperty
    private String location;
    @MetaProperty
    private Integer year;

    @MetaProperty
    private String image;
    @MetaProperty
    private List<MediaPojo> mediaList;
    @MetaProperty
    private List<ModelRangePojo> modelRangeList;

    public List<MediaPojo> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<MediaPojo> mediaList) {
        this.mediaList = mediaList;
    }

    public List<ModelRangePojo> getModelRangeList() {
        return modelRangeList;
    }

    public void setModelRangeList(List<ModelRangePojo> modelRangeList) {
        this.modelRangeList = modelRangeList;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}