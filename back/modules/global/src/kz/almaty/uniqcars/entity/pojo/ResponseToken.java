package kz.almaty.uniqcars.entity.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseToken {

    String access_token;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
