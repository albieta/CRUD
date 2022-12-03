package arg.crud.entity;

import java.util.Objects;

public class Product {
    String productId;
    String productName;
    double price;
    int numSales;

    public Product(){}

    public Product(String productId, String productName, double price, int numSales){
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.numSales = numSales;
    }
    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getNumSales() {
        return this.numSales;
    }

    public void setNumSales(int numSales) {
        this.numSales = numSales;
    }

    public void sold(int quantity) {
        this.numSales = numSales + quantity;
    }

    public boolean isNull(){
        return(Objects.equals(productId, ""));
    }
}
