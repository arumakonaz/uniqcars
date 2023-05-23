package kz.almaty.uniqcars.entity.pojo;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

import java.util.List;

@MetaClass(name = "uniqcars_CarPojoList")
public class CarPojoList extends BaseUuidEntity {
    private static final long serialVersionUID = 7962628679318290106L;

    @MetaProperty
    private List<CarPojo> cars;

    @MetaProperty
    private Integer maxPage;


    public List<CarPojo> getCars() {
        return cars;
    }

    public void setCars(List<CarPojo> cars) {
        this.cars = cars;
    }

    public Integer getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(Integer maxPage) {
        this.maxPage = maxPage;
    }
}