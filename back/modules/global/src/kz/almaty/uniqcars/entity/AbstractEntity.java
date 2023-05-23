package kz.almaty.uniqcars.entity;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.PersistenceHelper;
import com.haulmont.cuba.core.global.UserSessionSource;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractEntity extends StandardEntity {
    private static final long serialVersionUID = 717857726967105983L;

    @Lob
    @Column(name = "NAME_RU")
    private String nameRu;

    @Lob
    @Column(name = "NAME_KZ")
    private String nameKz;

    @Lob
    @Column(name = "NAME_EN")
    private String nameEn;

    @Lob
    @Column(name = "DESCRIPTION_RU")
    private String descriptionRu;

    @Lob
    @Column(name = "DESCRIPTION_KZ")
    private String descriptionKz;

    @Lob
    @Column(name = "DESCRIPTION_EN")
    private String descriptionEn;


    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameKz() {
        return nameKz;
    }

    public void setNameKz(String nameKz) {
        this.nameKz = nameKz;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public void setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
    }

    public String getDescriptionKz() {
        return descriptionKz;
    }

    public void setDescriptionKz(String descriptionKz) {
        this.descriptionKz = descriptionKz;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    @MetaProperty(related = {"descriptionRu", "descriptionKz", "descriptionEn"})
    public String getDescription() {
        if (!isBaseLangsFullLoaded("descriptionRu", "descriptionKz", "descriptionEn"))
            return null;

        return getCurrentDescriptionValue();
    }

    @MetaProperty(related = {"nameRu", "nameKz", "nameEn"})
    public String getName() {
        if (!isBaseLangsFullLoaded("nameRu", "nameKz", "nameEn"))
            return null;

        return getCurrentNameValue();
    }

    private String getCurrentDescriptionValue() {
        String currentLanguage = getUserSessionLanguage();
        switch (currentLanguage) {
            case "en":
                return descriptionEn;
            case "kz":
                return descriptionKz;
            default:
                return descriptionRu;
        }
    }

    private boolean isBaseLangsFullLoaded(String... properties) {
        for (String property : properties) {
            if (!PersistenceHelper.isLoaded(this, property))
                return false;
        }
        return true;
    }

    public String getCurrentNameValue() {
        String currentLanguage = getUserSessionLanguage();
        switch (currentLanguage) {
            case "en":
                return nameEn;
            case "kz":
                return nameKz;
            default:
                return nameRu;
        }
    }

    public static String getUserSessionLanguage() {
        UserSessionSource userSessionSource = AppBeans.get("cuba_UserSessionSource");
        return userSessionSource.getLocale().getLanguage();
    }

}