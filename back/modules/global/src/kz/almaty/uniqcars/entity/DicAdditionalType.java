package kz.almaty.uniqcars.entity;

import com.haulmont.chile.core.annotations.NamePattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table(name = "UNIQCARS_DIC_ADDITIONAL_TYPE")
@Entity(name = "uniqcars_DicAdditionalType")
@NamePattern("%s | name,nameRu,nameKz,nameEn")
public class DicAdditionalType extends AbstractDictionary {
    private static final long serialVersionUID = 1382696091732205198L;

    @NotNull
    @Column(name = "ORDER_", nullable = false)
    private Integer order;

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}