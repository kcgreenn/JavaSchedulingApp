/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import DAOImplementation.AppointmentImpl;
import DAOImplementation.ContactImpl;
import DAOImplementation.CustomerImpl;
import DAOImplementation.UserImpl;
import Model.Appointment;
import Model.Contact;
import Model.Customer;
import Model.User;
import Utility.InputValidator;
import Utility.LoginHandler;
import Utility.TimeConversion;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class for the UpdateAppointment scene.
 *
 * @author KC Green
 */
public class UpdateAppointmentController implements Initializable {
    /**
     * A TextField for the user to enter the Appointment ID.
     */    
    @FXML
    private TextField idField;
    /**
     * A TextField for the user to enter the Appointment Title.
     */    
    @FXML
    private TextField titleField;
    /**
     * A TextField for the user to enter the Appointment Description.
     */    
    @FXML
    private TextField descField;
    /**
     * A TextField for the user to enter the Appointment Location.
     */    
    @FXML
    private TextField locationField;
    /**
     * A TextField for the user to enter the Appointment Type.
     */
    @FXML
    private TextField typeField;
    /**
     * A ComboBox for the User to select the Appointment's Contact ID.
     */    
    @FXML
    private ComboBox<String> contactBox;
    /**
     * A ComboBox for the User to select the Appointment's Customer ID.
     */    
    @FXML
    private ComboBox<String> custBox;
    /**
     * The cancel button used to cancel the Appointment update and return the user to the dashboard scene.
     */
    @FXML
    private Button cancelBtn;
    /**
     * The create button used to confirm the Appointment update and return the user to the dashboard scene.
     */
    @FXML
    private Button createBtn;
    /**
     * A Label for displaying any errors or custom messages to the user.
     */
    @FXML
    private Label messageDisplayLabel;
    /**
     * A DatePicker for the user to select the Appointment's Start Time.
     */        
    @FXML
    private DatePicker startDatePicker;
    /**
     * A DatePicker for the user to select the Appointment's End Time.
     */    
    @FXML
    private DatePicker endDatePicker;
    /**
     * A ComboBox for the User to select the Appointment's User ID.
     */
    @FXML
    private ComboBox<String> userBox;
    /**
     * A Spinner for the User to input the hours of the Appointment Start Time.
     */    
    @FXML
    private Spinner<Integer> startHourSpinner;
    /**
     * A Spinner for the User to input the minutes of the Appointment Start Time.
     */    
    @FXML
    private Spinner<Integer> startMinuteSpinner;
    /**
     * A Spinner for the User to input the hours of the Appointment End Time.
     */    
    @FXML
    private Spinner<Integer> endHourSpinner;
    /**
     * A Spinner for the User to input the minutes of the Appointment End Time.
     */
    @FXML
    private Spinner<Integer> endMinuteSpinner;    
    /**
     * A JavaFX Stage object.
     */
    Stage stage;
    /**
     * A JavaFX Parent object.
     */
    Parent scene;
    /**
     * The currently logged in user.
     */
    User currentUser = LoginHandler.getCurrentUser();
    /**
     * The appointment selected by the user to be updated.
     */
    Appointment updatingAppt;
    /**
     * A localization ResourceBundle for both english and french.
     */
    ResourceBundle rb = ResourceBundle.getBundle("scheduler.l10n", Locale.getDefault());
    /**
     * An instance of the ContactImplementation class.
     */    
    ContactImpl contImpl = new ContactImpl();
    /**
     * An instance of the CustomerImplementation class.
     */    
    CustomerImpl custImpl = new CustomerImpl();
    /**
     * An instance of the UserImplementation class.
     */    
    UserImpl userImpl = new UserImpl();
    /**
     * An instance of the AppointmentImplementation class.
     */    
    AppointmentImpl apptImpl = new AppointmentImpl();
    /**
     * An ObservableList containing all Contacts for the Contact ComboBox.
     */
    ObservableList<Contact> contacts = FXCollections.observableArrayList();
    /**
     * An ObservableList containing all Customers for the Customer ComboBox.
     */    
    ObservableList<Customer> customers = FXCollections.observableArrayList();
    /**
     * An ObservableList containing all Users for the User ComboBox.
     */    
    ObservableList<User> users = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     * @param url Uniform Resource Locator
     * @param rb Resource Bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Fill combobox options
        generateContactOptions();
        generateCustomerOptions();
        generateUserOptions();
    }    
    
    /**
     * Receives the appointment that will be updated and the currently logged in user. Used to prepopulate TextFields, ComboBoxes, and DatePickers.
     * @param appt The appointment selected by the user to be updated.
     * @param currentUser The user that is currently logged in.
     */
    public void sendAppointmentAndUser(Appointment appt, User currentUser){
        this.currentUser = currentUser;
        this.updatingAppt = appt;
        
        // Fill fields with appointment to be updated.
        this.idField.setText(String.valueOf(updatingAppt.getAppointmentId()));
        this.titleField.setText(updatingAppt.getTitle());
        this.descField.setText(updatingAppt.getDescription());
        this.locationField.setText(updatingAppt.getLocation());
        this.typeField.setText(updatingAppt.getType());
        
        // Select appointment data from combo boxes.
        Contact cont = contImpl.get(updatingAppt.getContactId());
        String formedCont = String.valueOf(cont.getId())+"\t"+cont.getName();
        User user = userImpl.get(updatingAppt.getUserId());
        String formedUser = String.valueOf(user.getId()+"\t"+user.getName());
        Customer cust = custImpl.get(updatingAppt.getCustomerId());
        String formedCust = String.valueOf(cust.getId()+"\t"+cust.getName());
        
        this.contactBox.getSelectionModel().select(formedCont);
        this.custBox.getSelectionModel().select(formedCust);
        this.userBox.getSelectionModel().select(formedUser);
        
        // Set dates and times
        this.startDatePicker.setValue(updatingAppt.getStart().toLocalDate());
        this.endDatePicker.setValue(updatingAppt.getEnd().toLocalDate());
        int startHour = updatingAppt.getStart().getHour();
        int startMinute = updatingAppt.getStart().getMinute();
        int endHour = updatingAppt.getEnd().getHour();
        int endMinute = updatingAppt.getEnd().getMinute();
        this.startHourSpinner.getValueFactory().setValue(startHour);
        this.startMinuteSpinner.getValueFactory().setValue(startMinute);
        this.endHourSpinner.getValueFactory().setValue(endHour);
        this.endMinuteSpinner.getValueFactory().setValue(endMinute);        
    }
    
    /**
     * Get all contacts from database and generate Contact options for the Contact ComboBox.
     */    
    public void generateContactOptions(){
        contacts = contImpl.getAll();
        
        ObservableList<String> options = FXCollections.observableArrayList();
        contacts.forEach(c-> options.add(c.getId()+"\t"+c.getName()));
        contactBox.setItems(options);
    } 

    /**
     * Get all customers from database and generate Customer options for the Customer ComboBox.
     */    
    public void generateCustomerOptions(){
        customers = custImpl.getAll();
        
        ObservableList<String> options = FXCollections.observableArrayList();
        customers.forEach(c-> options.add(c.getId()+"\t"+c.getName()));
        custBox.setItems(options);
    }     

    /**
     * Get all users from database and generate User options for the User ComboBox.
     */
    public void generateUserOptions(){
        users = userImpl.getAll();
        
        ObservableList<String> options = FXCollections.observableArrayList();
        users.forEach(c-> options.add(c.getId()+"\t"+c.getName()));
        userBox.setItems(options);
    }       

    /**
     * Not Implemented
     * @param event The Action event.
     */
    @FXML
    private void handleContactBox(ActionEvent event) {
    }

    /**
     * Not Implemented
     * @param event The Action event.
     */
    @FXML
    private void handleCustBox(ActionEvent event) {
    }

    /**
     * Handle the cancel button mouse click, sending the user back to the Dashboard scene.
     * @param event The Mouse click event.
     */
    @FXML
    private void handleCancelClick(MouseEvent event) {
        try{
            // Confirm Cancel Action
            Alert cancelAlert = new Alert(Alert.AlertType.CONFIRMATION);
            cancelAlert.headerTextProperty().set(rb.getString("confirmCancel"));
        
            Optional<ButtonType> result = cancelAlert.showAndWait();
        
            if(result.isPresent() && result.get() == ButtonType.OK){
                // Return to main screen if user confirms cancel

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/View_Controller/Dashboard.fxml"));
                loader.load();

                DashboardController DController = loader.getController();
                DController.sendUser(LoginHandler.getCurrentUser());

                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                Parent scene = loader.getRoot();
                stage.setScene(new Scene(scene));
                stage.show();                
            }        
        }catch(IOException e){
            System.out.println(e.getMessage());
        }          
    }

    /**
     * Handles the create button click, sending an UPDATE query to the database.
     * @param event The mouse click event
     */
    @FXML
    private void handleCreateClick(MouseEvent event) {
        TextField[] tf = {titleField, typeField, descField, locationField};
        ComboBox[] cb = {this.custBox, this.contactBox, this.userBox};
        DatePicker[] dp = {startDatePicker, endDatePicker};
        Spinner[] sp = {startHourSpinner, startMinuteSpinner, endHourSpinner, endMinuteSpinner};
        int rowsAffected = 0;
        // Validate user input and create new customer in DB
        if(!InputValidator.isEmpty(tf)
            && !InputValidator.nothingSelected(messageDisplayLabel, cb)){
            
            ZonedDateTime zonedStartDateTime = TimeConversion.getDateTimeInput(startDatePicker.getValue(), startHourSpinner.getValue(), startMinuteSpinner.getValue());
            ZonedDateTime zonedEndDateTime = TimeConversion.getDateTimeInput(endDatePicker.getValue(), endHourSpinner.getValue(), endMinuteSpinner.getValue());
            // Validate Appointment Times
            if(InputValidator.isDuringOfficeHours(zonedStartDateTime, messageDisplayLabel) 
                    && InputValidator.isDuringOfficeHours(zonedEndDateTime, messageDisplayLabel) 
                    && InputValidator.isInFuture(zonedStartDateTime, messageDisplayLabel)
                    && InputValidator.startIsBeforeEnd(zonedStartDateTime, zonedEndDateTime, messageDisplayLabel)){

                Appointment newAppt = new Appointment();
                newAppt.setAppointmentId(Integer.parseInt(idField.getText()));
                newAppt.setTitle(titleField.getText());
                newAppt.setType(typeField.getText());
                newAppt.setDescription(descField.getText());
                newAppt.setLocation(locationField.getText());
                newAppt.setCreateDate(ZonedDateTime.now(ZoneId.systemDefault()));
                newAppt.setCreatedBy(this.currentUser.getName());
                newAppt.setLastUpdate(ZonedDateTime.now(ZoneId.systemDefault()));
                newAppt.setLastUpdatedBy(this.currentUser.getName());
                // Get selections from combo boxes
                newAppt.setContactId(Integer.parseInt(contactBox.getSelectionModel().getSelectedItem().substring(0, 2).stripTrailing()));
                newAppt.setUserId(Integer.parseInt(userBox.getSelectionModel().getSelectedItem().substring(0, 2).stripTrailing()));
                newAppt.setCustomerId(Integer.parseInt(custBox.getSelectionModel().getSelectedItem().substring(0, 2).stripTrailing()));
                // Get date and time from picker and spinners
                newAppt.setStart(zonedStartDateTime);
                newAppt.setEnd(zonedEndDateTime);
                newAppt.formatAllDateTimes();

                rowsAffected = apptImpl.update(newAppt);    
            }
        }
        // Return to dashboard if successful
        if(rowsAffected > 0){          
            try{
                // Return to dashboard
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/View_Controller/Dashboard.fxml"));
                loader.load();

                DashboardController DController = loader.getController();
                DController.sendUser(LoginHandler.getCurrentUser());

                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                Parent scene = loader.getRoot();
                stage.setScene(new Scene(scene));
                stage.show();
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        }else{
            messageDisplayLabel.setText(rb.getString("update"));
        }          
    }

    /**
     * Set the end Date picker equal to the start date picked by the user.
     * @param event The Action event.
     */
    @FXML
    private void handleStartDatePick(ActionEvent event) {
        this.endDatePicker.setValue(this.startDatePicker.getValue());
    }

    /**
     * Not implemented.
     * @param event The Action event.
     */
    @FXML
    private void handleEndDatePick(ActionEvent event) {
    }

    /**
     * Not implemented.
     * @param event The Action event.
     */
    @FXML
    private void handleUserBox(ActionEvent event) {
    }
    
}
