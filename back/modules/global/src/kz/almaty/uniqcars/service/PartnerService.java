package kz.almaty.uniqcars.service;

import kz.almaty.uniqcars.entity.pojo.PartnerPojo;
import kz.almaty.uniqcars.entity.pojo.PartnerPojoList;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public interface PartnerService {
    String NAME = "uniqcars_PartnerService";

    PartnerPojoList getPartners(String locale) throws Exception;
    PartnerPojo getPartnerById(UUID partnerId, String locale) throws Exception;

}