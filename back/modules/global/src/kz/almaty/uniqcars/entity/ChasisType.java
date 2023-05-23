package kz.almaty.uniqcars.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum ChasisType implements EnumClass<String> {

    HATCHBACK("hatchback"),
    SEDAN("sedan"),
    LIFTBACK("liftback"),
    WAGON("wagon"),
    COUPE("coupe"),
    CROSSOVER("crossover"),
    SUV("suv"),
    PICKUP("pickup"),
    BUS("bus"),
    VAN("van"),
    HARDTOP_CONVERTIBLE("hardtop_convertible"),
    SOFT_TOP_CONVERTIBLE("soft_top_convertible");

    private String id;

    ChasisType(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static ChasisType fromId(String id) {
        for (ChasisType at : ChasisType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}