package kz.almaty.uniqcars.entity.pojo;

public class Amount {

    public Amount() {
        this.breakdown = new BreakDown();
    }

    private String currency_code = "USD";
    private String value = "0.05";
    private BreakDown breakdown;

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
