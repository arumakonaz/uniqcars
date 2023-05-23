package kz.almaty.uniqcars.entity.pojo;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;
import com.haulmont.cuba.core.entity.FileDescriptor;

import javax.persistence.*;
import java.util.Date;

@MetaClass(name = "uniqcars_MediaPojo")
public class MediaPojo extends BaseUuidEntity {
    private static final long serialVersionUID = -4323984409591470854L;


    @MetaProperty
    private String image;

    @MetaProperty
    private String name;

    @MetaProperty
    private String location;

    @MetaProperty
    private Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}