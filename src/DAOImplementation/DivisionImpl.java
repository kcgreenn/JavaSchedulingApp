/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImplementation;

import Utility.DB;
import Utility.Query;
import DAO.DAOInterface;
import Model.Division;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Utility.TimeConversion;
import java.time.ZonedDateTime;

/**
 * The DAO Implementation for the Division Model. Handles all queries to the Division table in the database.
 * @author KC Green
 */
public class DivisionImpl implements DAOInterface{
    /**
     * An ObservableList containing all of the Divisions retrieved from the database.
     */
    private ObservableList<Division> divisions = FXCollections.observableArrayList();

    @Override
    public Division get(int id) {
        Division div = new Division();
        try{
            String selectStatement = "SELECT * FROM first_level_divisions WHERE Division_ID=?;";
            
            Query.setPreparedStatement(DB.getConnection(), selectStatement);
            PreparedStatement ps = Query.getPreparedStatement();
            
            ps.setInt(1, id);
            
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            
            while(rs.next()){
                div.setId(rs.getInt("Division_ID"));
                div.setName(rs.getString("Division"));
                ZonedDateTime zonedCreateDate = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("Create_Date"));
                div.setCreateDate(zonedCreateDate);
                div.setCreatedBy(rs.getString("Created_By"));
                ZonedDateTime zonedLastUpdate = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("Last_Update"));
                div.setLastUpdate(zonedLastUpdate);
                div.setLastUpdatedBy(rs.getString("Last_Updated_By"));
                div.setCountryId(rs.getInt("Country_ID"));  
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return div;
    }

    @Override
    public ObservableList<Division> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Retrieves all of the Divisions in the given Country.
     * @param countryId The ID of the Country
     * @return An ObservableList containing all First_Level_Divisions of the given Country.
     */
    public ObservableList<Division> getAllByCountry(int countryId){
        this.divisions.clear();
        try{
            String selectStatement = "SELECT * FROM countries INNER JOIN first_level_divisions ON countries.Country_ID = first_level_divisions.Country_ID  WHERE countries.Country_ID=?;";
            
            Query.setPreparedStatement(DB.getConnection(), selectStatement);
            PreparedStatement ps = Query.getPreparedStatement();
            
            ps.setInt(1, countryId);
                        
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            
            while(rs.next()){
                Division div = new Division();
                div.setId(rs.getInt("Division_ID"));
                div.setName(rs.getString("Division"));
                this.divisions.add(div);
            }
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return divisions;
    }    

    /**
     * Not supported yet.
     * @param t The Division object.
     * @return The number of rows affected by the Insert Query.
     */    
    @Override
    public int create(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Not supported yet.
     * @param t The Division object.
     * @return The number of rows affected by the Update Query.
     */    
    @Override
    public int update(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Not supported yet.
     * @param t The Division object.
     * @return The number of rows affected by the Delete Query.
     */    
    @Override
    public int delete(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
