package kz.almaty.uniqcars.service;

import com.haulmont.cuba.core.entity.FileDescriptor;
import kz.almaty.uniqcars.entity.pojo.ImagePojo;

import java.util.List;
import java.util.UUID;

public interface FileDescriptorService {
    String NAME = "uniqcars_FileDescriptorService";

    List<ImagePojo> getImages(List<FileDescriptor> images);

}