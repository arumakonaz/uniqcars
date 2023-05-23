package kz.almaty.uniqcars.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum EngineType implements EnumClass<String> {

    GASOLINE("GASOLINE"),
    DIESEL("DIESEL"),
    HYBRID("HYBRID"),
    ELECTRO("ELECTRO");

    private String id;

    EngineType(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static EngineType fromId(String id) {
        for (EngineType at : EngineType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}