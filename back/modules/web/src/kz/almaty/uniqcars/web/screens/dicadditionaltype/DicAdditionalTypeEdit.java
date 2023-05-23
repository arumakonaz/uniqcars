package kz.almaty.uniqcars.web.screens.dicadditionaltype;

import com.haulmont.cuba.gui.screen.*;
import kz.almaty.uniqcars.entity.DicAdditionalType;

@UiController("uniqcars_DicAdditionalType.edit")
@UiDescriptor("dic-additional-type-edit.xml")
@EditedEntityContainer("dicAdditionalTypeDc")
@LoadDataBeforeShow
public class DicAdditionalTypeEdit extends StandardEditor<DicAdditionalType> {
}