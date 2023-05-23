package kz.almaty.uniqcars.web.screens.userext;

import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.screen.*;
import kz.almaty.uniqcars.entity.UserExt;

import javax.inject.Inject;

@UiController("uniqcars_UserExt.browse")
@UiDescriptor("user-ext-browse.xml")
@LookupComponent("userExtsTable")
@LoadDataBeforeShow
public class UserExtBrowse extends StandardLookup<UserExt> {


    @Inject
    protected ScreenBuilders screenBuilders;
    @Inject
    protected GroupTable<UserExt> userExtsTable;

    @Subscribe("userExtsTable.edit")
    protected void onUserExtsTableEdit(Action.ActionPerformedEvent event) {
        screenBuilders.editor(userExtsTable)
                .editEntity(userExtsTable.getSingleSelected())
                .withScreenClass(UserExtEdit.class)
                .build().show();
    }

}