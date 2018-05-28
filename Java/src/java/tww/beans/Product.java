package tww.beans;

import java.math.BigDecimal;

public class Product {
   int prod_id;
   String name;
   String description;
   String frontview;
   String sideview;
   String backview;
   double price;
   String com;
   String cat;
   int count;
    public Product(int prod_id, String name, String description, String frontview,String sideview,String backview, double price, String com, String cat, int count) {
        this.prod_id = prod_id;
        this.name = name;
        this.description = description;
        this.frontview = frontview;
        this.sideview = sideview;
        this.backview = backview;
        this.price = price;
        this.com = com;
        this.cat = cat;
        this.count = count;
    }

    public int getProdId() {
        return prod_id;
    }

    public void setProdId(int prod_id) {
        this.prod_id = prod_id;
    }
    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getName() {
        return name;
    }

    public void setName(String category) {
        this.name = name;
    }
     public int getCount() {
        return count;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getSideview() {
        if(sideview==null){
            return "not";
        }
        return sideview;
    }

    public void setSideview(String sideview) {
        this.sideview = sideview;
    }

    
    public String getBackview() {
        if(backview==null){
            return "not";
        }
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


    public String getcom() {
        return com;
    }

    public void setcom(String com) {
        this.com = com;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
