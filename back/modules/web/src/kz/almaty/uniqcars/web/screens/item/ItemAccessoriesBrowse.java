package kz.almaty.uniqcars.web.screens.item;

import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.screen.*;
import kz.almaty.uniqcars.entity.DicItemType;
import kz.almaty.uniqcars.entity.Item;
import kz.almaty.uniqcars.service.CommonService;

import javax.inject.Inject;

@UiController("uniqcars_ItemAccessories.browse")
@UiDescriptor("item-AccessoriesBrowse.xml")
@LookupComponent("itemsTable")
@LoadDataBeforeShow
public class ItemAccessoriesBrowse extends StandardLookup<Item> {

    @Inject
    protected CommonService commonService;
    @Inject
    protected ScreenBuilders screenBuilders;
    @Inject
    protected GroupTable<Item> itemsTable;

    @Subscribe("itemsTable.create")
    protected void onItemsTableCreate(Action.ActionPerformedEvent event) {
        screenBuilders.editor(itemsTable)
                .newEntity()
                .withScreenClass(ItemEdit.class)
                .withInitializer(e -> e.setItemType(commonService.getEntity(DicItemType.class, "ACCESSORIES")))
                .build()
                .show();
    }

}