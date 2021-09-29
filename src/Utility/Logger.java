/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;


/**
 * A class to log Login attempts by any users.
 * @author KC Green
 */
public class Logger {
    /**
     * Log the attempted logins of any users.
     * @param username The username provided by the user.
     * @param date The date of the login attempt.
     * @param time The time of the login attempt.
     * @param success Whether the login attempt was successful or not.
     */
    public static void logAttempt(String username, LocalDate date, LocalTime time, boolean success){
        try{
            String dirName = System.getProperty("user.dir");
            String fileName = "/login_activity.txt";
            FileWriter fw = new FileWriter(dirName+fileName, true);
            PrintWriter pw = new PrintWriter(fw);
        
            pw.println(username+"\t-\t"+date.toString()+"\t-\t"+time.toString().substring(0, 12)+"\t-\t"+success);
        
            pw.close();
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }

    }
}
