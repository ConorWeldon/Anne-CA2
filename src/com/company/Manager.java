package com.company;

import java.util.ArrayList;
import java.util.List;

public class Manager {

    // instance variables
    private int managerId;
    private String name;
    private int officeNumber;

    private List<Product> productList;

    //make sure you have a constructor that does not have an ID, for when you are creating the object for the first time
    // the database will assign the id
    public Manager(String name, int officeNumber){
        this.name = name;
        this.officeNumber = officeNumber;
    }


    public Manager(int id, String name, int officeNumber){
        this.managerId = id;
        this.name = name;
        this.officeNumber = officeNumber;

        // product is an empty list of products to start with.
        productList = new ArrayList();
    }

    //@Override
    public String toStringSolo() {
        return "Manager Details " +
                "manager Id : " + managerId +
                ", name : '" + name + '\'' +
                ", officeNumber : " + officeNumber +
                '}';
    }

    // add a new Product member to the managers list of products
    // remember we cannot instantiate Staff objects as the class is abstract.
    // Instead Seasonal Product or Electrical Product objects are passed in here.
    public void addProducttoManager(Product s){
        productList.add(s);
    }

    // remove a SoftwareStaff member from the mangers list of staff
    public void removeProductFromManager(Product s){
        productList.remove(s);
    }


    public int getManagerId() {
        return managerId;
    }


    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getOfficeNumber() {
        return officeNumber;
    }


    public void setOfficeNumber(int officeNumber) {
        this.officeNumber = officeNumber;
    }


    public List <Product> getProductList() {
        return productList;
    }


    public void setProductList(List <Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "Manager Details " +
                "manager Id : " + managerId +
                ", name : '" + name + '\'' +
                ", officeNumber : " + officeNumber +
                "\n LIST OF MANAGERS PRODUCTS "
                 + productList +
                '}';
    }

}
