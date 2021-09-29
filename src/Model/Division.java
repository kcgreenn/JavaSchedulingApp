/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.ZonedDateTime;

/**
 * The Division class contains the id, name and country id data necessary to map division names to their countries.
 * @author kcgre
 */
public class Division {
    /**
     * The ID of the Division.
     */
    private int id;
    /**
     * The Name of the Division.
     */
    private String name;
    /**
     * The Create Date of the Division.
     */
    private ZonedDateTime createDate;
    /**
     * The Created By of the Division.
     */
    private String createdBy;
    /**
     * The Last Update of the Division.
     */
    private ZonedDateTime lastUpdate;
    /**
     * The Last Updated By of the Division.
     */
    private String lastUpdatedBy;
    /**
     * The ID of the Country this Division belongs to.
     */
    private int countryId;
    
    /**
     * Default Constructor. Creates a Division object with no values in it's members.
     */
    public Division(){};
    
    /**
     * Class Constructor. Creates a Division object with the specified values.
     * @param divisionId The unique, integer ID of the Division.
     * @param divisionName The Name of the Division.
     * @param createDate The Create Date of the Division.
     * @param createdBy The Created By of the Division.
     * @param lastUpdate The Last Update of the Division.
     * @param lastUpdatedBy The Last Updated By of the Division.
     * @param countryId The Country ID of the Division.
     */
    public Division(int divisionId, String divisionName, ZonedDateTime createDate, String createdBy, ZonedDateTime lastUpdate, String lastUpdatedBy, int countryId){
        this.id = divisionId;
        this.name = divisionName;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryId = countryId;
    }
    
    /**
     * Sets the ID of the Division.
     * @param divisionId The ID of the Division.
     */
    public void setId(int divisionId){
        this.id = divisionId;
    }
    
    /**
     * Sets the Name of the Division.
     * @param divisionName The Name of the Division.
     */
    public void setName(String divisionName){
        this.name = divisionName;
    }
    
    /**
     * Sets the Create Date of the Division.
     * @param createDate The Create Date of the Division.
     */
    public void setCreateDate(ZonedDateTime createDate){
        this.createDate = createDate;
    }
    
    /**
     * Sets the Created By of the Division.
     * @param createdBy The Created By of the Division.
     */
    public void setCreatedBy(String createdBy){
        this.createdBy = createdBy;
    }
    
    /**
     * Sets the Last Update of the Division.
     * @param lastUpdate The Last Update of the Division.
     */
    public void setLastUpdate(ZonedDateTime lastUpdate){
        this.lastUpdate = lastUpdate;
    }
    
    /**
     * Sets the Last Updated By of the Division.
     * @param lastUpdatedBy The Last Updated By of the Division.
     */
    public void setLastUpdatedBy(String lastUpdatedBy){
        this.lastUpdatedBy = lastUpdatedBy;
    }
    
    /**
     * Sets the Country ID of the Division.
     * @param countryId The Country ID of the Division.
     */
    public void setCountryId(int countryId){
        this.countryId = countryId;
    }
    
    /**
     * Retrieves the ID of the Division.
     * @return The ID of the Division.
     */
    public int getId(){
        return this.id;
    }
    
    /**
     * Retrieves the Name of the Division.
     * @return The Name of the Division.
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * Retrieves the Create Date of the Division.
     * @return The Create Date of the Division.
     */
    public ZonedDateTime getCreateDate(){
        return this.createDate;
    }
    
    /**
     * Retrieves the Created By of the Division.
     * @return The Created By of the Division.
     */
    public String getCreatedBy(){
        return this.createdBy;
    }
    
    /**
     * Retrieves the Last Update of the Division.
     * @return The Last Update of the Division.
     */
    public ZonedDateTime getLastUpdate(){
        return this.lastUpdate;
    }
    
    /**
     * Retrieves the Last Updated By of the Division.
     * @return The Last Updated By of the Division.
     */
    public String getLastUpdatedBy(){
        return this.lastUpdatedBy;
    }
    
    /**
     * Retrieves the Country ID of the Division.
     * @return The Country ID of the Division.
     */
    public int getCountryId(){
        return this.countryId;
    }
}
