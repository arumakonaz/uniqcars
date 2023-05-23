package kz.almaty.uniqcars.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum Transmission implements EnumClass<String> {

    MANUAL("MANUAL"),
    AUTO("AUTO");

    private String id;

    Transmission(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static Transmission fromId(String id) {
        for (Transmission at : Transmission.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}