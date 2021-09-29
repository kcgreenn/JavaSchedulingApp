/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 * A class used to create and retrieve PreparedStatements.
 * @author KC Green
 */
public class Query {
    /**
     * The PreparedStatement object, used to form queries before sending to the database.
     */
    private static PreparedStatement preparedStatement;

    /**
     * Sends the PreparedStatement to the database.
     * @param conn A connection object to the database.
     * @param sqlStatement A String containing the query.
     */
    public static void setPreparedStatement(Connection conn, String sqlStatement){
        try{
            preparedStatement = conn.prepareStatement(sqlStatement);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
    }
    
    /**
     * Retrieved the PreparedStatement.
     * @return The PreparedStatement previously set.
     */
    public static PreparedStatement getPreparedStatement(){
        return preparedStatement;
    }
}
