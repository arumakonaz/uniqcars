package kz.almaty.uniqcars.service;

import kz.almaty.uniqcars.entity.pojo.UserPojo;

import javax.validation.constraints.NotNull;

public interface UserService {
    String NAME = "uniqcars_UserService";

    UserPojo getUser();

    void updateUser(UserPojo userPojo);

    void changeUserSessionLocale(String locale);

    void sendPasswordToUser(@NotNull String email) throws Exception;

    Boolean isOtpCodeValid(@NotNull String email, @NotNull Integer otpCode);

    void renewPassword(@NotNull UserPojo userPojo);
}