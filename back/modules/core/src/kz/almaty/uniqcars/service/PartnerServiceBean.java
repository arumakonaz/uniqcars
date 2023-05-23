package kz.almaty.uniqcars.service;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import kz.almaty.uniqcars.entity.Media;
import kz.almaty.uniqcars.entity.ModelRange;
import kz.almaty.uniqcars.entity.ModelRangePojo;
import kz.almaty.uniqcars.entity.Partner;
import kz.almaty.uniqcars.entity.pojo.MediaPojo;
import kz.almaty.uniqcars.entity.pojo.PartnerPojo;
import kz.almaty.uniqcars.entity.pojo.PartnerPojoList;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service(PartnerService.NAME)
public class PartnerServiceBean implements PartnerService {


    @Inject
    protected UserService userService;
    @Inject
    protected DataManager dataManager;
    @Inject
    protected LoggerService loggerService;
    @Inject
    protected Metadata metadata;
    @Inject
    protected LocationService locationService;
    @Inject
    protected FileDowloaderService fileDowloaderService;

    @Override
    public PartnerPojoList getPartners(String locale) throws Exception {

        try {
            if (locale != null && !locale.isEmpty())
                userService.changeUserSessionLocale(locale);

            List<Partner> partners = dataManager.load(Partner.class)
                    .query("select e from uniqcars_Partner e")
                    .view("partner-getAllView")
                    .list();

            if (partners.isEmpty())
                return null;

            return partnersToPojo(partners);
        } catch (Exception exception) {
            loggerService.saveLog("PartnerServiceBean.getItems", "", exception);
            throw new Exception("Обратитесь к администратору");
        }
    }

    @Override
    public PartnerPojo getPartnerById(UUID partnerId, String locale) throws Exception {

        try {
            if (locale != null && !locale.isEmpty())
                userService.changeUserSessionLocale(locale);

            Partner partner = dataManager.load(Partner.class)
                    .id(partnerId)
                    .view("partner-withDetailsView")
                    .optional()
                    .orElse(null);

            if (partner == null)
                throw new Exception("Partner not found");

            return partnerWithDeatilsToPojo(partner);
        } catch (Exception exception) {
            loggerService.saveLog("PartnerServiceBean.getItems", "", exception);
            throw new Exception("Обратитесь к администратору");
        }
    }

    protected PartnerPojo partnerWithDeatilsToPojo(Partner partner) {
        PartnerPojo partnerPojo = getPartnerPojoFromEntity(partner);
        partnerPojo.setMediaList(getMedialistPojo(partner));
        partnerPojo.setModelRangeList(getModelRangeListPojo(partner));
        return partnerPojo;
    }

    private List<ModelRangePojo> getModelRangeListPojo(Partner partner) {
        if (partner.getModelRangeList() == null || partner.getModelRangeList().isEmpty())
            return null;

        List<ModelRangePojo> modelRangePojos = new ArrayList<>();
        for (ModelRange modelRange : partner.getModelRangeList()) {
            ModelRangePojo modelRangePojo = metadata.create(ModelRangePojo.class);
            modelRangePojo.setImage(modelRange.getImage() != null
                    ? fileDowloaderService.getImageLink(modelRange.getImage()) : null);
            modelRangePojo.setName(modelRange.getName());
            modelRangePojo.setPrice(modelRange.getPrice());
            modelRangePojos.add(modelRangePojo);
        }
        return modelRangePojos;
    }

    private List<MediaPojo> getMedialistPojo(Partner partner) {

        if (partner.getMediaList() == null || partner.getMediaList().isEmpty())
            return null;

        List<MediaPojo> mediaPojos = new ArrayList<>();
        for (Media media : partner.getMediaList()) {
            MediaPojo mediaPojo = metadata.create(MediaPojo.class);
            mediaPojo.setImage(media.getImage() != null
                    ? fileDowloaderService.getImageLink(media.getImage()) : null);
            mediaPojo.setName(media.getName());
            mediaPojo.setLocation(media.getLocation().toString());
            mediaPojo.setLocation(locationService.getLocationLocale(media.getLocation()));
            mediaPojos.add(mediaPojo);
        }
        return mediaPojos;
    }


    protected PartnerPojoList partnersToPojo(List<Partner> partners) {

        List<PartnerPojo> partnerPojos = new ArrayList<>();

        PartnerPojoList partnerPojoList = metadata.create(PartnerPojoList.class);
        partners.forEach(e -> partnerPojos.add(getPartnerPojoFromEntity(e)));

        partnerPojoList.setPartnerPojos(partnerPojos);

        return partnerPojoList;
    }

    protected PartnerPojo getPartnerPojoFromEntity(Partner partner) {
        PartnerPojo partnerPojo = metadata.create(PartnerPojo.class);
        partnerPojo.setId(partner.getId());
        partnerPojo.setLocation(locationService.getLocationLocale(partner.getLocation()));
        partnerPojo.setName(partner.getName());
        partnerPojo.setYear(partner.getYear());
        partnerPojo.setImage(partner.getImage() != null ? fileDowloaderService.getImageLink(partner.getImage()) : null);
        return partnerPojo;
    }
}