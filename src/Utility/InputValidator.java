/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import DAOImplementation.AppointmentImpl;
import Model.Appointment;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

/**
 * A class that validates the input from various kinds of UI controls.
 * @author KC Green
 */
public class InputValidator {
    /**
     * A ResourceBundle object used for localization between English and French.
     */
    private static ResourceBundle rb = ResourceBundle.getBundle("scheduler.l10n", Locale.getDefault());    
    
    /**
     * Determines if any of an arbitrary number of TextFields are empty. Displays an error for any that are empty.
     * @param tf An arbitrary number of TextFields.
     * @return The boolean value of whether any TextFields were empty.
     */
    public static boolean isEmpty(TextField... tf){
        boolean empty = false;
        for(TextField t : tf){
            if(t.getText().isBlank()){
                t.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
                t.setPromptText(rb.getString("missingField"));     
                empty = true;
            }else{
                t.setStyle("-fx-text-box-border: black; -fx-focuc-color: blue;");
                t.setPromptText("");
            }
        }
        return empty;
    }
    
    /**
     * Determines if a DatePicker is empty. Displays an error message if the DatePicker is empty.
     * @param errorLabel The Label on which any errors will be displayed.
     * @param dp The DatePicker that will be validated.
     * @return The boolean value of whether the DatePicker was empty.
     */
    public static boolean isEmpty(Label errorLabel, DatePicker dp){
        boolean empty = false;
            if(dp.getValue().equals(null)){
                dp.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
                errorLabel.setText(rb.getString("noDateSelected"));  
                empty = true;            
            }else{
                    dp.setStyle("-fx-text-box-border: black; -fx-focuc-color: blue;");
                    errorLabel.setText("");
            }            
        return empty;
    }
    
    /**
     * Determine if any of an arbitrary number of Spinners are empty. Display a message for any Spinners that are empty.
     * @param errorLabel The label on which any error will be displayed.
     * @param sp An arbitrary number of Spinner objects.
     * @return The boolean value of whether any Spinners were empty.
     */
    public static boolean isEmpty(Label errorLabel, Spinner sp){
        boolean empty = false;
            if(sp.getValue().equals("")){
                System.out.println(empty);
                errorLabel.setText(rb.getString("noTimeSelected"));
                empty = true;
            }else{
                sp.setStyle("-fx-text-box-border: black; -fx-focuc-color: blue;");
                errorLabel.setText("");
            }
        return empty;        
    }
    
    /**
     * Determines if a TextField contains an integer. Displays an error if the TextField does not display an integer.
     * @param tf An arbitrary number of TextFields.
     * @return The boolean value of whether any TextFields contained an integer.
     */
    public static boolean isInt(TextField... tf){
        boolean isInt = true;
        for(TextField t : tf){
            try{
                Integer.parseInt(t.getText());
            }catch(NumberFormatException e){
                t.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
                t.setPromptText(rb.getString("missingField"));
                isInt = false;
            }
        }
        return isInt;
    }
    
    /**
     * Determines if there is nothing selected from an arbitrary number of ComboBoxes. Displays an error if nothing is selected.
     * @param lb The label on which any errors will be displayed.
     * @param cb An arbitrary number of ComboBoxes.
     * @return The boolean value of whether any of the ComboBoxes had nothing selected.
     */
    public static boolean nothingSelected(Label lb, ComboBox... cb){
        boolean nothingSelected = false;
        for(ComboBox c : cb){
            if(c.getSelectionModel().isEmpty()){
                nothingSelected = true;
                lb.setText("Please select a "+c.getPromptText());
                return nothingSelected;
            }
        }
        return nothingSelected;
    }
    
    /**
     * Determines if a given ZonedDateTime is during the office hours of the company(EST)
     * @param zdt A ZonedDateTime object
     * @param errorLabel A Label on which any errors will be displayed.
     * @return The boolean value of whether the ZonedDateTime is during the office hours of the company.
     */
    public static boolean isDuringOfficeHours(ZonedDateTime zdt, Label errorLabel){
        // Office hours are between 8am and 10pm 7 days a week
        ZonedDateTime estZdt = TimeConversion.getOfficeTime(zdt);
        int estHour = estZdt.getHour();
        if(estHour >= 8 && estHour <= 22){
            return true;
        }else{
            errorLabel.setText(rb.getString("outsideOfficeHours"));
            return false;
        }
    }
    
    /**
     * Determines if the given ZonedDateTime object is in the future. Displays an error if the ZonedDateTime is not in the future.
     * @param zdt A ZonedDateTime Object.
     * @param errorLabel A label on which any errors will be displayed.
     * @return The boolean value of whether the ZonedDateTime object is in the future.
     */
    public static boolean isInFuture(ZonedDateTime zdt, Label errorLabel){
        if(zdt.isBefore(ZonedDateTime.now(ZoneId.systemDefault()))){
            errorLabel.setText(rb.getString("futureTime"));
            return false;
        }else{
            return true;
        }
    }
    
    /**
     * Determines if the startDateTime is before the endDateTime. Displays an error if this is not true.
     * @param startDateTime The time at which an appointment will start.
     * @param endDateTime The time at which an appointment will end.
     * @param errorLabel A label on which any errors will be displayed.
     * @return The boolean value of whether the start is before the end time.
     */
    public static boolean startIsBeforeEnd(ZonedDateTime startDateTime, ZonedDateTime endDateTime, Label errorLabel){
        if(startDateTime.isBefore(endDateTime)){
            return true;
        }else{
            errorLabel.setText(rb.getString("beforeEnd"));
            return false;
        }
    }
    
    /**
     * Determines if the Customer is available at the given Appointment time. Displays an error if the customer is not available.
     * @param zdt A ZonedDateTime object of when the appointment is scheduled to start.
     * @param customerId The ID of the customer involved with the appointment.
     * @param errorLabel A label on which any errors will be displayed.
     * @return The boolean value of whether the customer is available at the given time.
     */
    public static boolean customerAvailable(ZonedDateTime zdt, int customerId, Label errorLabel){
        boolean available = true;
        errorLabel.setText("");
        AppointmentImpl apptImpl = new AppointmentImpl();
        ObservableList<Appointment> appts = apptImpl.getAllByCustomer(customerId);
        for(Appointment appt: appts){
            if(appt.getStart().equals(zdt)){
                available = false;
                errorLabel.setText(rb.getString("custNotAvail"));
            }
        }
        return available;
    }
}
