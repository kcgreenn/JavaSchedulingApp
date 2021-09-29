/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;

/**
 * A class that handles login attempts by the User.
 * @author KC Green
 */
public class LoginHandler {
    /**
     * Holds the data of the currently logged in user.
     */
    private static User currentUser = new User();
    /**
     * Is the user authenticated and logged in.
     */
    private static boolean loggedIn = false;
    
    /**
     * Retrieves the User that has logged in.
     * @return The currently logged in user.
     */
    public static User getCurrentUser(){
        return currentUser;
    }
    
    /**
     * Queries the database for the User login data that was entered.
     * @param name The username entered by the user.
     * @param password The password that was entered by the user.
     */
    public static void getUserByName(String name, String password){
        try{
            String selectStatement = "SELECT * FROM users WHERE User_Name=? AND Password=?";

            Query.setPreparedStatement(DB.getConnection(), selectStatement);
            PreparedStatement ps = Query.getPreparedStatement();

            ps.setString(1, name);
            ps.setString(2, password);
            
            ps.execute();

            ResultSet rs = ps.getResultSet();

            // Set loggedIn to true and set User information
            while(rs.next()){
                loggedIn = true;
                currentUser.setId(rs.getInt("User_ID"));
                currentUser.setName(rs.getString("User_Name"));
                currentUser.setPassword(rs.getString("Password"));
                ZonedDateTime zonedCreateDate = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("Create_Date"));
                currentUser.setCreateDate(zonedCreateDate);
                currentUser.setCreatedBy(rs.getString("Created_By"));
                ZonedDateTime zonedLastUpdate = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("Last_Update"));
                currentUser.setLastUpdate(zonedLastUpdate);
                currentUser.setLastUpdatedBy(rs.getString("Last_Updated_By"));                
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }    
    
    /**
     * Returns the logged in status of the User.
     * @return Boolean value of whether or not the user is logged in.
     */
    public static boolean isLoggedIn(){
        return loggedIn;
    }
}
