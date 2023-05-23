package kz.almaty.uniqcars.service;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import kong.unirest.jackson.JacksonObjectMapper;
import kz.almaty.uniqcars.entity.ServiceLogger;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;

@Service(LoggerService.NAME)
public class LoggerServiceBean implements LoggerService {

    @Inject
    protected Logger log;
    @Inject
    private Metadata metadata;
    @Inject
    private DataManager dataManager;

    @Override
    public void saveLog(
            String methodName,
            Object methodParams,
            @NotNull Exception e) {

        saveLog(
                methodName,
                new JacksonObjectMapper()
                        .writeValue(methodParams),
                ExceptionUtils.getMessage(e),
                ExceptionUtils.getStackTrace(e),
                false
        );
    }

    @Override
    public void saveLog(
            String methodName,
            Object methodParams,
            String message,
            String stackTrace,
            boolean isSuccess) {

        saveLog(
                methodName,
                new JacksonObjectMapper()
                        .writeValue(methodParams),
                message,
                stackTrace,
                isSuccess
        );
    }

    @Override
    public void saveLog(
            String methodName,
            String methodParams,
            String message,
            String stackTrace,
            boolean isSuccess) {

        try {
            ServiceLogger serviceLogger = metadata.create(ServiceLogger.class);

            serviceLogger.setMethodName(methodName);
            serviceLogger.setParams(methodParams);
            serviceLogger.setMessage(message);
            serviceLogger.setStackTrace(stackTrace);
            serviceLogger.setIsSuccess(isSuccess);

            dataManager.commit(serviceLogger);
        } catch (Exception exception) {
            log.error(ExceptionUtils.getMessage(exception));
        }

    }

}