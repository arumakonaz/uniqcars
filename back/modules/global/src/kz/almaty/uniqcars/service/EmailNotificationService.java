package kz.almaty.uniqcars.service;

import com.haulmont.cuba.core.global.EmailException;

import java.io.Serializable;
import java.util.Map;

public interface EmailNotificationService {
    String NAME = "uniqcars_EmailNotificationService";

    void sendEmailToUserFromTemplate(String caption, String template, String to, Map<String, Serializable> params) throws EmailException;
}