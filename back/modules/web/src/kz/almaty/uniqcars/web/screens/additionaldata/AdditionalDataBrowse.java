package kz.almaty.uniqcars.web.screens.additionaldata;

import com.haulmont.cuba.gui.screen.*;
import kz.almaty.uniqcars.entity.AdditionalData;

@UiController("uniqcars_AdditionalData.browse")
@UiDescriptor("additional-data-browse.xml")
@LookupComponent("additionalDatasTable")
@LoadDataBeforeShow
public class AdditionalDataBrowse extends StandardLookup<AdditionalData> {
}