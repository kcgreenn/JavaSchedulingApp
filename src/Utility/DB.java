/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A class used to establish, reference and close a connection to a mySQL database.
 * @author KC Green
 */
public class DB {
    /**
     * A String containing the database protocol.
     */
    private static final String protocol = "jdbc";
    /**
     * A String containing the database vendor name.
     */
    private static final String vendorName = ":mysql:";
    /**
     * A String containing the database server name.
     */
    private static final String serverName = "//wgudb.ucertify.com/WJ07qnq";
    /**
     * A String containing the concatenated database URL.
     */
    private static final String jdbcUrl = protocol + vendorName + serverName;
    /**
     * A String containing the Java database driver.
     */
    private static final String MYSQLDriver = "com.mysql.cj.jdbc.Driver";    
    /**
     * A String containing the database admin username.
     */
    private static final String username = "U07qnq";
    /**
     * A String containing the database admin password.
     */    
    private static final String password = "53689104747";
    /**
     * The mySQL database connection object.
     */
    public static Connection conn = null;   
   
    /**
     * Establishes a connection to a mySQL database.
     * @return The Connection object.
     */
    public static Connection startConnection(){
       try{
           Class.forName(MYSQLDriver);
           conn = DriverManager.getConnection(jdbcUrl, username, password);
           System.out.println("Connection Successful!");
       }catch(ClassNotFoundException e){
           System.out.println("Class not found - "+e.getMessage());
       }catch(SQLException e){
           System.out.println(e.getMessage());
       }
       return conn;
    }
    
    /**
     * Retrieve the database connection that was previously established.
     * @return The Connection object.
     */
    public static Connection getConnection(){
        return conn;
    }
   
    /**
     * Closes the connection to the mySQL database.
     */
    public static void closeConnection(){
        try{
            conn.close();
            System.out.println("Connection Terminated.");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
