package kz.almaty.uniqcars.entity.pojo;

public class BreakDown {

    public BreakDown() {
        this.item_total = new ItemTotal();
    }

    private ItemTotal item_total;

    public ItemTotal getItem_total() {
        return item_total;
    }

    public void setItem_total(ItemTotal item_total) {
        this.item_total = item_total;
    }
}
