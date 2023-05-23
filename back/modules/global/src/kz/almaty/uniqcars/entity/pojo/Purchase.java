package kz.almaty.uniqcars.entity.pojo;

import java.util.List;

public class Purchase {

    public Purchase(List<PurchaseUnit> purchaseUnits, ApplicationContext applicationContext) {
        this.intent = "CAPTURE";
        this.purchase_units = purchaseUnits;
        this.application_context = applicationContext;
    }

    private String intent;
    private List<PurchaseUnit> purchase_units;
    private ApplicationContext application_context;

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public List<PurchaseUnit> getPurchase_units() {
        return purchase_units;
    }

    public void setPurchase_units(List<PurchaseUnit> purchase_units) {
        this.purchase_units = purchase_units;
    }

    public ApplicationContext getApplication_context() {
        return application_context;
    }

    public void setApplication_context(ApplicationContext application_context) {
        this.application_context = application_context;
    }
}
