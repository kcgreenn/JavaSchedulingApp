/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.ZonedDateTime;

/**
 * The Customer class contains all of the Customer information necessary for scheduling appointments.
 * @author KC Green
 */
public class Customer {
    private int id;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private ZonedDateTime createDate;
    private String createdBy;
    private ZonedDateTime lastUpdate;
    private String lastUpdatedBy;
    private int divisionId;
    
    /**
     * Default Constructor. Creates a Customer with no values in its members.
     */
    public Customer(){};
    
    /**
     * Class Constructor. Creates a Customer with the specified values.
     * @param id The ID of the Customer.
     * @param name The name of the Customer.
     * @param address The Address of the Customer.
     * @param postalCode The Postal Code of the Customer.
     * @param phone The Phone number of the Customer.
     * @param createDate The Create Date of the Customer.
     * @param createdBy The Created By of the Customer.
     * @param lastUpdate The Last Update of the Customer.
     * @param lastUpdatedBy The Last Updated By of the Customer.
     * @param divisionId The Division ID of the Customer.
     */
    public Customer(int id, String name, String address, String postalCode, String phone, ZonedDateTime createDate, String createdBy, ZonedDateTime lastUpdate, String lastUpdatedBy, int divisionId){
        this.id = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionId = divisionId;
    }
    
    /**
     * Sets the ID of the Customer.
     * @param id The ID of the Customer.
     */
    public void setId(int id){
        this.id = id;
    }
    
    /**
     * Sets the Name of the Customer.
     * @param name The Name of the Customer.
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * Sets the Address of the Customer.
     * @param address The Address of the Customer.
     */
    public void setAddress(String address){
        this.address = address;
    }
    
    /**
     * Sets the Postal Code of the Customer.
     * @param postalCode The Postal Code of the Customer.
     */
    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }
    
    /**
     * Sets the Phone of the Customer.
     * @param phone The Phone of the Customer.
     */
    public void setPhone(String phone){
        this.phone = phone;
    }
    
    /**
     * Sets the Create Date of the Customer.
     * @param createDate The Create Date of the Customer.
     */
    public void setCreateDate(ZonedDateTime createDate){
        this.createDate = createDate;
    }
    
    /**
     * Sets the Created By of the Customer.
     * @param createdBy The Created By of the Customer.
     */
    public void setCreatedBy(String createdBy){
        this.createdBy = createdBy;
    }
    
    /**
     * Sets the Last Update of the Customer.
     * @param lastUpdate The Last Update of the Customer.
     */
    public void setLastUpdate(ZonedDateTime lastUpdate){
        this.lastUpdate = lastUpdate;
    }
    
    /**
     * Sets the Last Updated By of the Customer.
     * @param lastUpdatedBy The Last Updated By of the Customer.
     */
    public void setLastUpdatedBy(String lastUpdatedBy){
        this.lastUpdatedBy = lastUpdatedBy;
    }
    
    /**
     * Sets the Division ID of the Customer.
     * @param divisionId The Division ID of the Customer.
     */
    public void setDivisionId(int divisionId){
        this.divisionId = divisionId;
    }
    
    /**
     * Retrieves the ID of the Customer.
     * @return The ID of the Customer.
     */
    public int getId(){
        return this.id;
    }
    
    /**
     * Retrieves the Name of the Customer.
     * @return The Name of the Customer.
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * Retrieves the Address of the Customer.
     * @return The Address of the Customer.
     */
    public String getAddress(){
        return this.address;
    }
    
    /**
     * Retrieves the Postal Code of the Customer.
     * @return The Postal Code of the Customer.
     */
    public String getPostalCode(){
        return this.postalCode;
    }
    
    /**
     * Retrieves the Phone of the Customer.
     * @return The Phone of the Customer.
     */
    public String getPhone(){
        return this.phone;
    }
    
    /**
     * Retrieves the Create Date of the Customer.
     * @return The Create Date of the Customer.
     */
    public ZonedDateTime getCreateDate(){
        return this.createDate;
    }
    
    /**
     * Retrieves the Created By of the Customer.
     * @return The Created By of the Customer.
     */
    public String getCreatedBy(){
        return this.createdBy;
    }
    
    /**
     * Retrieves the Last Update of the Customer.
     * @return The Last Update of the Customer.
     */
    public ZonedDateTime getLastUpdate(){
        return this.lastUpdate;
    }
    
    /**
     * Retrieves the Last Updated By of the Customer.
     * @return The Last Updated By of the Customer.
     */
    public String getLastUpdatedBy(){
        return this.lastUpdatedBy;
    }
    
    /**
     * Retrieves the Division ID of the Customer.
     * @return The Division ID of the Customer.
     */
    public int getDivisionId(){
        return this.divisionId;
    }
}
