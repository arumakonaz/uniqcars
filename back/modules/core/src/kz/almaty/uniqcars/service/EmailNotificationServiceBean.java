package kz.almaty.uniqcars.service;

import com.haulmont.cuba.core.app.EmailService;
import com.haulmont.cuba.core.app.EmailerAPI;
import com.haulmont.cuba.core.global.EmailException;
import com.haulmont.cuba.core.global.EmailInfo;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Service(EmailNotificationService.NAME)
public class EmailNotificationServiceBean implements EmailNotificationService {


    @Inject
    protected EmailService emailService;

    @Override
    public void sendEmailToUserFromTemplate(String caption, String template, String to, Map<String, Serializable> params) throws EmailException {
        EmailInfo emailInfo = new EmailInfo(to,
                caption,
                "",
                template,
                params);

        emailService.sendEmail(emailInfo);
    }
}