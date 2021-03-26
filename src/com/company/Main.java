package com.company;


import com.company.utils.Input;

import java.util.List;
import java.util.Scanner;

public class Main {

    static Model model;
    static Scanner keyboard = new Scanner(System.in);


    public static void main(String[] args) {
        //   keyboard = new Scanner(System.in);
        model = Model.getInstance();
        int opt;


        /*************************************************
         ************* DISPLAY MENU **********************
         ***********************************************/
        do {
            System.out.println("\n***********MENU************");

            System.out.println("1. Create a Product");
            System.out.println("2. Read all Product");
            System.out.println("3. Get Product By Id");
            System.out.println("4. Update a Product");
            System.out.println("5. Delete a Product");

            System.out.println("6. View Manager by Id");
            System.out.println("7. View all Managers");
            System.out.println("8. Create A New Manager");
            System.out.println("9. Delete A Manager");
            System.out.println("10. Assign Product to existing Manager...");


            System.out.println("11. Exit");
            System.out.println("**************************");
            System.out.println();

            System.out.print("Enter option: ");
            String line = keyboard.nextLine();
            opt = Integer.parseInt(line);


            /***********************************************
             ****** DECIDE WHAT OPTION USER CHOSE***********
             ***********************************************/
            switch (opt) {

                case 1: {
                    createProduct();
                    break;
                }

                case 2: {
                    viewProduct();
                    break;
                }

                case 3: {
                    viewProductByID();
                    break;
                }

                case 4: {
                    updateSeasonalProducts();
                    break;
                }

                case 5: {
                    deleteProduct();
                    break;
                }

                case 6: {
                    viewManagerbyId();
                    break;
                }
                case 7: {
                    viewAllManagers();
                    break;
                }
                case 8: {
                    createNewManager();
                    break;
                }

                case 9: {
                    deleteManager();
                    break;
                }

                case 10: {
                    assignSeasonalProducts();
                    break;
                }

            }
        }
        while (opt != 9);
        System.out.println("Goodbye");
    } // END OF MAIN()

    // Creates either a Electrical Product or Seasonal Product
    private static void createProduct() {
        Product product = null;

        System.out.println("\nWhat do you want to create??");
        System.out.println("1. Electrical");
        System.out.println("2. Seasonal Product");

        int choice = Integer.parseInt(keyboard.nextLine());

        switch (choice) {
            case 1: {
                product = Input.readElectrical();
                int generatedId = model.createProduct(product);
                System.out.println("Product created with Id " + generatedId);
                break;
            }
            case 2: {
                product = Input.readSeasonalProduct();
                int generatedId = model.createProduct(product);
                System.out.println("Product created with Id " + generatedId);
                break;
            }


        }

    }

    private static void createNewManager() {
        Manager m = Input.readManager();

        if (model.createManager(m))
            System.out.println("Success : manager Added to DB");
        else
            System.out.println("Oh Ohh Something went wrong");

    }

    private static void viewManagerbyId() {
        System.out.println("Enter the id for the manager...");
        int id = keyboard.nextInt();
        Manager m = null;
        //consume the new line that will come after typing in the ID integer
        keyboard.nextLine();

        System.out.println("Do you also want to view the staff for that Manager? y/n");
        if (keyboard.nextLine().equalsIgnoreCase("y")) {
            m = model.viewManager(id);
            System.out.println("************PRINTING MANAGER DETAILS **************\n\n" + m.toString());
        } else {
            m = model.viewManagerSolo(id);
            System.out.println("************PRINTING MANAGER DETAILS **************\n\n" + m.toStringSolo());
        }
    }

    private static void viewAllManagers() {
        List<Manager> managerList = model.viewAllManagerSolo();
        for (Manager manager : managerList) {
            System.out.println("Name: " + manager.toStringSolo());
        }
    }


    private static void viewProduct() {
        System.out.println("\nDo you want to see a Detailed Report of Products or Just a Summary???");
        System.out.println("1. Detailed");
        System.out.println("2. Summary");

        int choice = Integer.parseInt(keyboard.nextLine());

        switch (choice) {
            case 1: {
                List<Product> productList = model.viewProduct();
                for (Product product : productList) {
                    //System.out.println("Name: " + product.toString());
                    product.printDetailedReport();
                }
                break;
            }
            case 2: {
                List<Product> productList = model.viewProduct();
                for (Product product : productList) {
                    //System.out.println("Name: " + product.toString());
                    product.printSummary();
                }
                break;
            }


        }
    }

    private static void updateSeasonalProducts() {
        Product p = null;

        String name, season, rating, warrenty, make;

        int id, managerId;
        double cost;

        System.out.println("Press 1. to Update Seasonal Product");
        System.out.println("Press 2. to Update Electrical");
        if (keyboard.nextLine().equalsIgnoreCase("1")) {

            System.out.println("You may leave an Area Blank if you do not wish to give it a Value\n");

            System.out.println("Enter the id of the Seasonal Product: ");
            id = keyboard.nextInt();
            keyboard.nextLine();

            System.out.println("Enter the name of the Seasonal Product: ");
            name = keyboard.nextLine();

            System.out.println("Enter the season of the Seasonal Product: ");
            season = keyboard.nextLine();

            System.out.println("Enter the rating of the Seasonal Product: ");
            rating = keyboard.nextLine();

            System.out.println("Enter the warrenty of the Seasonal Product: ");
            warrenty = keyboard.nextLine();

            System.out.println("Enter the cost of the Seasonal Product: ");
            cost = keyboard.nextDouble();
            keyboard.nextLine();

            System.out.println("Enter the manager id of the Seasonal Product: ");
            managerId = keyboard.nextInt();
            keyboard.nextLine();

            if (model.updateSeasonalProducts(id, name, season, rating, warrenty, cost, managerId)) {
                System.out.println("\n***** Seasonal Product has been Updated *****");
            } else {
                System.out.println("\n***** Seasonal Product has not been Updated *****");
            }
        } else {

            System.out.println("You may leave an Area Blank if you do not wish to give it a Value\n");

            System.out.println("Enter the id of the Electrical Product: ");
            id = keyboard.nextInt();
            keyboard.nextLine();

            System.out.println("Enter the name of the Electrical Product: ");
            name = keyboard.nextLine();

            System.out.println("Enter the cost of the Electrical Product: ");
            cost = keyboard.nextDouble();
            keyboard.nextLine();

            System.out.println("Enter the make of the Electrical Product: ");
            make = keyboard.nextLine();

            System.out.println("Enter the warrenty of the Electrical Product: ");
            warrenty = keyboard.nextLine();

            System.out.println("Enter the manager id of the Electrical Product: ");
            managerId = keyboard.nextInt();
            keyboard.nextLine();

            if (model.updateElectricalProducts(id, name, cost, make, warrenty, managerId)) {
                System.out.println("\n***** Electrical Product has been Updated *****");
            } else {
                System.out.println("\n***** Electrical Product has not been Updated *****");
            }
        }
    }

    // Deletes the Seasonal Product or Electrical Product by id.
    private static void deleteProduct() {
        Product Sp;

        System.out.println("\nWhat table do you want to delete from??");
        System.out.println("1. Electrical");
        System.out.println("2. Seasonal Product");

        int choice = Integer.parseInt(keyboard.nextLine());

        switch (choice) {
            case 1: {

                int id = Input.deleteElectrical();

                Sp = model.viewProductEByID(id);
                System.out.println("************PRINTING PRODUCT TO BE DELETED **************" + Sp.toString());
                System.out.println("\nIs this the Product you wish to Delete?? (y/n)");
                if (keyboard.nextLine().equalsIgnoreCase("y")) {

                    if (model.deleteEProduct(id)) {
                        System.out.println("\nElectrical Product deleted");
                    } else {
                        System.out.println("\nElectrical Product not deleted, check your database to see if a Product with this ID actually exists");
                    }
                    break;
                } else {

                    System.out.println("We Have Cancled the Proccess");
                }
            }
            case 2: {

                int id = Input.deleteSeasonalProduct();

                Sp = model.viewProductSByID(id);
                System.out.println("************PRINTING PRODUCT TO BE DELETED **************" + Sp.toString());
                System.out.println("\nIs this the Product you wish to Delete?? (y/n)");

                if (keyboard.nextLine().equalsIgnoreCase("y")) {

                    if (model.deleteSProduct(id)) {
                        System.out.println("\nSeasonal Product deleted");
                    } else {
                        System.out.println("\nSeasonal Product not deleted, check your database to see if a Product with this ID actually exists");
                    }
                    break;
                } else {

                    System.out.println("We Have Cancled the Proccess");
                }
            }
        }
    }

    private static void assignSeasonalProducts() {

        int id, managerId;

        System.out.println("Press 1. to Assign Seasonal Product");
        System.out.println("Press 2. to Assign Electrical");
        if (keyboard.nextLine().equalsIgnoreCase("1")) {

            System.out.println("Enter the id of the Seasonal Product: ");
            id = keyboard.nextInt();
            keyboard.nextLine();

            System.out.println("Enter the manager id of the Seasonal Product: ");
            managerId = keyboard.nextInt();
            keyboard.nextLine();

            if (model.assignSeasonalProducts(id, managerId)) {
                System.out.println("\n***** Seasonal Product has been Assigned A Manager *****");
            } else {
                System.out.println("\n***** Seasonal Product has not been Assigned A Manager *****");
            }
        } else {

            System.out.println("Enter the id of the Electrical Product: ");
            id = keyboard.nextInt();
            keyboard.nextLine();

            System.out.println("Enter the manager id of the Electrical Product: ");
            managerId = keyboard.nextInt();
            keyboard.nextLine();

            if (model.assignElectricalProducts(id, managerId)) {
                System.out.println("\n***** Electrical Product has been Assigned A Manager *****");
            } else {
                System.out.println("\n***** Electrical Product has not been Assigned A Manager *****");
            }
        }
    }

    // Deletes the Manager by id.
    private static void deleteManager() {

        Manager m;

        int id, managerId;

        System.out.println("Before we delete a Manager, we need to Re-Assign his products");
        System.out.println("Enter the id you wish to Re-Assign the Managers Products to: ");
        id = keyboard.nextInt();
        keyboard.nextLine();

        System.out.println("Enter the manager id you wish to delete");
        managerId = keyboard.nextInt();
        keyboard.nextLine();

        if (!model.reAssignSeasonalProductManager(id, managerId)) {
            System.out.println("\nThere were no Supermarket Products Associated with this Manager");
        }

        if (!model.reAssignElectricalProductManager(id, managerId)) {
            System.out.println("\nThere were no Electrical Products Associated with this Manager");
        }

        m = model.viewManagerSolo(id);
        System.out.println("\n" + m.toStringSolo());
        System.out.println("\nIs this the Manager you wish to Delete?? (y/n)");

        if (keyboard.nextLine().equalsIgnoreCase("y")) {

            if (model.deleteManager(managerId)) {
                System.out.println("\nManager deleted");
            } else {
                System.out.println("\nWe Were unable to Delete the Manager");
            }
        } else {

            System.out.println("We Have Cancled the Proccess");
        }

    }

    private static void viewProductByID() {
        System.out.println("Press 1. to Find A Seasonal Product");
        System.out.println("Press 2. to Find A Electrical");
        if (keyboard.nextLine().equalsIgnoreCase("1")) {

            System.out.println("Enter the id for the Product...");
            int id = keyboard.nextInt();
            Product p = null;
            //consume the new line that will come after typing in the ID integer
            keyboard.nextLine();

            System.out.println("Do you to see the VAT of the Product?? y/n");
            if (keyboard.nextLine().equalsIgnoreCase("y")) {
                p = model.viewProductSByID(id);
                System.out.println("************PRINTING PRODUCT DETAILS **************\n" + p.toString() + p.getCost() * p.VAT());
            } else {
                p = model.viewProductSByID(id);
                System.out.println("************PRINTING PRODUCT DETAILS **************\n" + p.toString());
            }
        } else {
            System.out.println("Enter the id for the Product...");
            int id = keyboard.nextInt();
            Product p = null;
            //consume the new line that will come after typing in the ID integer
            keyboard.nextLine();

            System.out.println("Do you to see the VAT of the Product?? y/n");
            if (keyboard.nextLine().equalsIgnoreCase("y")) {
                p = model.viewProductEByID(id);
                System.out.println("************PRINTING PRODUCT DETAILS **************\n" + p.toString() + "\nCost : " + p.getCost() * p.VAT());
            } else {
                p = model.viewProductEByID(id);
                System.out.println("************PRINTING PRODUCT DETAILS **************\n" + p.toString() + "\nCost : " + p.getCost());
            }
        }
    }
}
