package kz.almaty.uniqcars.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum Location implements EnumClass<String> {

    ALMATY("Almaty"),
    ASTANA("Astana"),
    SHYMKENT("Shymkent"),
    KARAGANDA("Karaganda"),
    TARAZ("Taraz"),
    PAVLODAR("Pavlodar"),
    URALSKE("Uralsk"),
    SEMEY("Semey"),
    AKTOBE("Aktobe"),
    ATYRAU("Atyrau"),
    KOSTANAY("Kostanay"),
    KYZYLORDA("Kyzylorda"),
    PETROPAVLOVSK("Petropavlovsk"),
    EKIBASTUZ("Ekibastuz"),
    RUDNY("Rudny"),
    TALDYKORGAN("Taldykorgan"),
    KOKSHETAU("Kokshetau");


    private String id;

    Location(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static Location fromId(String id) {
        for (Location at : Location.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}