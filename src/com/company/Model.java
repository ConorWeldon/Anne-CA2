package com.company;

import com.company.database.DBConnection;
import com.company.database.ManagerTableGateway;
import com.company.database.ProductTableGateway;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Model {

    private static Model instance = null;

    public static synchronized Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }


    private List<Product> productList;
    private List<Manager> managerList;
    private ProductTableGateway pGateway;
    private ManagerTableGateway mGateway;


    private Model() {

        try {
            Connection conn = DBConnection.getInstance();
            this.pGateway = new ProductTableGateway(conn);
            this.mGateway = new ManagerTableGateway(conn);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public List viewProduct() {
        this.productList = this.pGateway.getProduct();
        return productList;
    }

    public Product viewProductSByID(int id) {
        Product Sp = pGateway.getProductSByID(id);
        return Sp;
    }

    public Product viewProductEByID(int id) {
        Product Sp = pGateway.getProductEByID(id);
        return Sp;
    }

    // gets a manager using an ID from the database then gets all that managers softwarestaff from the DB
    public Manager viewManager(int id){
        Manager m = mGateway.getManager(id);
        m.setProductList(this.pGateway.getProductByManagerId(id));
        return m;
    }

    public List viewAllManagerSolo () {
        this.managerList = this.mGateway.getManagers();
        return managerList;
    }

    public Manager viewManagerSolo (int id) {
        Manager m = mGateway.getManager(id);
//        this.managerList = this.mGateway.getManagers();
        return m;
    }

    public boolean createManager(Manager m){
//        boolean inserted = mGateway.insertManager(m);
//        return  inserted;
//
       return (mGateway.insertManager(m));
    }

    public int createProduct(Product product) {
        int createdId = this.pGateway.createProduct(product);
        return createdId;
    }

    public boolean updateSeasonalProducts(int id, String name, String season, String rating, String warrenty, double cost, int managerId) {
        return this.pGateway.updateSeasonalProducts(id, name, season, rating, warrenty, cost, managerId);
    }

    public boolean updateElectricalProducts(int id, String name, double cost, String make, String warrenty, int managerId) {
        return this.pGateway.updateElectricalProducts(id, name, cost, make, warrenty, managerId);
    }

    public boolean deleteEProduct(int id) {
        return (pGateway.deleteEProduct(id));
    }

    public boolean deleteSProduct(int id) {
        return (pGateway.deleteSProduct(id));
    }

    public boolean assignSeasonalProducts(int id, int managerId) {
        return this.pGateway.assignSeasonalProducts(id, managerId);
    }

    public boolean assignElectricalProducts(int id, int managerId) {
        return this.pGateway.assignElectricalProducts(id, managerId);
    }

    public boolean deleteManager(int id) {
        return (pGateway.deleteManager(id));
    }

    public boolean reAssignSeasonalProductManager(int id, int managerId) {
         return this.pGateway.reAssignSeasonalProductManager(id, managerId);
    }

    public boolean reAssignElectricalProductManager(int id, int managerId) {
        return this.pGateway.reAssignElectricalProductManager(id, managerId);
    }
}


