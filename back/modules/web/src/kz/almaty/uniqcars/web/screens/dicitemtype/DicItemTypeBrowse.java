package kz.almaty.uniqcars.web.screens.dicitemtype;

import com.haulmont.cuba.gui.screen.*;
import kz.almaty.uniqcars.entity.DicItemType;

@UiController("uniqcars_DicItemType.browse")
@UiDescriptor("dic-item-type-browse.xml")
@LookupComponent("dicItemTypesTable")
@LoadDataBeforeShow
public class DicItemTypeBrowse extends StandardLookup<DicItemType> {
}