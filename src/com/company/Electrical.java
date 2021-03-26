package com.company;


// Programmer "is-a" SoftwareStaff
// So Programmer inherits id, name, email and mobile from SoftwareStaff
public class Electrical extends Product {
    private String make;
    private String warrenty;



    public Electrical(String n, double c, String m, String w, int mId) {
        // Calling the Superclass constructor - SoftwareStaff
        // This constructor expects and ID, name, email, mobile and staffnumber passed in
        // Since we don't have an id yet we pass in -1 (ie. this programmer is not yet created in the database.)
        super(-1, n,c, mId);
        this.make = m;
        this.warrenty = w;
    }

    public Electrical(int id, String n, double c, String m, String w) {
        super(id,n,c);
        this.make = m;
        this.warrenty = w;

        // call to superclass constructor must be the first line in sub-class constructor.
        // super(id,n,c); don't have you call to the super class after the other assignments

    }

    public Electrical(int id, String n, double c, String m, String w, int managerId) {
        super(id,n,c, managerId);
        this.make = m;
        this.warrenty = w;

        // call to superclass constructor must be the first line in sub-class constructor.
        // super(id,n,c); don't have you call to the super class after the other assignments

    }


    public Electrical(int id,  String n, double c) {
        super(id, n, c);
    }

    public Electrical() {
        super();
    }


    @Override
    public void printDetailedReport() {
        System.out.println("******DETAILED PRODUCT REPORT **********");
        System.out.println("Item ID : " + super.getId());
        System.out.println("Item Name : " + super.getName());
        System.out.println("Item Cost : " + super.getCost());
        System.out.println("Make : " + make);
        System.out.println("Warrenty : " + warrenty);
        System.out.println("**************************************");
    }

    @Override
    public void printSummary() {
        System.out.println("********* SUMMARY ***************");
        System.out.println("Item ID : " + super.getId());
        System.out.println("Item Cost : " + super.getCost());
        System.out.println("Discount Cost : " + calcPay());
        System.out.println("********************************");
    }

    @Override
    public double VAT() {
        return 1.55;
    }

//    @Override
//    public double calcPay() {
//        // lets say they get paid monthly. So divide salary by 12
//        return cost / 12;
//    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getWarrenty() {
        return warrenty;
    }

    public void setWarrenty(String warrenty) {
        this.warrenty = warrenty;
    }

    @Override
    public String toString()
    {
//        System.out.println("\n\n****** ELECTRICAL PRODUCTS **********");
        // super.toString() calls the super class SoftwareStaff's toString method()
        // this returns a String for the SoftwareStaff instance variables which is concatenated to these other strings below.
        // then the whole string is returned to the calling program (in our case the test program in main().
        return "\n\nElectrical Product " + super.toString() + "\nMake : " + make + "\nWarrenty : " + warrenty; //+ "\nSalary " +  salary;
    }

    @Override
    public double calcPay() {
        // 25% discount
        return cost / 125;
    }
}
