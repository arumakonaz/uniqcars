package kz.almaty.uniqcars.web.screens.additionaldata;

import com.haulmont.cuba.gui.screen.*;
import kz.almaty.uniqcars.entity.AdditionalData;

@UiController("uniqcars_AdditionalData.edit")
@UiDescriptor("additional-data-edit.xml")
@EditedEntityContainer("additionalDataDc")
@LoadDataBeforeShow
public class AdditionalDataEdit extends StandardEditor<AdditionalData> {
}