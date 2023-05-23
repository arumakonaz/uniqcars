package kz.almaty.uniqcars.web.screens.partner;

import com.haulmont.cuba.gui.screen.*;
import kz.almaty.uniqcars.entity.Partner;

@UiController("uniqcars_Partner.browse")
@UiDescriptor("partner-browse.xml")
@LookupComponent("partnersTable")
@LoadDataBeforeShow
public class PartnerBrowse extends StandardLookup<Partner> {
}