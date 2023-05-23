package kz.almaty.uniqcars.entity.pojo;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

@MetaClass(name = "uniqcars_AdditionalDataPojo")
@NamePattern("%s|name")
public class AdditionalDataPojo extends BaseUuidEntity {
    private static final long serialVersionUID = -8560339172286442527L;

    @MetaProperty
    private String name;

    @MetaProperty
    private String value;

    @MetaProperty
    private Integer order;

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}