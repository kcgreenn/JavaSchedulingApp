/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.ZonedDateTime;

/**
 * The Customer class contains the data necessary to identify a Country
 * @author KC Green
 */
public class Country {
    /**
     * The ID of the Country.
     */
    private int id;
    /**
     * The Name of the Country.
     */
    private String name;
    /**
     * The Create Date oof the Country.
     */
    private ZonedDateTime createDate;
    /**
     * The Created By of the Country.
     */
    private String createdBy;
    /**
     * The Last Update of the Country.
     */
    private ZonedDateTime lastUpdate;
    /**
     * The Last Updated By of the Country.
     */
    private String lastUpdatedBy;
    /**
     * Default Constructor. Creates a Country object with no values in its members.
     */
    public Country(){};
    
    
    /**
     * Class Constructor. Creates a Country object with specified values in its members.
     * @param countryId The ID of the Country.
     * @param countryName The Name of the Country.
     * @param createDate The Create Date of the Country.
     * @param createdBy The Created By of the Country.
     * @param lastUpdate The Last Update of the Country.
     * @param lastUpdatedBy The Last Updated By of the Country.
     */
    public Country(int countryId, String countryName, ZonedDateTime createDate, String createdBy, ZonedDateTime lastUpdate, String lastUpdatedBy){
        this.id = countryId;
        this.name = countryName;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }
    
    /**
     * Retrieves the ID of the Country.
     * @return The ID of the Country.
     */
    public int getId(){
        return this.id;
    }
    
    /**
     * Retrieves the Name of the Country.
     * @return The Name of the Country.
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * Retrieves the Create Date of the Country.
     * @return The Create Date of the Country.
     */
    public ZonedDateTime getCreateDate(){
        return this.createDate;
    }
    
    /**
     * Retrieves the Created By of the Country.
     * @return The Created By of the Country.
     */
    public String getCreatedBy(){
        return this.createdBy;
    }
    
    /**
     * Retrieves the Last Update of the Country.
     * @return The Last Update of the Country.
     */
    public ZonedDateTime getLastUpdate(){
        return this.lastUpdate;
    }
    
    /**
     * Retrieves the Last Updated By of the Country.
     * @return The Last Updated By of the Country.
     */
    public String getLastUpdatedBy(){
        return this.lastUpdatedBy;
    }
    
    /**
     * Sets the ID of the Country.
     * @param countryId The ID of the Country.
     */
    public void setId(int countryId){
        this.id = countryId;
    }
    
    /**
     * Sets the Name of the Country.
     * @param countryName The Name of the Country.
     */
    public void setName(String countryName){
        this.name = countryName;
    }
    
    /**
     * Sets the Create Date of the Country.
     * @param createDate The Create Date of the Country.
     */
    public void setCreateDate(ZonedDateTime createDate){
        this.createDate = createDate;
    }
    
    /**
     * Sets the Created By of the Country.
     * @param createdBy The Created By of the Country.
     */
    public void setCreatedBy(String createdBy){
        this.createdBy = createdBy;
    }
    
    /**
     * Sets the Last Update of the Country.
     * @param lastUpdate The Last Update of the Country.
     */
    public void setLastUpdate(ZonedDateTime lastUpdate){
        this.lastUpdate = lastUpdate;
    }
    
    /**
     * Sets the Last Updated By of the Country.
     * @param lastUpdatedBy The Last Updated By of the Country.
     */
    public void setLastUpdatedBy(String lastUpdatedBy){
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
