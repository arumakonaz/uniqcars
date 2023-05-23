package kz.almaty.uniqcars.service;

import javax.validation.constraints.NotNull;

public interface LoggerService {
    String NAME = "uniqcars_LoggerService";

    void saveLog(
            String methodName,
            Object methodParams,
            @NotNull Exception e);

    void saveLog(
            String methodName,
            Object methodParams,
            String message,
            String stackTrace,
            boolean isSuccess);

    void saveLog(
            String methodName,
            String methodParams,
            String message,
            String stackTrace,
            boolean isSuccess);
}