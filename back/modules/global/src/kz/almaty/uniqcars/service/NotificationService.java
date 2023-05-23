package kz.almaty.uniqcars.service;

public interface NotificationService {
    String NAME = "uniqcars_NotificationService";

    void sendEmail(String body,String caption,String email);
}