package com.company.database;

import com.company.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductTableGateway {
    // Define all fields in the staff table
    //private static String TABLE_TEST = "test";
    private static String TABLE_SUPER = "supermarket";
    private static String TABLE_ELEC = "electrical";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_COST = "cost";
    private static final String COLUMN_MANAGER_ID = "managerId";
    private static final String COLUMN_SEASON = "season";
    private static final String COLUMN_RATING = "rating";
    private static final String COLUMN_MAKE = "make";
    private static final String COLUMN_WARRENTY = "warrenty";
    private static final String COLUMN_WARRENTYE = "warrenty";


    private Connection mConnection;

    public ProductTableGateway(Connection connection) {
        mConnection = connection;
    }

    public List<Product> getProduct() {
        String query;                   // the SQL query to execute
        String eQuery;                   // the SQL query to execute
        Statement stmt;                 // the java.sql.Statement object used to execute the SQL query
        ResultSet rs;                   // the java.sql.ResultSet representing the result of SQL query
        List<Product> productList;   // the java.util.List containing the Programmer objects created for each row

        String name, season, rating, warrenty, make;
        int id, managerId;
        double cost;

        Product product;


        query = "SELECT * FROM " + TABLE_SUPER;
        eQuery = "SELECT * FROM " + TABLE_ELEC;

        productList = new ArrayList<Product>();

        try {
            stmt = this.mConnection.createStatement();
            rs = stmt.executeQuery(query);

            // iterate through the result set, extracting the data from each row then creating either a Programmer or HourlyWorker and putting it into array List
            while (rs.next()) {
                id = rs.getInt(COLUMN_ID);
                name = rs.getString(COLUMN_NAME);
                season = rs.getString(COLUMN_SEASON);
                rating = rs.getString(COLUMN_RATING);

                warrenty = rs.getString(COLUMN_WARRENTY);
                cost = rs.getDouble(COLUMN_COST);
                managerId = rs.getInt(COLUMN_MANAGER_ID);


                productList.add(new SeasonalProduct(id, name, season, rating, warrenty, cost, managerId));

            }
        } catch (SQLException ex) {
            System.out.println("SeasonalProductTableGateway Line 56: " + ex);
        }

        try {
            stmt = this.mConnection.createStatement();
            rs = stmt.executeQuery(eQuery);

//            System.out.println("****** ELECTRICAL PRODUCTS **********");
            // iterate through the result set, extracting the data from each row then creating either a Programmer or HourlyWorker and putting it into array List
            while (rs.next()) {

                id = rs.getInt(COLUMN_ID);
                name = rs.getString(COLUMN_NAME);
                cost = rs.getDouble(COLUMN_COST);
                managerId = rs.getInt(COLUMN_MANAGER_ID);
                make = rs.getString(COLUMN_MAKE);
                warrenty = rs.getString(COLUMN_WARRENTYE);


                productList.add(new Electrical(id, name, cost, make, warrenty, managerId));

            }
        } catch (SQLException ex) {
            System.out.println("SeasonalProductTableGateway Line 56 2: " + ex);
        }


        // return the list of Products objects retrieved
        return productList;
    }

    public List<Product> getProductByManagerId(int mid) {
        String query;                   // the SQL query to execute
        String eQuery;                   // the SQL query to execute
        Statement stmt;                 // the java.sql.Statement object used to execute the SQL query
        ResultSet rs;                   // the java.sql.ResultSet representing the result of SQL query
        List<Product> productList;   // the java.util.List will contain the Product objects for the Manager

        String name, season, rating, warrenty, make;
        int id;
        double cost;


        query = "SELECT * FROM " + TABLE_SUPER + " WHERE " + COLUMN_MANAGER_ID + " = " + mid;
        eQuery = "SELECT * FROM " + TABLE_ELEC + " WHERE " + COLUMN_MANAGER_ID + " = " + mid;
        // for debugging purposes only.
        //System.out.println("SQL QUERY IS : " + query);


        productList = new ArrayList<Product>();

        try {
            stmt = this.mConnection.createStatement();
            rs = stmt.executeQuery(query);

            // iterate through the result set, extracting the data from each row then creating either a Seasonal Product or Electrical Product and putting it into array List
            while (rs.next()) {

                id = rs.getInt(COLUMN_ID);
                name = rs.getString(COLUMN_NAME);
                season = rs.getString(COLUMN_SEASON);
                rating = rs.getString(COLUMN_RATING);

                warrenty = rs.getString(COLUMN_WARRENTY);
                cost = rs.getDouble(COLUMN_COST);
                mid = rs.getInt(COLUMN_MANAGER_ID);


                productList.add(new SeasonalProduct(id, name, season, rating, warrenty, cost, mid));

            }
        } catch (SQLException ex) {
            System.out.println("SoftwareStaffTableGateway Line 56: " + ex);
        }

        try {
            stmt = this.mConnection.createStatement();
            rs = stmt.executeQuery(eQuery);

            // iterate through the result set, extracting the data from each row then creating either a Programmer or HourlyWorker and putting it into array List
            while (rs.next()) {

                id = rs.getInt(COLUMN_ID);
                name = rs.getString(COLUMN_NAME);
                cost = rs.getDouble(COLUMN_COST);
                make = rs.getString(COLUMN_MAKE);
                warrenty = rs.getString(COLUMN_WARRENTYE);
                mid = rs.getInt(COLUMN_MANAGER_ID);

                productList.add(new Electrical(id, name, cost, make, warrenty, mid));

            }
        } catch (SQLException ex) {
            System.out.println("SoftwareStaffTableGateway Line 56: " + ex);
        }

        // return the list of Product objects retrieved
        return productList;
    }

    // I return the primary key ID of the Product that was created
    public int createProduct(Product product) {

        String query = "";                   // the SQL query to execute
        PreparedStatement stmt;         // the java.sql.PreparedStatement object used to execute the SQL query
        int numRowsAffected;
        int generatedId;


        if (product.getClass() == SeasonalProduct.class) {
            // the required SQL INSERT statement with place holders for the values to be inserted into the database
            query = "INSERT INTO " + TABLE_SUPER + " (" +
                    COLUMN_NAME + ", " +
                    COLUMN_COST + ", " +
                    COLUMN_MANAGER_ID + ", " +

                    COLUMN_SEASON + ", " +
                    COLUMN_RATING + ", " +
                    COLUMN_WARRENTY +
                    ") VALUES (?, ?, ?, ?, ?, ?)";
        } else if (product.getClass() == Electrical.class) {

            query = "INSERT INTO " + TABLE_ELEC + " (" +
                    COLUMN_NAME + ", " +
                    COLUMN_COST + ", " +

                    COLUMN_MANAGER_ID + ", " +

                    COLUMN_MAKE + ", " +
                    COLUMN_WARRENTYE +
                    ") VALUES (?, ?, ?, ?, ?)";
        }

        try {
            // create a PreparedStatement object to execute the query and insert the values into the query
            stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getCost());
            stmt.setInt(3, product.getManagerId());


            // at compile time Product is a Product object, not a SeasonalProduct or Electrical Product
            // therefore stmts such as the one commented below will give you errors - uncomment them to see
            //softwareStaff.getSkills();
            // At runtime the object may "take on many forms" i.e. can be a Programmer or an HourlyWorker, This is known as polymorphism.
            // So at runtime we determine the object type for softwareStaff so we can get skills&salary or hourlyRate
            if (product.getClass() == Electrical.class) {
                //  TABLE_NAME = "electrical";
                // note...I created a default constructor in Programmer and the superclass which takes no parameters
                Electrical p1 = new Electrical();
                p1 = (Electrical) product;
                stmt.setString(4, p1.getMake());
                stmt.setString(5, p1.getWarrenty());
//                    stmt.setNull(7, 1);

                System.out.println(stmt.toString());

            } else if (product.getClass() == SeasonalProduct.class) {
                SeasonalProduct h1 = new SeasonalProduct();
                h1 = (SeasonalProduct) product;
                stmt.setString(4, h1.getSeason());
                stmt.setString(5, h1.getRating());
                stmt.setString(6, h1.getWarrenty());

                //System.out.println(stmt.toString());
            }

            //  execute the query and make sure that one and only one row was inserted into the database
            numRowsAffected = stmt.executeUpdate();

            // if numRowsAffected is 1 - that means the SQL insert inserted one row into the table, so the ID should have been auto-incremented and sent back here.
            // so assign this new ID to the ID.
            if (numRowsAffected == 1) {
                // if one row was inserted, retrieve the id that was assigned to that row in the database and ret
                ResultSet keys = stmt.getGeneratedKeys();
                keys.next();
                generatedId = keys.getInt(1);
                return generatedId;
            }

        } catch (SQLException e) {
            // remember to put sensible error logging into your code...
            //System.out.println(e);
        }

        // return the Product object created with the new ID, The calling product in the Model should check to ensure it is not null
        return -1;
    }

    public boolean updateSeasonalProducts(int id, String name, String season, String rating, String warrenty, double cost, int managerId) /*throws SQLException*/ {
        String query;
        PreparedStatement stmt;
        int numRowsAffected;

        query = "UPDATE supermarket SET name = (?), season = (?), rating = (?), warrenty = (?), cost = (?), managerId = (?) WHERE id = ?";
        try {
            stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name);
            stmt.setString(2, season);
            stmt.setString(3, rating);
            stmt.setString(4, warrenty);
            stmt.setDouble(5, cost);
            stmt.setDouble(6, managerId);

            stmt.setInt(7, id);

            numRowsAffected = stmt.executeUpdate();

            if (numRowsAffected == 1) {
                return true;
            }
        } catch (SQLException e) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in UpdateGateway : UpdateSeasonalProduct(), Check the SQL you have created to see where your error is", e);
        }
        return false;
    }

    public boolean updateElectricalProducts(int id, String name, double cost, String make, String warrenty, int managerId) /*throws SQLException*/ {
        String query;
        PreparedStatement stmt;
        int numRowsAffected;

        query = "UPDATE electrical SET name = (?), cost = (?), make = (?), warrenty = (?), managerId = (?) WHERE id = ?";
        try {
            stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name);
            stmt.setDouble(2, cost);
            stmt.setString(3, make);
            stmt.setString(4, warrenty);
            stmt.setDouble(5, managerId);

            stmt.setInt(6, id);

            numRowsAffected = stmt.executeUpdate();

            if (numRowsAffected == 1) {
                return true;
            }
        } catch (SQLException e) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in UpdateGateway : UpdateElectrical(), Check the SQL you have created to see where your error is", e);
        }
        return false;
    }

    public boolean deleteEProduct(int id) {

        int numRowsAffected;

        // the SQL query to execute
        String query = "DELETE FROM " + TABLE_ELEC + " WHERE " + COLUMN_ID + "= ?";

        try {
            PreparedStatement stmt;         // the java.sql.PreparedStatement object used to execute the SQL query

            // create a PreparedStatement object to execute the delete, insert the id into the ? in the prepared statement
            stmt = mConnection.prepareStatement(query);
            stmt.setInt(1, id);

            // Test to see if the Query string is correct. This can be deleted from the final submission, it's here to show you how to print out your SQL so you can manually check it.
            //System.out.println("\n\nTHE SQL LOOKS LIKE THIS " + stmt.toString() + "\n\n");

            //  execute the query and make sure that one and only one row was inserted into the database
            numRowsAffected = stmt.executeUpdate();
            // if a row was affected in the database - the row was deleted, so return true
            if (numRowsAffected == 1) {
                return true;
            }
        } catch (SQLException e) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in SeasonalProductTableGateway : deleteProduct(), Check the SQL you have created to see where your error is", e);
        }

        // if you get to here the SeasonalProduct was not deleted so return false
        return false;

    }

    public boolean deleteSProduct(int id) {

        int numRowsAffected;

        // the SQL query to execute
        String query = "DELETE FROM " + TABLE_SUPER + " WHERE " + COLUMN_ID + "= ?";


        try {
            PreparedStatement stmt;         // the java.sql.PreparedStatement object used to execute the SQL query

            // create a PreparedStatement object to execute the delete, insert the id into the ? in the prepared statement
            stmt = mConnection.prepareStatement(query);
            stmt.setInt(1, id);

            // Test to see if the Query string is correct. This can be deleted from the final submission, it's here to show you how to print out your SQL so you can manually check it.
            //System.out.println("\n\nTHE SQL LOOKS LIKE THIS " + stmt.toString() + "\n\n");

            //  execute the query and make sure that one and only one row was inserted into the database
            numRowsAffected = stmt.executeUpdate();
            // if a row was affected in the database - the row was deleted, so return true
            if (numRowsAffected == 1) {
                return true;
            }
        } catch (SQLException e) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in SeasonalProductTableGateway : deleteProduct(), Check the SQL you have created to see where your error is", e);
        }

        // if you get to here the SeasonalProduct was not deleted so return false
        return false;

    }

    public boolean assignSeasonalProducts(int id, int managerId) /*throws SQLException*/ {
        String query;
        PreparedStatement stmt;
        int numRowsAffected;

        query = "UPDATE supermarket SET managerId = (?) WHERE id = ?";
        try {
            stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setDouble(1, id);

            stmt.setInt(2, managerId);

            numRowsAffected = stmt.executeUpdate();

            if (numRowsAffected == 1) {
                return true;
            }
        } catch (SQLException e) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in assignGateway : assignSeasonalProduct(), Check the SQL you have created to see where your error is", e);
        }
        return false;
    }

    public boolean assignElectricalProducts(int id, int managerId) /*throws SQLException*/ {
        String query;
        PreparedStatement stmt;
        int numRowsAffected;

        query = "UPDATE electrical SET managerId = (?) WHERE id = ?";
        try {
            stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setDouble(1, managerId);

            stmt.setInt(2, id);

            numRowsAffected = stmt.executeUpdate();

            if (numRowsAffected == 1) {
                return true;
            }
        } catch (SQLException e) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in assignGateway : assignElectrical(), Check the SQL you have created to see where your error is", e);
        }
        return false;
    }

    public boolean deleteManager(int id) {

        int numRowsAffected;

        // the SQL query to execute
        String query = "DELETE FROM manager WHERE " + COLUMN_ID + "= ?";


        try {
            PreparedStatement stmt;         // the java.sql.PreparedStatement object used to execute the SQL query

            // create a PreparedStatement object to execute the delete, insert the id into the ? in the prepared statement
            stmt = mConnection.prepareStatement(query);
            stmt.setInt(1, id);

            // Test to see if the Query string is correct. This can be deleted from the final submission, it's here to show you how to print out your SQL so you can manually check it.
            //System.out.println("\n\nTHE SQL LOOKS LIKE THIS " + stmt.toString() + "\n\n");

            //  execute the query and make sure that one and only one row was inserted into the database
            numRowsAffected = stmt.executeUpdate();
            // if a row was affected in the database - the row was deleted, so return true
            if (numRowsAffected == 1) {
                return true;
            }
        } catch (SQLException e) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in SeasonalProductTableGateway : deleteProduct(), Check the SQL you have created to see where your error is", e);
        }

        // if you get to here the SeasonalProduct was not deleted so return false
        return false;

    }

    public boolean reAssignSeasonalProductManager(int id, int managerId) /*throws SQLException*/ {
        String query;
        PreparedStatement stmt;
        int numRowsAffected;

        query = "UPDATE supermarket SET managerId = (?) WHERE managerId = ?";
        try {
            stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setDouble(1, id);

            stmt.setInt(2, managerId);

            numRowsAffected = stmt.executeUpdate();

            if (numRowsAffected == 1) {
                return true;
            }
        } catch (SQLException e) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in re-assignGateway : re-assignSeasonalProduct(), Check the SQL you have created to see where your error is", e);
        }
        return false;
    }

    public boolean reAssignElectricalProductManager(int id, int managerId) /*throws SQLException*/ {
        String query;
        PreparedStatement stmt;
        int numRowsAffected;

        query = "UPDATE electrical SET managerId = (?) WHERE managerId = ?";
        try {
            stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setDouble(1, id);

            stmt.setInt(2, managerId);

            numRowsAffected = stmt.executeUpdate();

            if (numRowsAffected == 1) {
                return true;
            }
        } catch (SQLException e) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in re-assignGateway : re-assignSeasonalProduct(), Check the SQL you have created to see where your error is", e);
        }
        return false;
    }

    // Get Product by ID
    public Product getProductSByID(int id) {
        String query;                   // the SQL query to execute
        Statement stmt;                 // the java.sql.Statement object used to execute the SQL query
        ResultSet rs;                   // the java.sql.ResultSet representing the result of SQL query


        String name, season, rating, warrenty, make;
        int managerId;
        double cost;


        Product Sp = null;                   // a Product object created from a row in the result of the query


        //query = "SELECT * FROM " + TABLE_NAME;
        // The WHERE clause is important as the table hold both programmers and hourlyworker.
        query = "SELECT * FROM " + TABLE_SUPER + " WHERE id = " + id;

        // This print if for debugging purposes only.
        //System.out.println(query);

        try {
            stmt = this.mConnection.createStatement();
            rs = stmt.executeQuery(query);

            // if manager id exists it should be returned otherwise we'll assume there is no manager with this id.
            if (rs.next()) {
                id = rs.getInt(COLUMN_ID);
                name = rs.getString(COLUMN_NAME);
                season = rs.getString(COLUMN_SEASON);
                rating = rs.getString(COLUMN_RATING);

                warrenty = rs.getString(COLUMN_WARRENTY);
                cost = rs.getDouble(COLUMN_COST);
                managerId = rs.getInt(COLUMN_MANAGER_ID);

                Sp = new SeasonalProduct(id, name, season, rating, warrenty, cost, managerId);
            }
        } catch (SQLException e) {
            System.out.println("Error caught:");
            System.out.println("\n" + e.getMessage());
            System.out.println("\tThrown in file " + e.getStackTrace()[0].getFileName());
            System.out.println("\tThrown on line " + e.getStackTrace()[0].getLineNumber());
        }


        // return the manager (it will be null if the manager did not exist
        return Sp;
    }

    // Get Product by ID
    public Product getProductEByID(int id) {
        String query;                   // the SQL query to execute
        Statement stmt;                 // the java.sql.Statement object used to execute the SQL query
        ResultSet rs;                   // the java.sql.ResultSet representing the result of SQL query


        String name, season, rating, warrenty, make;
        int managerId;
        double cost;


        Product Sp = null;                   // a Product object created from a row in the result of the query


        //query = "SELECT * FROM " + TABLE_NAME;
        // The WHERE clause is important as the table hold both programmers and hourlyworker.
        query = "SELECT * FROM " + TABLE_ELEC + " WHERE id = " + id;

        // This print if for debugging purposes only.
        //System.out.println(query);

        try {
            stmt = this.mConnection.createStatement();
            rs = stmt.executeQuery(query);

            // if manager id exists it should be returned otherwise we'll assume there is no manager with this id.
            if (rs.next()) {
                id = rs.getInt(COLUMN_ID);
                name = rs.getString(COLUMN_NAME);
                cost = rs.getDouble(COLUMN_COST);
                managerId = rs.getInt(COLUMN_MANAGER_ID);
                make = rs.getString(COLUMN_MAKE);
                warrenty = rs.getString(COLUMN_WARRENTYE);


                Sp = new Electrical(id, name, cost, make, warrenty, managerId);
            }
        } catch (SQLException e) {
            System.out.println("Error caught:");
            System.out.println("\n" + e.getMessage());
            System.out.println("\tThrown in file " + e.getStackTrace()[0].getFileName());
            System.out.println("\tThrown on line " + e.getStackTrace()[0].getLineNumber());
        }


        // return the manager (it will be null if the manager did not exist
        return Sp;
    }
}

