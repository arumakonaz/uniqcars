package kz.almaty.uniqcars.web.screens.dicitemtype;

import com.haulmont.cuba.gui.screen.*;
import kz.almaty.uniqcars.entity.DicItemType;

@UiController("uniqcars_DicItemType.edit")
@UiDescriptor("dic-item-type-edit.xml")
@EditedEntityContainer("dicItemTypeDc")
@LoadDataBeforeShow
public class DicItemTypeEdit extends StandardEditor<DicItemType> {
}