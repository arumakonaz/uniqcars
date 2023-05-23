package kz.almaty.uniqcars.entity.pojo;

import com.haulmont.chile.core.annotations.MetaProperty;

import java.io.Serializable;

public class EmailPojo implements Serializable {
    public EmailPojo() {
    }

    public EmailPojo(String phone, String from, String name, String email, String body) {
        this.phone = phone;
        this.from = from;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    private String phone;
    private String from;
    private String name;
    private String email;
    private String body;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
