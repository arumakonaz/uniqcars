package kz.almaty.uniqcars.entity.pojo;

import java.util.List;

public class PurchaseUnit {
    public PurchaseUnit(List<PurchaseItem> items) {
        this.items = items;
        this.amount = new Amount();
    }

    private List<PurchaseItem> items;
    private Amount amount;

    public List<PurchaseItem> getItems() {
        return items;
    }

    public void setItems(List<PurchaseItem> items) {
        this.items = items;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }
}
