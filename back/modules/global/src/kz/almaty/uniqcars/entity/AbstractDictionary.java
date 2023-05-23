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
public class AbstractDictionary extends StandardEntity {
    private static final long serialVersionUID = 4762615505109086124L;

    @Lob
    @Column(name = "NAME_RU")
    private String nameRu;

    @Column(name = "CODE")
    private String code;

    @Lob
    @Column(name = "NAME_KZ")
    private String nameKz;

    @Lob
    @Column(name = "NAME_EN")
    private String nameEn;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameKz() {
        return nameKz;
    }

    public void setNameKz(String nameKz) {
        this.nameKz = nameKz;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }



    @MetaProperty(related = {"nameRu", "nameKz", "nameEn"})
    public String getName() {
        if (!isBaseLangsFullLoaded("nameRu", "nameKz", "nameEn"))
            return null;

        return getCurrentNameValue();
    }


    protected boolean isBaseLangsFullLoaded(String... properties) {
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