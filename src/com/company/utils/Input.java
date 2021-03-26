package com.company.utils;

import java.util.Scanner;

import com.company.Manager;
import com.company.Electrical;
import com.company.Model;
import com.company.SeasonalProduct;

/**
 *
 * @author Conor Weldon
 */
public class Input {
    static Model model = Model.getInstance();


    // Called to read in a new Product when Create A Product is chosen by user
    public static Electrical readElectrical() {
        String name, make, warrenty;
        int mId;
        double cost;
        Scanner keyboard = new Scanner(System.in);


        // ask the user for all the Product data except the ID - ID is automatically
        // created in the database when you are creating something for the first time
        System.out.print("Enter name : ");
        name = keyboard.nextLine();

        System.out.print("Enter Cost : ");
        cost = keyboard.nextDouble();
        keyboard.nextLine();

        System.out.print("Enter make : ");
        make = keyboard.nextLine();

        System.out.print("Enter warrenty : ");
        warrenty = keyboard.nextLine();

        // Asking for the manager ID - this needs to exist in the Manager table otherwise it will crash
        // I don't check here, I'll leave this for you to figure out!
        System.out.print("Enter Manger ID : ");
        mId = keyboard.nextInt();
        keyboard.nextLine();


        // Create the Product object p
        Electrical p =
                new Electrical(name, cost, make, warrenty, mId);


        return p;
    }

    // Called to read in a new Product when Create A Product is chosen by user
    public static SeasonalProduct readSeasonalProduct() {
        String name, season, rating, warrenty;
        int mId;
        double cost;
        Scanner keyboard = new Scanner(System.in);


        // ask the user for all the Product data except the ID - ID is automatically
        // created in the database when you are creating something for the first time

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

        // Asking for the manager ID - this needs to exist in the Manager table otherwise it will crash
        // I don't check here, I'll leave this for you to figure out!
        System.out.print("Enter Manger ID : ");
        mId = keyboard.nextInt();
        keyboard.nextLine();


        // Create the Seasonal Product object Sp
        SeasonalProduct Sp =
                new SeasonalProduct(name, season, rating, warrenty, cost, mId);


        return Sp;
    }

    public static Manager readManager() {
        String name;
        int office;

        Scanner keyboard = new Scanner(System.in);

        // ask the user for all the Product data except the ID - ID is automatically
        // created in the database when you are creating something for the first time
        System.out.println("Enter manager name : ");
        name = keyboard.nextLine();

        System.out.println("Enter Office Number : ");
        office = keyboard.nextInt();
        keyboard.nextLine();

        return (new Manager(name, office));
    }

    // Called to read in a new Product when Delete A Product is chosen by user
    public static int deleteElectrical() {
        Scanner keyboard = new Scanner(System.in);


        System.out.print("Enter the ID of the Electrical Product to delete:");
        int id = Integer.parseInt(keyboard.nextLine());


      return id;
    }


    // Called to read in a new Product when Delete A Product is chosen by user
    public static int deleteSeasonalProduct() {
        Scanner keyboard = new Scanner(System.in);


        System.out.print("Enter the ID of the Seasonal Product to delete:");
        int id = Integer.parseInt(keyboard.nextLine());

       return id;
    }

    // Called to read in a new Product when Create Product is chosen by user
    public static int deleteManager() {
        Scanner keyboard = new Scanner(System.in);


        System.out.print("Enter the ID of the Manager you Wish to delete:");
        int id = Integer.parseInt(keyboard.nextLine());

        return id;
    }
}

