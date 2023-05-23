package kz.almaty.uniqcars.service;

import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.*;
import com.haulmont.cuba.security.entity.Group;
import com.haulmont.cuba.security.entity.User;
import kz.almaty.uniqcars.config.LoadingConfig;
import kz.almaty.uniqcars.entity.BankCard;
import kz.almaty.uniqcars.entity.UserExt;
import kz.almaty.uniqcars.entity.pojo.BankCardPojo;
import kz.almaty.uniqcars.entity.pojo.ImagePojo;
import kz.almaty.uniqcars.entity.pojo.UserPojo;
import org.checkerframework.checker.units.qual.C;
import org.jsoup.helper.Validate;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Service(UserService.NAME)
public class UserServiceBean implements UserService {

    @Inject
    protected LoadingConfig loadingConfig;
    @Inject
    protected UserSessionSource userSessionSource;
    @Inject
    protected DataManager dataManager;
    @Inject
    protected PasswordEncryption passwordEncryption;
    @Inject
    protected LoggerService loggerService;
    @Inject
    protected EmailNotificationService emailNotificationService;
    @Inject
    protected Metadata metadata;
    @Inject
    protected FileDescriptorService fileDescriptorService;
    @Inject
    protected FileDowloaderService fileDowloaderService;

    @Override
    public UserPojo getUser() {

        UUID userId = userSessionSource.getUserSession().getUser().getId();

        UserExt userExt = dataManager.load(UserExt.class)
                .id(userId)
                .view("userExt-EditView")
                .optional()
                .orElse(null);

        UserPojo userPojo = metadata.create(UserPojo.class);
        userPojo.setId(userId);
        userPojo.setEmail(userExt.getEmail());
        userPojo.setFirstName(userExt.getFirstName());
        userPojo.setLastName(userExt.getLastName());
        userPojo.setImage(userExt.getImage() != null ? fileDowloaderService.getImageLink(userExt.getImage()) : null);
        userPojo.setBonus(userExt.getBonus() == null ? new BigDecimal("0") : userExt.getBonus());
        userPojo.setBankCards(getPojoFromBankCards(userExt.getBankCards()));

        return userPojo;
    }

    @Override
    public void updateUser(UserPojo userPojo) {
        Validate.notNull(userPojo);

        UUID userId = userSessionSource.getUserSession().getUser().getId();

        UserExt userExt = dataManager.load(UserExt.class)
                .id(userId)
                .view("userExt-EditView")
                .optional()
                .orElse(null);

        if (!userId.equals(userPojo.getId()))
            throw new PermissionDeniedDataAccessException("Permission Denied", null);

        CommitContext commitContext = new CommitContext();

        userExt.setEmail(userPojo.getEmail());
        userExt.setLogin(userPojo.getEmail());
        userExt.setFirstName(userPojo.getFirstName());
        userExt.setLastName(userPojo.getLastName());
        userExt.setBonus(userPojo.getBonus());
        userExt.setImage(getDescriptorFromPojo(userPojo.getImage()));
        commitContext.addInstanceToCommit(userExt);

        for (BankCardPojo bankCardPojo : userPojo.getBankCards()) {
            BankCard bankCard = dataManager.load(BankCard.class)
                    .id(bankCardPojo.getId())
                    .view("_base")
                    .optional().orElse(null);

            if (bankCard == null)
                continue;

            bankCard.setExpireDate(bankCardPojo.getExpireDate());
            bankCard.setNameOnCard(bankCardPojo.getNameOnCard());
            bankCard.setNumber(bankCardPojo.getNumber());
            commitContext.addInstanceToCommit(bankCard);
        }
        dataManager.commit(commitContext);
    }

    private FileDescriptor getDescriptorFromPojo(String imageId) {
        if (imageId == null)
            return null;

        FileDescriptor fileDescriptor = dataManager.load(FileDescriptor.class)
                .id(UUID.fromString(imageId))
                .optional().orElse(null);

        return fileDescriptor;
    }

    private List<BankCardPojo> getPojoFromBankCards(List<BankCard> bankCards) {
        List<BankCardPojo> bankCardPojoList = new ArrayList<>();
        for (BankCard bankCard : bankCards) {
            BankCardPojo bankCardPojo = metadata.create(BankCardPojo.class);
            bankCardPojo.setId(bankCard.getId());
            bankCardPojo.setExpireDate(bankCard.getExpireDate());
            bankCardPojo.setNameOnCard(bankCard.getNameOnCard());
            bankCardPojo.setNumber(bankCard.getNumber());
            bankCardPojoList.add(bankCardPojo);
        }
        return bankCardPojoList;
    }

    @Override
    public void changeUserSessionLocale(String locale) {
        if (!loadingConfig.getAvailableLocales().contains(locale))
            locale = "ru";

        userSessionSource.getUserSession().setLocale(getLocale(locale));
    }

    @Override
    public void sendPasswordToUser(@NotNull String email) throws Exception {
        Validate.notNull(email);

        try {
            UserExt userExt = getUserByEmail(email);

            if (userExt == null)
                userExt = createUser(email);

            Integer otpCode = null;
            if (userExt.getOtpCode() == null) {
                otpCode = getRandomNumber();
                userExt.setOtpCode(otpCode);
                dataManager.commit(userExt);
            } else {
                otpCode = userExt.getOtpCode();
            }
            sendEmailToUserWithOtpCode(email, otpCode);


        } catch (Exception exception) {
            loggerService.saveLog("UserServiceBean.sendPasswordToUser", email, exception);
            throw new Exception("К админу");
        }
    }

    @Override
    public Boolean isOtpCodeValid(@NotNull String email, @NotNull Integer otpCode) {
        Validate.notNull(email);
        Validate.notNull(otpCode);

        UserExt userExt = getUserByEmail(email);
        return userExt.getOtpCode() != null && userExt.getOtpCode().equals(otpCode) ? true : false;

    }

    @Override
    public void renewPassword(@NotNull UserPojo userPojo) {

        Validate.notNull(userPojo);
        Validate.notNull(userPojo.getEmail());
        Validate.notNull(userPojo.getOtpCode());
        Validate.notNull(userPojo.getPassword());

        UserExt userExt = getUserByEmail(userPojo.getEmail());

        userExt.setOtpCode(null);
        userExt.setPassword(passwordEncryption.getPasswordHash(userExt.getId(), userPojo.getPassword()));
        dataManager.commit(userExt);
    }

    private void sendEmailToUserWithOtpCode(@NotNull String email, Integer otpCode) throws EmailException {
        String template = "kz/almaty/uniqcars/templates/restore_password.txt";

        Map<String, Serializable> params = new HashMap<>();
        params.put("email", email);
        params.put("otpCode", otpCode);

        emailNotificationService.sendEmailToUserFromTemplate("Одноразовый OTP code", template, email, params);
    }

    private Integer getRandomNumber() {
        Random random = new Random();
        int randomNumber = random.nextInt(1000);
        return randomNumber;
    }

    private UserExt createUser(String email) {
        UserExt userExt = dataManager.create(UserExt.class);
        userExt.setEmail(email);
        userExt.setLogin(email);
        userExt.setActive(true);
        userExt.setGroup(getStandardGroup());
        userExt.setPassword(passwordEncryption.generateRandomPassword());
        userExt.setPasswordEncryption(passwordEncryption.getHashMethod());

        String password = passwordEncryption.generateRandomPassword();
        userExt.setPasswordEncryption(passwordEncryption.getHashMethod());
        String passwordHash = passwordEncryption.getPasswordHash(userExt.getId(), password);
        userExt.setPassword(passwordHash);

        return userExt;
    }

    private UserExt getUserByEmail(String email) {
        return dataManager.load(UserExt.class)
                .query("select e from uniqcars_UserExt e where e.login = :login")
                .parameter("login", email)
                .optional().orElse(null);
    }

    private Locale getLocale(String locale) {
        return new Locale(locale);
    }

    private Group getStandardGroup() {
        return dataManager.load(Group.class).query(
                "select e from sec$Group e where e.name = 'Company'")
                .optional().orElse(null);
    }
}