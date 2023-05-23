package kz.almaty.uniqcars.entity.pojo;

public class UnitAmount {
    public UnitAmount() {
    }

    private String currency_code = "USD";
    private String value = "0.05";

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
