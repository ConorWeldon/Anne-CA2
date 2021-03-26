package com.company;

// Super Class - sub-classes will inherit id, name, email and mobile number.
// SoftwareStaff class - superclass that has common information for all Staff
// SoftwareStaff cannot be instantiated.

public abstract class Product implements Reports {

    public int id;
    public String name;
    public double cost;

    private int managerId;

    public Product(int id, String n, double c) {
        this.id = id;
        this.name = n;
        this.cost = c;

    }

    public Product(int id, String n, double c, int managerId) {
        this.id = id;
        this.name = n;
        this.cost = c;
        this.managerId = managerId;

    }

    public Product() {

    }

    // Define an abstract method here, forces any sub-class to implement it.
    public abstract double calcPay();

    public abstract double VAT();

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getManagerId() {   return managerId; }

    public void setManagerId(int managerId) { this.managerId = managerId;   }

    @Override
    //    now you know the toString() method overrides the toString() method in the Super-Super class Object
    // This method always returns a String, it's up to you to return a string that represents the object
    public String toString()
    {
        return "\nName : " + getName() + "\nID : " + id;
    }

    @Override
    public void printDetailedReport() {

    }


//    @Override
//    public double calcPay() {
//        // 25% discount
//        return cost / 125;
//    }

}
