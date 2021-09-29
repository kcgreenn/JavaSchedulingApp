/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImplementation;

import Utility.DB;
import Utility.Query;
import Model.Appointment;
import Utility.GroupCount;
import Utility.TimeConversion;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.ZonedDateTime;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * The DAO Implementation for the Appointment Model. Handles all queries to the Appointment table in the database.
 * @author KC Green
 */
public class AppointmentImpl implements DAO.DAOInterface<Appointment>{
    /**
     * An ObersvableList containing all of the Appointments retrieved from the database.
     */
    private ObservableList<Appointment> appointmentsList = FXCollections.observableArrayList();
    @Override
    public Appointment get(int id) {
        Appointment appt = new Appointment();
        try{
            String selectStatement = "SELECT * FROM appointments WHERE Appointment_ID=?";

            Query.setPreparedStatement(DB.getConnection(), selectStatement);
            PreparedStatement ps = Query.getPreparedStatement();

            ps.setInt(1, id);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            while(rs.next()){
                ZonedDateTime zonedStart = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("Start"));
                ZonedDateTime zonedEnd = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("End"));
                ZonedDateTime zonedCreate = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("Create_Date"));
                ZonedDateTime zonedLastUpdate = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("Last_Update"));
                appt = new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"), rs.getString("Description"), rs.getString("Location"), rs.getString("Type"), 
                    zonedStart, zonedEnd, zonedCreate, rs.getString("Created_By"), zonedLastUpdate, rs.getString("Last_Updated_By"), rs.getInt("Customer_ID"), 
                    rs.getInt("User_ID"), rs.getInt("Contact_ID"));
                appt.formatAllDateTimes();
            }            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return appt;
    }
    
    /**
     * Retrieves any of the user's appointments that are scheduled in the next fifteen minutes.
     * @param userId The ID of the User.
     * @return Returns the appointment scheduled in the next fifteen minutes, if it exists.
     */
    public Appointment getUpcomingAppointment(int userId){
        Appointment appt = new Appointment();
        try{
            String selectStatement = "SELECT * FROM appointments WHERE TIMESTAMPDIFF(MINUTE, NOW(), Start)<15 AND TIMESTAMPDIFF(MINUTE, NOW(), Start)>0 AND User_ID=?;";
            
            Query.setPreparedStatement(DB.getConnection(), selectStatement);
            PreparedStatement ps = Query.getPreparedStatement();
            
            ps.setInt(1, userId);
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            
            while(rs.next()){
                appt.setAppointmentId(rs.getInt("Appointment_ID"));
                appt.setTitle(rs.getString("Title"));
                appt.setDescription(rs.getString("Description"));
                appt.setLocation(rs.getString("Location"));
                appt.setType(rs.getString("Type"));
                ZonedDateTime zonedStart = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("Start"));
                appt.setStart(zonedStart);
                ZonedDateTime zonedEnd = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("End"));
                appt.setEnd(zonedEnd);
                ZonedDateTime zonedCreate = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("Create_Date"));
                appt.setCreateDate(zonedCreate);
                appt.setCreatedBy(rs.getString("Created_By"));
                ZonedDateTime zonedUpdate = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("Last_Update"));
                appt.setLastUpdate(zonedUpdate);
                appt.setLastUpdatedBy(rs.getString("Last_Updated_By"));
                appt.setCustomerId(rs.getInt("Customer_ID"));
                appt.setUserId(rs.getInt("User_ID"));
                appt.setContactId(rs.getInt("Contact_ID"));
                appt.formatAllDateTimes();
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return appt;
    }

    @Override
    public ObservableList<Appointment> getAll(){
        try{
            this.appointmentsList.clear();
            String selectStatement = "SELECT * FROM appointments;";
            
            Query.setPreparedStatement(DB.getConnection(), selectStatement);
            PreparedStatement ps = Query.getPreparedStatement();
            
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            
            while(rs.next()){
                ZonedDateTime zonedStart = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("Start"));
                ZonedDateTime zonedEnd = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("End"));
                ZonedDateTime zonedCreate = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("Create_Date"));
                ZonedDateTime zonedLastUpdate = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("Last_Update"));
                Appointment appt = new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"), rs.getString("Description"), rs.getString("Location"), rs.getString("Type"), 
                    zonedStart, zonedEnd, zonedCreate, rs.getString("Created_By"), zonedLastUpdate, rs.getString("Last_Updated_By"), rs.getInt("Customer_ID"), 
                    rs.getInt("User_ID"), rs.getInt("Contact_ID"));
                appt.formatAllDateTimes();
                this.appointmentsList.add(appt);
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return this.appointmentsList;
    }
    
    /**
     * Retrieves all of the appointments scheduled for the current week.
     * @return An ObservableList of appointments scheduled for the current week.
     */
    public ObservableList<Appointment> getAllByWeek(){
        appointmentsList.clear();        
        this.appointmentsList.clear();
        try{
            String selectStatement = "SELECT * FROM appointments WHERE WEEK(Start)=WEEK(CURDATE());";
            
            Query.setPreparedStatement(DB.getConnection(), selectStatement);
            PreparedStatement ps = Query.getPreparedStatement();
            
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            
            while(rs.next()){
                ZonedDateTime zonedStart = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("Start"));
                ZonedDateTime zonedEnd = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("End"));
                ZonedDateTime zonedCreate = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("Create_Date"));
                ZonedDateTime zonedLastUpdate = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("Last_Update"));
                Appointment appt = new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"), rs.getString("Description"), rs.getString("Location"), rs.getString("Type"), 
                    zonedStart, zonedEnd, zonedCreate, rs.getString("Created_By"), zonedLastUpdate, rs.getString("Last_Updated_By"), rs.getInt("Customer_ID"), 
                    rs.getInt("User_ID"), rs.getInt("Contact_ID"));
                appt.formatAllDateTimes();
                this.appointmentsList.add(appt);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return this.appointmentsList;
    }
    
    /**
     * Retrieves all of the appointments scheduled for the current month.
     * @return An ObservableList of appointments scheduled for the current month.
     */
    public ObservableList<Appointment> getAllByMonth(){
        appointmentsList.clear();
        try{
            String selectStatement = "SELECT * FROM appointments WHERE MONTH(Start)=MONTH(CURDATE());";
            
            Query.setPreparedStatement(DB.getConnection(), selectStatement);
            PreparedStatement ps = Query.getPreparedStatement();
                        
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            
            while(rs.next()){
                ZonedDateTime zonedStart = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("Start"));
                ZonedDateTime zonedEnd = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("End"));
                ZonedDateTime zonedCreate = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("Create_Date"));
                ZonedDateTime zonedLastUpdate = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("Last_Update"));
                Appointment appt = new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"), rs.getString("Description"), rs.getString("Location"), rs.getString("Type"), 
                    zonedStart, zonedEnd, zonedCreate, rs.getString("Created_By"), zonedLastUpdate, rs.getString("Last_Updated_By"), rs.getInt("Customer_ID"), 
                    rs.getInt("User_ID"), rs.getInt("Contact_ID"));
                appt.formatAllDateTimes();
                this.appointmentsList.add(appt);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return this.appointmentsList;
    }
    
    /**
     * Retrieves all of the Contact's appointments.
     * @param contactID The ID of the Contact.
     * @return An ObservableList of the Contact's appointments.
     */
    public ObservableList<Appointment> getAllByContact(int contactID){
        this.appointmentsList.clear();
        try{
            String selectStatement = "SELECT * FROM appointments WHERE Contact_ID=?;";
            
            Query.setPreparedStatement(DB.getConnection(), selectStatement);
            PreparedStatement ps = Query.getPreparedStatement();
            
            ps.setInt(1, contactID);
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            
            while(rs.next()){
                ZonedDateTime zonedStart = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("Start"));
                ZonedDateTime zonedEnd = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("End"));
                ZonedDateTime zonedCreate = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("Create_Date"));
                ZonedDateTime zonedLastUpdate = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("Last_Update"));
                Appointment appt = new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"), rs.getString("Description"), rs.getString("Location"), rs.getString("Type"), 
                    zonedStart, zonedEnd, zonedCreate, rs.getString("Created_By"), zonedLastUpdate, rs.getString("Last_Updated_By"), rs.getInt("Customer_ID"), 
                    rs.getInt("User_ID"), rs.getInt("Contact_ID"));
                appt.formatAllDateTimes();
                this.appointmentsList.add(appt);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        System.out.println(appointmentsList.size());
        return this.appointmentsList;
    }

    /**
     * Retrieves all of the customer's appointments.
     * @param customerId The ID of the Customer.
     * @return An ObservableList of the Customer's Appointments.
     */
    public ObservableList<Appointment> getAllByCustomer(int customerId){       
        this.appointmentsList.clear();
        try{
            String selectStatement = "SELECT * FROM appointments WHERE Customer_ID=?;";
            
            Query.setPreparedStatement(DB.getConnection(), selectStatement);
            PreparedStatement ps = Query.getPreparedStatement();
            
            ps.setInt(1, customerId);
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            
            while(rs.next()){
                ZonedDateTime zonedStart = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("Start"));
                ZonedDateTime zonedEnd = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("End"));
                ZonedDateTime zonedCreate = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("Create_Date"));
                ZonedDateTime zonedLastUpdate = TimeConversion.getZonedFromTimestamp(rs.getTimestamp("Last_Update"));
                Appointment appt = new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"), rs.getString("Description"), rs.getString("Location"), rs.getString("Type"), 
                    zonedStart, zonedEnd, zonedCreate, rs.getString("Created_By"), zonedLastUpdate, rs.getString("Last_Updated_By"), rs.getInt("Customer_ID"), 
                    rs.getInt("User_ID"), rs.getInt("Contact_ID"));
                appt.formatAllDateTimes();
                this.appointmentsList.add(appt);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return this.appointmentsList;
    }
    /**
     * Returns each month of the year that contains and appointment.
     * @return A list of integers representing the months in a year.
     */
    public ArrayList<Integer> getEachMonth(){
        String groupByMonthStatement = "SELECT MONTH(Start) AS Month FROM appointments GROUP BY MONTH(Start);";
        ArrayList<Integer> monthList = new ArrayList<Integer>();
        try{
            Query.setPreparedStatement(DB.getConnection(), groupByMonthStatement);
            PreparedStatement ps = Query.getPreparedStatement();
            
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            
            while(rs.next()){
                monthList.add(rs.getInt("Month"));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return monthList;
    }
    
    public ObservableList<String> getTypeCountPerMonth(int month){
        String groupByTypeStatemtent = "SELECT Type, COUNT(Type) AS Count FROM appointments WHERE MONTH(Start)=? GROUP BY Type ORDER BY COUNT(TYPE) DESC;";
        ObservableList<String> countList = FXCollections.observableArrayList();
        countList.add("Type Count");
        try{
            Query.setPreparedStatement(DB.getConnection(), groupByTypeStatemtent);
            PreparedStatement ps = Query.getPreparedStatement();

            ps.setInt(1, month);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            while(rs.next()){
                String type = rs.getString("Type");
                String count = String.valueOf(rs.getInt("Count"));
                countList.add(type+": "+count);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return countList;
    }
    
    @Override
    public int create(Appointment t){
        int rowsAffected = 0;
        try{
            String insertStatement = "INSERT INTO appointments(Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
            Query.setPreparedStatement(DB.getConnection(), insertStatement);
            PreparedStatement ps = Query.getPreparedStatement();
            
            ps.setString(1, t.getTitle());
            ps.setString(2, t.getDescription());
            ps.setString(3, t.getLocation());
            ps.setString(4, t.getType());
            Timestamp startTimestamp = TimeConversion.getUTCTimestamp(t.getStart());
            ps.setTimestamp(5, startTimestamp);
            Timestamp endTimestamp = TimeConversion.getUTCTimestamp(t.getEnd());
            ps.setTimestamp(6, endTimestamp);
            Timestamp createTimestamp = TimeConversion.getUTCTimestamp(t.getCreateDate());
            ps.setTimestamp(7, createTimestamp);
            ps.setString(8, t.getCreatedBy());
            Timestamp updateTimestamp = TimeConversion.getUTCTimestamp(t.getLastUpdate());
            ps.setTimestamp(9, updateTimestamp);
            ps.setString(10, t.getLastUpdatedBy());
            ps.setInt(11, t.getCustomerId());
            ps.setInt(12, t.getUserId());
            ps.setInt(13, t.getContactId());

            ps.execute();
            
            rowsAffected = ps.getUpdateCount();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return rowsAffected;
    }

    @Override
    public int update(Appointment t) {
        int rowsAffected = 0;
        try{
            String updateStatement = 
                    "UPDATE appointments "
                    + "SET Title=?, Description=?, Location=?, Type=?, Start=?, End=?, Create_Date=?, Created_By=?, Last_Update=?, Last_Updated_By=?, Customer_ID=?, User_ID=?, Contact_ID=? "
                    + "WHERE Appointment_ID=?;";
            Query.setPreparedStatement(DB.getConnection(), updateStatement);
            PreparedStatement ps = Query.getPreparedStatement();
            
            ps.setString(1, t.getTitle());
            ps.setString(2, t.getDescription());
            ps.setString(3, t.getLocation());
            ps.setString(4, t.getType());
            Timestamp startTimestamp = TimeConversion.getUTCTimestamp(t.getStart());
            ps.setTimestamp(5, startTimestamp);
            Timestamp endTimestamp = TimeConversion.getUTCTimestamp(t.getEnd());
            ps.setTimestamp(6, endTimestamp);
            Timestamp createTimestamp = TimeConversion.getUTCTimestamp(t.getCreateDate());
            ps.setTimestamp(7, createTimestamp);
            ps.setString(8, t.getCreatedBy());
            Timestamp updateTimestamp = TimeConversion.getUTCTimestamp(t.getLastUpdate());
            ps.setTimestamp(9, updateTimestamp);
            ps.setString(10, t.getLastUpdatedBy());
            ps.setInt(11, t.getCustomerId());
            ps.setInt(12, t.getUserId());
            ps.setInt(13, t.getContactId());
            ps.setInt(14, t.getAppointmentId());
            
            ps.execute();
            
            rowsAffected = ps.getUpdateCount();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return rowsAffected;
    }

    @Override
    public int delete(Appointment t) {
        int rowsAffected = 0;
        try{
            String deleteStatement = "DELETE FROM appointments WHERE Appointment_ID=?";
            
            Query.setPreparedStatement(DB.getConnection(), deleteStatement);
            PreparedStatement ps = Query.getPreparedStatement();
            
            ps.setInt(1, t.getAppointmentId());
            
            ps.execute();
            
            rowsAffected = ps.getUpdateCount();            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return rowsAffected;
    }
    
    /**
     * Deletes all of a Customer's Appointments. Used when deleting a Customer record.
     * @param customerID The ID of the Customer.
     * @return The number of rows affected by the delete query.
     */
    public int deleteByCustomerID(int customerID){
        int rowsAffected = 0;
        try{
            String deleteStatement = "DELETE FROM appointments WHERE Customer_ID=?;";
            
            Query.setPreparedStatement(DB.getConnection(), deleteStatement);
            PreparedStatement ps = Query.getPreparedStatement();
            
            ps.setInt(1, customerID);
            ps.execute();
            
            rowsAffected = ps.getUpdateCount();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return rowsAffected;
    }
    
    /**
     * Retrieves each Type of Appointment and the total number of each Type.
     * @return An object containing each Type of Appointment and the number of each Type.
     */
    public ObservableList<GroupCount> totalByType(){
        ObservableList<GroupCount> groupCountList = FXCollections.observableArrayList();
        
        try{     
            String selectStatement = "SELECT Type, COUNT(*) AS Count FROM appointments GROUP BY Type ORDER BY Count DESC;";
            
            Query.setPreparedStatement(DB.getConnection(), selectStatement);
            PreparedStatement ps = Query.getPreparedStatement();
            
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            
            while(rs.next()){
                groupCountList.add(new GroupCount(rs.getString("Type"), rs.getInt("Count")));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return groupCountList;
    }
    
    /**
     * Retrieves each Month with an Appointment and the total number of Appointments in each Month.
     * @return An object containing each Month with an Appointment and the total number of Appointments in each Month.
     */    
    public ObservableList<GroupCount> totalByMonth(){
        ObservableList<GroupCount> groupCountList = FXCollections.observableArrayList();
        
        try{     
            String selectStatement = "SELECT MONTHNAME(Start) AS Month, COUNT(MONTHNAME(Start)) AS Count FROM appointments GROUP BY MONTHNAME(Start) ORDER BY Count DESC;";
            
            Query.setPreparedStatement(DB.getConnection(), selectStatement);
            PreparedStatement ps = Query.getPreparedStatement();
            
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            
            while(rs.next()){
                groupCountList.add(new GroupCount(rs.getString("Month"), rs.getInt("Count")));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return groupCountList;
    }    
    
    /**
     * Retrieves each Hour with an Appointment scheduled in it and the total number of Appointments in each hour.
     * @return An object containing each Hour with an Appointment scheduled in it and the total number of Appointments in each hour.
     */    
    public ObservableList<GroupCount> totalByTime(){
        ObservableList<GroupCount> groupCountList = FXCollections.observableArrayList();
        
        try{
            String selectStatement = "SELECT HOUR(Start)-5 AS Time, COUNT(HOUR(Start)-5) AS Count FROM appointments GROUP BY HOUR(Start)-5;";
            
            Query.setPreparedStatement(DB.getConnection(), selectStatement);
            PreparedStatement ps = Query.getPreparedStatement();
            
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            
            while(rs.next()){
                groupCountList.add(new GroupCount(rs.getString("Time"), rs.getInt("Count")));
            }
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return groupCountList;
    }
}
