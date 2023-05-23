package kz.almaty.uniqcars.entity.pojo;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Entity;
import java.util.List;

@MetaClass(name = "uniqcars_ItemsPojoList")
public class ItemsPojoList extends BaseUuidEntity {
    private static final long serialVersionUID = -4962828684635365784L;

    @MetaProperty
    private List<ItemPojo> items;

    @MetaProperty
    private Integer maxPage;

    public Integer getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(Integer maxPage) {
        this.maxPage = maxPage;
    }

    public List<ItemPojo> getItems() {
        return items;
    }

    public void setItems(List<ItemPojo> items) {
        this.items = items;
    }
}