package webtek.demo.beans;

import java.math.BigDecimal;

public class Product {
   String prod_id;
   String name;
   String description;
   String frontview;
   String sideview;
   String backview;
   BigDecimal com_id;
    public Product(String prod_id, String name, String description, String frontview,String sideview,String backview, BigDecimal com_id) {
        this.prod_id = prod_id;
        this.name = name;
        this.description = description;
        this.frontview = frontview;
        this.sideview = sideview;
        this.backview = backview;
        this.com_id = com_id;
    }

    public String getProdId() {
        return prod_id;
    }

    public void setProdId(String prod_id) {
        this.prod_id = prod_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String category) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getSideview() {
        return sideview;
    }

    public void setSideview(String sideview) {
        this.sideview = sideview;
    }

    public String getBackview() {
        return backview;
    }

    public void setBackview(String backview) {
        this.backview = backview;
    }

    public String getFrontview() {
        return frontview;
    }

    public void setFrontview(String frontview) {
        this.frontview = frontview;
    }


    public BigDecimal getcom_id() {
        return com_id;
    }

    public void setcom_id(BigDecimal price) {
        this.com_id = com_id;
    }
}
