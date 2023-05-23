package kz.almaty.uniqcars.web.screens.modelrange;

import com.haulmont.cuba.gui.screen.*;
import kz.almaty.uniqcars.entity.ModelRange;

@UiController("uniqcars_ModelRange.edit")
@UiDescriptor("model-range-edit.xml")
@EditedEntityContainer("modelRangeDc")
@LoadDataBeforeShow
public class ModelRangeEdit extends StandardEditor<ModelRange> {
}