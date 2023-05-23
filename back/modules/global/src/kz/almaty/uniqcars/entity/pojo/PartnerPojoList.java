package kz.almaty.uniqcars.entity.pojo;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

import java.util.List;

@MetaClass(name = "uniqcars_PartnerPojoList")
public class PartnerPojoList extends BaseUuidEntity {
    private static final long serialVersionUID = -3239178451829536113L;

    @MetaProperty
    private List<PartnerPojo> partnerPojos;

    public List<PartnerPojo> getPartnerPojos() {
        return partnerPojos;
    }

    public void setPartnerPojos(List<PartnerPojo> partnerPojos) {
        this.partnerPojos = partnerPojos;
    }
}