package kz.almaty.uniqcars.entity.pojo;

public class PurchaseItem {

    public PurchaseItem(String name) {
        this.name = name;
        this.unit_amount = new UnitAmount();
    }

    private String name;
    private String description = "";
    private Integer quantity = 1;
    private UnitAmount unit_amount;

}
