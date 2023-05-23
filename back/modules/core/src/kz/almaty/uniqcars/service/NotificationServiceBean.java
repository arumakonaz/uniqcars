package kz.almaty.uniqcars.service;

import com.haulmont.cuba.core.app.EmailService;
import com.haulmont.cuba.core.global.EmailException;
import com.haulmont.cuba.core.global.EmailInfo;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(NotificationService.NAME)
public class NotificationServiceBean implements NotificationService {

    @Inject
    private EmailService emailService;
    @Inject
    protected Logger log;

    @Override
    public void sendEmail(String body, String caption, String email) {
        EmailInfo emailInfo = new EmailInfo(email,caption,body);

        try {
            emailService.sendEmail(emailInfo);
        } catch (EmailException e) {
            log.error(ExceptionUtils.getMessage(e));
        }
    }

}