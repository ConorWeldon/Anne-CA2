package com.company;

public class SeasonalProduct extends Product {

    private String season;
    private String rating;
    private String warrenty;

    // we have added the managerId as a parameter, representing the one-many relationhsip with manager.
    // it is passed to the superclass constructor
    public SeasonalProduct(String n, String s, String r,  String w, double c, int mId){
        super(-1, n, c, mId);
        this.season = s;
        this.rating = r;
        this.warrenty = w;
    }

    public SeasonalProduct(int id, String n, String s, String r,  String w, double c, int managerId){
        super(id, n, c, managerId);
        this.season = s;
        this.rating = r;
        this.warrenty = w;
    }

    public SeasonalProduct() {
        super();
    }

    @Override
    public void printDetailedReport() {
        System.out.println("******DETAILED PRODUCT REPORT **********");
        System.out.println("Item ID : " + super.getId());
        System.out.println("Item Name : " + super.getName());
        System.out.println("Item Cost : " + super.getCost());
        System.out.println("Season : " + season);
        System.out.println("Rating : " + rating);
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

//    @Override
//    public double calcPay() {
//        // 25% discount
//        return cost / 125;
//    }

//    public double getHourlyRate() {
//        return hourlyRate;
//    }

    @Override
    public double VAT() {
        return 1.25;
    }


    public int getId()
    {
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

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }


    public String getWarrenty() {
        return warrenty;
    }

    public void setWarrenty(String warrenty) {
        this.warrenty = warrenty;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }


    @Override

    public String toString() {
//        System.out.println("\n\n****** SEASONAL PRODUCTS **********");
        return "\n\nSeasonalProduct " + super.toString() + "\nCost : " + cost;
    }

    @Override
    public double calcPay() {
        // 25% discount
        return cost / 125;
    }
}

