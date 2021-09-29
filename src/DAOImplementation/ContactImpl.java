/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImplementation;

import DAO.DAOInterface;
import Model.Contact;
import Utility.DB;
import Utility.Query;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The DAO Implementation for the Contact Model. Handles all queries to the Contact table in the database.
 * @author KC Green
 */
public class ContactImpl implements DAOInterface<Contact>{
    /**
     * An ObservableList containing all of the Contacts retrieved from the database.
     */
    ObservableList<Contact> contacts = FXCollections.observableArrayList();

    @Override
    public Contact get(int id) {
        Contact contact = new Contact();
        
        try{
            String selectStatement = "SELECT * FROM contacts WHERE Contact_ID="+id;

            Query.setPreparedStatement(DB.getConnection(), selectStatement);
            PreparedStatement ps = Query.getPreparedStatement();

            ps.execute();

            ResultSet rs = ps.getResultSet();
            
            while(rs.next()){
                contact.setId(rs.getInt("Contact_ID"));
                contact.setName(rs.getString("Contact_Name"));
                contact.setEmail(rs.getString("Email"));
            }            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return contact;        
    }

    @Override
    public ObservableList<Contact> getAll() {
        this.contacts.clear();
        try{
            String selectStatement = "SELECT * FROM contacts;";

            Query.setPreparedStatement(DB.getConnection(), selectStatement);
            PreparedStatement ps = Query.getPreparedStatement();

            ps.execute();

            ResultSet rs = ps.getResultSet();

            while(rs.next()){
                int contactId = rs.getInt("Contact_ID");
                String  contactName = rs.getString("Contact_Name");
                String email = rs.getString("Email");

                Contact newContact = new Contact(contactId, contactName, email);
                contacts.add(newContact);
            }            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return this.contacts;
    }

    /**
     * Not Supported Yet.
     * @param t The Contact object.
     * @return The number of rows affected by the Insert Query.
     */
    @Override
    public int create(Contact t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Not Supported Yet.
     * @param t The Contact object.
     * @return The number of rows affected by the Update Query.
     */    
    @Override
    public int update(Contact t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Not Supported Yet.
     * @param t The Contact object.
     * @return The number of rows affected by the Delete Query.
     */    
    @Override
    public int delete(Contact t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
