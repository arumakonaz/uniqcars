package kz.almaty.uniqcars.web.screens.partner;

import com.haulmont.cuba.gui.screen.*;
import kz.almaty.uniqcars.entity.Partner;

@UiController("uniqcars_Partner.edit")
@UiDescriptor("partner-edit.xml")
@EditedEntityContainer("partnerDc")
@LoadDataBeforeShow
public class PartnerEdit extends StandardEditor<Partner> {
}