/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImplementation;

import DAO.DAOInterface;
import Model.Customer;
import Utility.DB;
import Utility.Query;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import Utility.TimeConversion;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * The DAO Implementation for the Customer Model. Handles all queries to the Customer table in the database.
 * @author KC Green
 */
public class CustomerImpl implements DAOInterface<Customer>{
    /**
     * An ObservableList containing all of the Customers retrieved from the database.
     */
    ObservableList<Customer> custList = FXCollections.observableArrayList();

    @Override
    public Customer get(int id) {
        Customer cust = new Customer();
        try{
            String selectStatement = "SELECT * FROM customers WHERE Customer_ID="+id;
        
            Query.setPreparedStatement(DB.getConnection(), selectStatement);
            PreparedStatement ps = Query.getPreparedStatement();
        
            ps.execute();
        
            ResultSet rs = ps.getResultSet();
            
            while(rs.next()){
                int custId = rs.getInt("Customer_ID");
                String name = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                ZonedDateTime zonedCreateDate = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("Create_Date"));
                String createdBy = rs.getString("Created_By");
                ZonedDateTime zonedLastUpdate = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("Last_Update"));
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int divisionId = rs.getInt("Division_ID");
                
                cust = new Customer(custId, name, address, postalCode, phone, zonedCreateDate, createdBy, zonedLastUpdate, lastUpdatedBy, divisionId);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return cust;
    }

    @Override
    public ObservableList<Customer> getAll() {
        custList.clear();
        try{
            String selectStatement = "SELECT * FROM customers;";
        
            Query.setPreparedStatement(DB.getConnection(), selectStatement);
            PreparedStatement ps = Query.getPreparedStatement();
        
            ps.execute();
        
            ResultSet rs = ps.getResultSet();    
            
            while(rs.next()){
                int custId = rs.getInt("Customer_ID");
                String name = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                ZonedDateTime zonedCreateDate = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("Create_Date"));
                String createdBy = rs.getString("Created_By");
                ZonedDateTime zonedLastUpdate = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("Last_Update"));
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int divisionId = rs.getInt("Division_ID");
                
                Customer cust = new Customer(custId, name, address, postalCode, phone, zonedCreateDate, createdBy, zonedLastUpdate, lastUpdatedBy, divisionId);      
                
                this.custList.add(cust);
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return this.custList;
    }

    @Override
    public int create(Customer t){
        int rowsAffected = 0;
        try{
            String selectStatement = "INSERT INTO customers"
                    + "(Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) "
                    + "VALUES(?,?,?,?,?,?,?,?,?);";

            Query.setPreparedStatement(DB.getConnection(), selectStatement);
            PreparedStatement ps = Query.getPreparedStatement();
            ps.setString(1, t.getName());
            ps.setString(2, t.getAddress());
            ps.setString(3, t.getPostalCode());
            ps.setString(4, t.getPhone());
            ps.setTimestamp(5, Timestamp.from(t.getCreateDate().withZoneSameInstant(ZoneId.of("UTC")).toInstant()));
            ps.setString(6, t.getCreatedBy());
            ps.setTimestamp(7, Timestamp.from(t.getLastUpdate().withZoneSameInstant(ZoneId.of("UTC")).toInstant()));
            ps.setString(8, t.getLastUpdatedBy());
            ps.setInt(9, t.getDivisionId());

            ps.execute();
            rowsAffected = ps.getUpdateCount();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return rowsAffected;
    }

    @Override
    public int update(Customer t) {
        int rowsAffected = 0;
        try{
            String updateStatement = "UPDATE customers "
                + "SET Customer_Name=?, Address=?, Postal_Code=?, Phone=?, Create_Date=?, Created_By=?, Last_Update=?, Last_Updated_By=?, Division_ID=? "
                + "WHERE Customer_ID=?;";            
            
            Query.setPreparedStatement(DB.getConnection(), updateStatement);
            PreparedStatement ps = Query.getPreparedStatement();
            
            ps.setString(1, t.getName());
            ps.setString(2, t.getAddress());
            ps.setString(3, t.getPostalCode());
            ps.setString(4, t.getPhone());
            ps.setTimestamp(5, Timestamp.from(t.getCreateDate().withZoneSameInstant(ZoneId.of("UTC")).toInstant()));
            ps.setString(6, t.getCreatedBy());
            ps.setTimestamp(7, Timestamp.from(t.getLastUpdate().withZoneSameInstant(ZoneId.of("UTC")).toInstant()));
            ps.setString(8, t.getLastUpdatedBy());
            ps.setInt(9, t.getDivisionId());
            ps.setInt(10, t.getId());
            
            ps.execute();
            
            rowsAffected = ps.getUpdateCount();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return rowsAffected;
    }

    @Override
    public int delete(Customer t) {
        int rowsAffected = 0;
        try{
            String deleteStatement = "DELETE FROM customers WHERE Customer_ID=?;";
            
            Query.setPreparedStatement(DB.getConnection(), deleteStatement);
            PreparedStatement ps = Query.getPreparedStatement();
            
            ps.setInt(1, t.getId());
            
            ps.execute();
            
            rowsAffected = ps.getUpdateCount();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return rowsAffected;
    }    
}
