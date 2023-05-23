package kz.almaty.uniqcars.web.screens.car;

import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.FileStorageException;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.model.CollectionPropertyContainer;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.gui.upload.FileUploadingAPI;
import kz.almaty.uniqcars.entity.Car;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.*;

@UiController("uniqcars_Car.edit")
@UiDescriptor("car-edit.xml")
@EditedEntityContainer("carDc")
@LoadDataBeforeShow
public class CarEdit extends StandardEditor<Car> {

    @Inject
    protected FileUploadingAPI fileUploadingAPI;
    @Inject
    protected CollectionPropertyContainer<FileDescriptor> imagesDc;
    @Inject
    protected Notifications notifications;
    @Inject
    protected Logger log;
    @Inject
    protected HBoxLayout imageWrapperLayout;
    @Inject
    protected UiComponents uiComponents;
    @Inject
    protected FileMultiUploadField multiUpload;


    @Inject
    private DataManager dataManager;



    @Subscribe("multiUpload")
    protected void onMultiUploadQueueUploadComplete(FileMultiUploadField.QueueUploadCompleteEvent event) {

        for (Map.Entry<UUID, String> entry : multiUpload.getUploadsMap().entrySet()) {
            UUID fileId = entry.getKey();
            String fileName = entry.getValue();
            FileDescriptor fd = fileUploadingAPI.getFileDescriptor(fileId, fileName);
            try {
                fileUploadingAPI.putFileIntoStorage(fileId, fd);
                imagesDc.getMutableItems().add(fd);

            } catch (FileStorageException e) {
                log.error(ExceptionUtils.getMessage(e));
            }
            dataManager.commit(fd);
        }
        notifications.create()
                .withCaption("Uploaded files: " + multiUpload.getUploadsMap().values())
                .show();
        multiUpload.clearUploads();
    }


    @Subscribe("imagesTable")
    protected void onImagesTableSelection(Table.SelectionEvent<FileDescriptor> event) {
        imageWrapperLayout.removeAll();
        Set<FileDescriptor> selectedImages = event.getSelected();

        if (!selectedImages.isEmpty()) {
            xrayImage(selectedImages.stream().findFirst().orElse(null));
        }
    }

    private void xrayImage(FileDescriptor file) {
        imageWrapperLayout.add(getImageComponent(file));
    }

    private Component getImageComponent(FileDescriptor fileDescriptor) {

        Image image = uiComponents.create(Image.class);

        FileDescriptorResource resource = image.createResource(FileDescriptorResource.class)
                .setFileDescriptor(fileDescriptor);

        image.setScaleMode(Image.ScaleMode.CONTAIN);
        image.setHeight("500");
        image.setWidth("500");
        image.setStyleName("avatar-icon-large");
        image.setAlignment(Component.Alignment.BOTTOM_CENTER);
        image.setSource(resource);
        return image;
    }
}