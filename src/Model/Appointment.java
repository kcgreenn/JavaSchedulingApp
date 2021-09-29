/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * The Appointment class contains all of the data necessary to record company appointments.
 * @author KC Green
 */
public class Appointment {
    /**
     * The unique ID of the Appointment.
     */
    private int appointmentId;
    /**
     * The Title of the appointment.
     */
    private String title;
    /**
     * The Description of the Appointment.
     */
    private String description;
    /**
     * The Location of the Appointment.
     */
    private String location;
    /**
     * The Type of the Appointment.
     */
    private String type;
    /**
     * The Start DateTime of the Appointment.
     */
    private ZonedDateTime start;
    /**
     * The formatted Start Date of the Appointment.
     */
    private String formattedStartDate;
    /**
     * The formatted Start Time of the Appointment.
     */
    private String formattedStartTime;
    /**
     * The formatted Start DateTime of the Appointment.
     */
    private String formattedStartDateTime;
    /**
     * The End DateTime of the Appointment.
     */
    private ZonedDateTime end;
    /**
     * The formatted End Date of the Appointment.
     */
    private String formattedEndDate;
    /**
     * The formatted End Time of the Appointment.
     */
    private String formattedEndTime;
    /**
     * The formatted End DateTime of the Appointment.
     */
    private String formattedEndDateTime;
    /**
     * The Create Date of the Appointment.
     */
    private ZonedDateTime createDate;
    /**
     * The Created By of the Appointment.
     */
    private String createdBy;
    /**
     * The Last Update of the Appointment.
     */
    private ZonedDateTime lastUpdate;
    /**
     * The Last Updated By of the Appointment.
     */
    private String lastUpdatedBy;
    /**
     * The Customer ID of the Appointment.
     */
    private int customerId;
    /**
     * The User ID of the Appointment.
     */
    private int userId;
    /**
     * The Contact ID of the Appointment.
     */
    private int contactId;
    
    /**
     * Default Constructor. Creates an Appointment with no values in its members.
     */
    public Appointment(){};
    
    /**
     * Class Constructor. Creates an Appointment with the specified values in its members.
     * @param appointmentId The ID of the Appointment.
     * @param title The Title of the Appointment.
     * @param description The Description of the Appointment.
     * @param location The Location of the Appointment.
     * @param type The Type of the Appointment.
     * @param start The Start DateTime of the Appointment.
     * @param end The End DateTime of the Appointment.
     * @param createDate The Create Date of the Appointment.
     * @param createdBy The Created By of the Appointment.
     * @param lastUpdate The Last Update of the Appointment.
     * @param lastUpdatedBy The Last Updated By of the Appointment.
     * @param customerId The Customer ID of the Appointment.
     * @param userId The User ID of the Appointment.
     * @param contactId The Contact ID of the Appointment.
     */
    public Appointment(int appointmentId, String title, String description, String location, String type, ZonedDateTime start, ZonedDateTime end, ZonedDateTime createDate, String createdBy,
                    ZonedDateTime lastUpdate, String lastUpdatedBy, int customerId, int userId, int contactId){
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId; 
        this.formatAllDateTimes();
    }
    
    /**
     * Sets the ID of the Appointment.
     * @param appointmentId The ID of the Appointment.
     */
    public void setAppointmentId(int appointmentId){
        this.appointmentId = appointmentId;
    }
    
    /**
     * Sets the Title of the Appointment.
     * @param title The Title of the Appointment.
     */
    public void setTitle(String title){
        this.title = title;
    }
    
    /**
     * Sets the Description of the Appointment.
     * @param description The Description of the Appointment.
     */
    public void setDescription(String description){
        this.description = description;
    }
    
    /**
     * Sets the Location of the Appointment.
     * @param location The Location of the Appointment.
     */
    public void setLocation(String location){
        this.location = location;
    }
    
    /**
     * Sets the Type of the Appointment.
     * @param type The Type of the Appointment.
     */
    public void setType(String type){
        this.type = type;
    }
    
    /**
     * Sets the Start DateTime of the Appointment.
     * @param start The Start DateTime of the Appointment.
     */
    public void setStart(ZonedDateTime start){
        this.start = start;
    }
    
    /**
     * Sets the End DateTime of the Appointment.
     * @param end The End DateTime of the Appointment.
     */
    public void setEnd(ZonedDateTime end){
        this.end = end;
    }
    
    /**
     * Sets the Create Date of the Appointment.
     * @param createDate The Create Date of the Appointment.
     */
    public void setCreateDate(ZonedDateTime createDate){
        this.createDate = createDate;
    }
    
    /**
     * Sets the Created By of the Appointment.
     * @param createdBy The Created By of the Appointment.
     */
    public void setCreatedBy(String createdBy){
        this.createdBy = createdBy;
    }
    
    /**
     * Sets the Last Update of the Appointment.
     * @param lastUpdate The Last Update of the Appointment.
     */
    public void setLastUpdate(ZonedDateTime lastUpdate){
        this.lastUpdate = lastUpdate;
    }
    
    /**
     * Sets the Last Updated By of the Appointment.
     * @param lastUpdatedBy The Last Updated By of the Appointment.
     */
    public void setLastUpdatedBy(String lastUpdatedBy){
        this.lastUpdatedBy = lastUpdatedBy;
    }
    
    /**
     * Sets the Customer ID of the Appointment.
     * @param customerId The Customer ID of the Appointment.
     */
    public void setCustomerId(int customerId){
        this.customerId = customerId;
    }
    
    /**
     * Sets the User ID of the Appointment.
     * @param userId The User ID of the Appointment.
     */
    public void setUserId(int userId){
        this.userId = userId;
    }
    
    /**
     * Sets the Contact ID of the Appointment.
     * @param contactId The Contact ID of the Appointment.
     */
    public void setContactId(int contactId){
        this.contactId = contactId;
    }
    
    /**
     * Retrieves the ID of the Appointment.
     * @return The ID of the Appointments.
     */
    public int getAppointmentId(){
        return this.appointmentId;
    }
    
    /**
     * Retrieves the Title of the Appointment.
     * @return The Title of the Appointment.
     */
    public String getTitle(){
        return this.title;
    }
    
    /**
     * Retrieves the Description of the Appointment.
     * @return The Description of the Appointment.
     */
    public String getDescription(){
        return this.description;
    }
    
    /** Retrieves the Location of the Appointment.
     * @return The Location of the Appointment.
     */
    public String getLocation(){
        return this.location;
    }
    
    /**
     * Retrieves the Type of the Appointment.
     * @return The Type of the Appointment.
     */
    public String getType(){
        return this.type;
    }
    
    /**
     * Retrieves the Start DateTime of the Appointment.
     * @return The Start DateTime of the Appointment.
     */
    public ZonedDateTime getStart(){
        return this.start;
    }
    
    /**
     * Retrieves the Formatted Start Date of the Appointment.
     * @return The Formatted Start Date of the Appointment.
     */
    public String getFormattedStartDate(){
        return this.formattedStartDate;
    }
    
    /**
     * Retrieves the Formatted Start Time of the Appointment.
     * @return The Formatted Start Time of the Appointment.
     */
    public String getFormattedStartTime(){
        return this.formattedStartTime;
    }
    
    /**
     * Retrieves the Formatted Start DateTime of the Appointment.
     * @return The Formatted Start DateTime of the Appointment.
     */    
    public String getFormattedStartDateTime(){
        return this.formattedStartDateTime;
    }
    
    /**
     * Retrieves the End DateTime of the Appointment.
     * @return The End DateTime of the Appointment.
     */
    public ZonedDateTime getEnd(){
        return this.end;
    }
    
    /**
     * Retrieves the Formatted End Date of the Appointment.
     * @return The Formatted End Date of the Appointment.
     */
    public String getFormattedEndDate(){
        return this.formattedEndDate;
    }
    
    /**
     * Retrieves the Formatted End Time of the Appointment.
     * @return The Formatted End Time of the Appointment.
     */
    public String getFormattedEndTime(){
        return this.formattedEndTime;
    }
    
    /**
     * Retrieves the Formatted End DateTime of the Appointment.
     * @return The Formatted End DateTime of the Appointment.
     */
    public String getFormattedEndDateTime(){
        return this.formattedEndDateTime;
    }    
    
    /**
     * Retrieves the Create Date of the Appointment.
     * @return The Create Date of the Appointment.
     */
    public ZonedDateTime getCreateDate(){
        return this.createDate;
    }
    
    /**
     * Retrieves the Create By of the Appointment.
     * @return The Create By of the Appointment.
     */
    public String getCreatedBy(){
        return this.createdBy;
    }
    
    /**
     * Retrieves the Last Update of the Appointment.
     * @return The Last Update of the Appointment.
     */
    public ZonedDateTime getLastUpdate(){
        return this.lastUpdate;
    }
    
    /**
     * Retrieves the Last Update By of the Appointment.
     * @return The Last Update By of the Appointment.
     */
    public String getLastUpdatedBy(){
        return this.lastUpdatedBy;
    }
    
    /**
     * Retrieves the Customer ID of the Appointment.
     * @return The Customer ID of the Appointment.
     */
    public int getCustomerId(){
        return this.customerId;
    }
    
    /**
     * Retrieves the User ID of the Appointment.
     * @return The User ID of the Appointment.
     */
    public int getUserId(){
        return this.userId;
    }
    
    /**
     * Retrieves the Contact ID of the Appointment.
     * @return The Contact ID of the Appointment.
     */
    public int getContactId(){
        return this.contactId;
    }
    
    /**
     * Formats the Start Date of the Appointment.
     * @param zdt The Start ZonedDateTime.
     */
    public void formatStartDate(ZonedDateTime zdt){
        this.formattedStartDate = zdt.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }    
    
    /**
     * Formats the Start Time of the Appointment.
     * @param zdt The Start ZonedDateTime.
     */
    public void formatStartTime(ZonedDateTime zdt){
        this.formattedStartTime = zdt.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
    }
    
    /**
     * Formats the Start DateTime of the Appointment.
     * @param zdt The Start ZonedDateTime.
     */
    public void formatStartDateTime(ZonedDateTime zdt){
        this.formattedStartDateTime = zdt.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
    }    
    
    /**
     * Formats the End Date of the Appointment.
     * @param zdt The End ZonedDateTime.
     */
    public void formatEndDate(ZonedDateTime zdt){
        this.formattedEndDate = zdt.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }
    
    /**
     * Formats the End Time of the Appointment.
     * @param zdt The End ZonedDateTime.
     */
    public void formatEndTime(ZonedDateTime zdt){
        this.formattedEndTime = zdt.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
    }
    
    /**
     * Formats the Start DateTime of the Appointment.
     * @param zdt The Start ZonedDateTime.
     */
    public void formatEndDateTime(ZonedDateTime zdt){
        this.formattedEndDateTime = zdt.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
    }        
    
    /**
     * Formats start and end DateTimes.
     */
    public void formatAllDateTimes(){
        formatStartDate(this.start);
        formatStartTime(this.start);
        formatStartDateTime(this.start);
        formatEndDate(this.end);
        formatEndTime(this.end);
        formatEndDateTime(this.end);
    }
}
