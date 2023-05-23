package kz.almaty.uniqcars.entity.pojo;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

import java.util.List;

@MetaClass(name = "uniqcars_LocationListPojo")
public class LocationListPojo extends BaseUuidEntity {
    private static final long serialVersionUID = -1778483630518707862L;

    @MetaProperty
    private List<LocationPojo> locations;

    public List<LocationPojo> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationPojo> locations) {
        this.locations = locations;
    }
}