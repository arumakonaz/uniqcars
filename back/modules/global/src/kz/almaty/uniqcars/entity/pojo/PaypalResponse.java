package kz.almaty.uniqcars.entity.pojo;

import java.util.List;

public class PaypalResponse {

    List<PaypalLinks> links;
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PaypalLinks> getLinks() {
        return links;
    }

    public void setLinks(List<PaypalLinks> links) {
        this.links = links;
    }
}
