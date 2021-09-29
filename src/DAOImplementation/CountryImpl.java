/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImplementation;

import Model.Country;
import Utility.DB;
import Utility.Query;
import Utility.TimeConversion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.time.ZonedDateTime;

/**
 * The DAO Implementation for the Country Model. Handles all queries to the Country table in the database.
 * @author KC Green
 */
public class CountryImpl implements DAO.DAOInterface<Country>{
    /**
     * An ObservableList containing all Countries retrieved from the database.
     */
    private ObservableList<Country> countries = FXCollections.observableArrayList();
    
    @Override
    public Country get(int id){
        
        Country cntry = new Country();
        
        try{
            String selectStatement = "SELECT * FROM countries WHERE Country_ID="+id;

            Query.setPreparedStatement(DB.getConnection(), selectStatement);
            PreparedStatement ps = Query.getPreparedStatement();

            ps.execute();

            ResultSet rs = ps.getResultSet();
            
            while(rs.next()){
                cntry.setId(rs.getInt("Country_ID"));
                cntry.setName(rs.getString("Country"));
                ZonedDateTime zonedCreateDate = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("Create_Date"));
                cntry.setCreateDate(zonedCreateDate);
                cntry.setCreatedBy(rs.getString("Created_By"));
                ZonedDateTime zonedLastUpdate = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("Last_Update"));
                cntry.setLastUpdate(zonedLastUpdate);
                cntry.setLastUpdatedBy(rs.getString("Last_Updated_By"));
            }            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return cntry;
    }
    
    @Override
    public ObservableList<Country> getAll(){
        this.countries.clear();
        try{
            String selectStatement = "SELECT * FROM countries;";

            Query.setPreparedStatement(DB.getConnection(), selectStatement);
            PreparedStatement ps = Query.getPreparedStatement();

            ps.execute();

            ResultSet rs = ps.getResultSet();

            while(rs.next()){
                int countryId = rs.getInt("Country_ID");
                String  countryName = rs.getString("Country");
                ZonedDateTime zonedCreateDate = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("Create_Date"));
                String createdBy = rs.getString("Created_By");
                ZonedDateTime zonedLastUpdate = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("Last_Update"));
                String lastUpdatedBy = rs.getString("Last_Updated_By");

                Country newCountry = new Country(countryId, countryName, zonedCreateDate, createdBy, zonedLastUpdate, lastUpdatedBy);
                countries.add(newCountry);
            }            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return this.countries;

    }

    /**
     * Not Supported Yet.
     * @param country The Country object.
     * @return The number of rows affected by the Insert Query.
     */    
    @Override
    public int create(Country country){
        return 0;
    }

    /**
     * Not Supported Yet.
     * @param country The Country object.
     * @return The number of rows affected by the Update Query.
     */    
    @Override
    public int update(Country country){
        return 0;
    }

    /**
     * Not Supported Yet.
     * @param country The Country object.
     * @return The number of rows affect by the Delete Query.
     */    
    @Override
    public int delete(Country country){
        return 0;
    }
}
