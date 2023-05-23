package kz.almaty.uniqcars.web.screens.bankcard;

import com.haulmont.cuba.gui.screen.*;
import kz.almaty.uniqcars.entity.BankCard;

@UiController("uniqcars_BankCard.edit")
@UiDescriptor("bank-card-edit.xml")
@EditedEntityContainer("bankCardDc")
@LoadDataBeforeShow
public class BankCardEdit extends StandardEditor<BankCard> {
}