package kz.almaty.uniqcars.entity.pojo;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

@MetaClass(name = "uniqcars_ImagePojo")
public class ImagePojo extends BaseUuidEntity {
    private static final long serialVersionUID = -7809800479863494621L;

    @MetaProperty
    String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}