package kz.almaty.uniqcars.entity;

import com.haulmont.chile.core.annotations.NamePattern;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "UNIQCARS_DIC_ITEM_TYPE")
@Entity(name = "uniqcars_DicItemType")
@NamePattern("%s | name,nameRu,nameKz,nameEn")
public class DicItemType extends AbstractDictionary {
    private static final long serialVersionUID = 3648594297636872235L;
}