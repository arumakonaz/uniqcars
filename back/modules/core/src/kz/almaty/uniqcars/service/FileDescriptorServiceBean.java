package kz.almaty.uniqcars.service;

import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.Metadata;
import kz.almaty.uniqcars.entity.pojo.ImagePojo;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service(FileDescriptorService.NAME)
public class FileDescriptorServiceBean implements FileDescriptorService {

    @Inject
    protected LoggerService loggerService;
    @Inject
    protected FileDowloaderService fileDowloaderService;
    @Inject
    protected Metadata metadata;

    @Override
    public List<ImagePojo> getImages(List<FileDescriptor> images) {
        try {
            if (images.isEmpty())
                return Collections.emptyList();

            List<ImagePojo> imagePojos = new ArrayList<>();

            for (FileDescriptor image : images) {
                ImagePojo imagePojo = metadata.create(ImagePojo.class);
                imagePojo.setLink(fileDowloaderService.getImageLink(image));
                imagePojos.add(imagePojo);
            }
            return imagePojos;
        } catch (Exception exception) {
            loggerService.saveLog("FileDescriptorServiceBean.getImageIds", images, exception);
            return new ArrayList<>();
        }
    }

}