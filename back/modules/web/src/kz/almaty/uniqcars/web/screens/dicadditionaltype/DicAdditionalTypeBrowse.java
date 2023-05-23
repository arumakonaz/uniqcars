package kz.almaty.uniqcars.web.screens.dicadditionaltype;

import com.haulmont.cuba.gui.screen.*;
import kz.almaty.uniqcars.entity.DicAdditionalType;

@UiController("uniqcars_DicAdditionalType.browse")
@UiDescriptor("dic-additional-type-browse.xml")
@LookupComponent("dicAdditionalTypesTable")
@LoadDataBeforeShow
public class DicAdditionalTypeBrowse extends StandardLookup<DicAdditionalType> {
}