package kz.almaty.uniqcars.web.screens.media;

import com.haulmont.cuba.gui.screen.*;
import kz.almaty.uniqcars.entity.Media;

@UiController("uniqcars_Media.edit")
@UiDescriptor("media-edit.xml")
@EditedEntityContainer("mediaDc")
@LoadDataBeforeShow
public class MediaEdit extends StandardEditor<Media> {
}