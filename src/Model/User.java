/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.ZonedDateTime;

/**
 * The User class contains the information necessary to authenticate employees using the Scheduling application.
 * @author KC Green
 */
public class User {
    /**
     * The ID of the User.
     */
    private int id;
    /**
     * The Name of the User.
     */
    private String name;
    /**
     * The Password of the User.
     */
    private String password;
    /**
     * The Create Date of the User.
     */
    private ZonedDateTime createDate;
    /**
     * The Created By of the User.
     */
    private String createdBy;
    /**
     * The Last Update of the User.
     */
    private ZonedDateTime lastUpdate;
    /**
     * The Last Updated By of the User.
     */
    private String lastUpdatedBy;
    
    /**
     * Default Constructor. Creates a User object with no values in the data members.
     */
    public User(){};
    
    
    /**
     * Class Constructor. Creates a User with the specified values.
     * @param id The User ID
     * @param name The User Name
     * @param password The User Password
     * @param createDate The User Create Date
     * @param createdBy The User Created By
     * @param lastUpdate The User Last Update
     * @param lastUpdatedBy The User Las Updated By
     */
    public User(int id, String name, String password, ZonedDateTime createDate, String createdBy, ZonedDateTime lastUpdate, String lastUpdatedBy){
        this.id = id;
        this.name = name;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }
    
    /**
     * Sets the User Id value.
     * @param id The integer unique User ID
     */
    public void setId(int id){
        this.id = id;
    }
    
    /**
     * Sets the User Name value.
     * @param name The Name of the User.
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * Sets the User Password value.
     * @param password The Password of the User.
     */
    public void setPassword(String password){
        this.password = password;
    }
    
    /**
     * Sets the User Create Date value.
     * @param createDate The Create Date of the User.
     */
    public void setCreateDate(ZonedDateTime createDate){
        this.createDate = createDate;
    }
    
    /**
     * Sets the User Create By value.
     * @param createdBy The Created By of the User.
     */
    public void setCreatedBy(String createdBy){
        this.createdBy = createdBy;
    }
    
    /**
     * Sets the User Last Update value.
     * @param lastUpdate The Last Update of the User.
     */
    public void setLastUpdate(ZonedDateTime lastUpdate){
        this.lastUpdate = lastUpdate;
    }
    
    /**
     * Sets the User Last Updated By value.
     * @param lastUpdatedBy The Last Updated By of the User.
     */
    public void setLastUpdatedBy(String lastUpdatedBy){
        this.lastUpdatedBy = lastUpdatedBy;
    }
    
    /**
     * Retrieves the User ID.
     * @return The ID of the User.
     */
    public int getId(){
        return this.id;
    }
    
    /**
     * Retrieves the User Name.
     * @return The Name of the User.
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * Retrieves the User Password.
     * @return The Password of the User.
     */
    public String getPassword(){
        return this.password;
    }
    
    /**
     * Retrieves the User Create Date.
     * @return The Create Date of the User.
     */
    public ZonedDateTime getCreateDate(){
        return this.createDate;
    }
    
    /**
     * Retrieves the User Created By.
     * @return The Created By of the User.
     */
    public String getCreatedBy(){
        return this.createdBy;
    }
    
    /**
     * Retrieves the User Last Update.
     * @return The Last Update of the User.
     */
    public ZonedDateTime getLastUpdate(){
        return this.lastUpdate;
    }
    
    /**
     * Retrieves the User Last Updated By.
     * @return The Last Updated By of the User.
     */
    public String getLastUpdatedBy(){
        return this.lastUpdatedBy;
    }    
}
