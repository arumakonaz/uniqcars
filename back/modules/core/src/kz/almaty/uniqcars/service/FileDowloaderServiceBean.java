package kz.almaty.uniqcars.service;

import com.haulmont.cuba.core.app.FileStorageAPI;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.FileStorageException;
import kz.almaty.uniqcars.config.LoadingConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Calendar;
import java.util.Date;

@Service(FileDowloaderService.NAME)
public class FileDowloaderServiceBean implements FileDowloaderService {

    @Inject
    protected FileStorageAPI fileStorageAPI;
    @Inject
    protected LoggerService loggerService;
    @Inject
    protected LoadingConfig loadingConfig;

    @Override
    public byte[] getBytesFromFileDescriptor(FileDescriptor fileDescriptor) {
        try {
            return fileStorageAPI.loadFile(fileDescriptor);
        } catch (FileStorageException e) {
            loggerService.saveLog("getBytesFromFileDescriptor", fileDescriptor.getId(), e);
            return null;
        }
    }

    @Override
    public String getImageLink(FileDescriptor fileDescriptor) {
        try {
            return loadingConfig.getAmazonLink()+resolveFileName(fileDescriptor);
        } catch (Exception e) {
            loggerService.saveLog("getBytesFromFileDescriptor", fileDescriptor.getId(), e);
            return null;
        }
    }

    protected String getStorageDir(Date createDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(createDate);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return String.format("%d/%s/%s", year,
                StringUtils.leftPad(String.valueOf(month), 2, '0'),
                StringUtils.leftPad(String.valueOf(day), 2, '0'));
    }

    protected String resolveFileName(FileDescriptor fileDescr) {
        return getStorageDir(fileDescr.getCreateDate()) + "/" + getFileName(fileDescr);
    }

    protected String getFileName(FileDescriptor fileDescriptor) {
        if (StringUtils.isNotBlank(fileDescriptor.getExtension())) {
            return fileDescriptor.getId().toString() + "." + fileDescriptor.getExtension();
        } else {
            return fileDescriptor.getId().toString();
        }
    }

}