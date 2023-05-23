package kz.almaty.uniqcars.web.screens.userext;

import com.haulmont.cuba.gui.screen.*;
import kz.almaty.uniqcars.entity.UserExt;

@UiController("uniqcars_UserExt.edit")
@UiDescriptor("user-ext-edit.xml")
@EditedEntityContainer("userExtDc")
@LoadDataBeforeShow
public class UserExtEdit extends StandardEditor<UserExt> {
}