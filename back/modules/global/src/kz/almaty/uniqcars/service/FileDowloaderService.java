package kz.almaty.uniqcars.service;


import com.haulmont.cuba.core.entity.FileDescriptor;

public interface FileDowloaderService {
    String NAME = "uniqcars_FileDowloaderService";

    byte[] getBytesFromFileDescriptor(FileDescriptor fileDescriptor);

    String getImageLink(FileDescriptor fileDescriptor);
}