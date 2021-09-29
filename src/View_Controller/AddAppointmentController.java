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
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import Utility.DateTimeInput;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class for the Add Appointment scene
 *
 * @author KC Green
 */
public class AddAppointmentController implements Initializable {

    @FXML
    private TextField idField;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button createBtn;
    @FXML
    private Label messageDisplayLabel;
    @FXML
    private TextField titleField;
    @FXML
    private TextField descField;
    @FXML
    private TextField locationField;
    @FXML
    private TextField typeField;
    @FXML
    private ComboBox<String> contactBox;
    @FXML
    private ComboBox<String> custBox;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private ComboBox<String> userBox;
    @FXML
    private Spinner<Integer> startHourSpinner;
    @FXML
    private Spinner<Integer> startMinuteSpinner;
    @FXML
    private Spinner<Integer> endHourSpinner;
    @FXML
    private Spinner<Integer> endMinuteSpinner;    
    
    Stage stage;
    Parent scene;
    
    User currentUser;
    
    ContactImpl contImpl = new ContactImpl();
    CustomerImpl custImpl = new CustomerImpl();
    UserImpl userImpl = new UserImpl();
    AppointmentImpl apptImpl = new AppointmentImpl();
    
    ResourceBundle rb = ResourceBundle.getBundle("scheduler.l10n", Locale.getDefault());
    
    ObservableList<Contact> contacts = FXCollections.observableArrayList();
    ObservableList<Customer> customers = FXCollections.observableArrayList();
    ObservableList<User> users = FXCollections.observableArrayList();

    

    /**
     * Initializes the controller class for the AddAppointmentController.
     * @param url Uniform Resource Locator
     * @param rb Resource Bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.idField.focusTraversableProperty().setValue(false);
        // Fill combobox options
        generateContactOptions();
        generateCustomerOptions();
        generateUserOptions();
        // Set DatePickers to today's date
        startDatePicker.setValue(LocalDate.now());
        endDatePicker.setValue(LocalDate.now());
    }    
    
    DateTimeInput dti = (ld, hour, minute) -> {
        LocalTime localTime = LocalTime.of(hour, minute, 0);
        LocalDateTime localDateTime = LocalDateTime.of(ld, localTime);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());     
        return zonedDateTime;
    };    
    
    /**
     * Send the currently logged in user to this scene, to customize the UI.
     * @param currentUser The user that is currently logged in.
     */
    public void sendUser(User currentUser){
        this.currentUser = currentUser;
    }
     /** 
     * Generates the options for the Contact ComboBox. 
     * <p><b>I used a lambda expression in this controller to generate the options for the ComboBoxes. I iterated through 
     * the Contact, Customer, and User lists and added their ID's and Names to the options of each ComboBox. This is a far more concise syntax than using a for loop and it makes it much easier to 
     * read the code. Used in the generateContactOptions, generateCustomerOptions and generateUserOptions methods.</b></p>
     */
    public void generateContactOptions(){
        contacts = contImpl.getAll();
        
        ObservableList<String> options = FXCollections.observableArrayList();
        contacts.forEach(c-> options.add(c.getId()+"\t"+c.getName()));
        contactBox.setItems(options);
    } 
    
    /**
     * Generates the options for the Customer ComboBox.
     */
    public void generateCustomerOptions(){
        customers = custImpl.getAll();
        
        ObservableList<String> options = FXCollections.observableArrayList();
        customers.forEach(c-> options.add(c.getId()+"\t"+c.getName()));
        custBox.setItems(options);
    }     

    /**
     * Generates the options for the user ComboBox.
     */
    public void generateUserOptions(){
        users = userImpl.getAll();
        
        ObservableList<String> options = FXCollections.observableArrayList();
        users.forEach(c-> options.add(c.getId()+"\t"+c.getName()));
        userBox.setItems(options);
    }         

    /**
     * Handles the Mouse Click event on the Cancel Button.
     * @param event The Mouse Click event.
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
     * Handles the mouse click event on the Create Button. 
     * <p><b>I used a lambda expression to parse user input from the DatePickers and Spinners to get a ZonedDateTime. Using a lambda 
     * expression made the code more readable and concise. It also made it easier to get all the values from the user inputs, since I parsed the values in the lambda rather than
     * doing it repeatedly for each input.</b></p>
     * @param event The mouse click event.
     */
    @FXML
    private void handleCreateClick(MouseEvent event) {
        TextField[] tf = {titleField, typeField, descField, locationField};
        ComboBox[] cb = {this.custBox, this.contactBox, this.userBox};
        DatePicker[] dp = {startDatePicker, endDatePicker};
        Spinner[] sp = {startHourSpinner, startMinuteSpinner, endHourSpinner, endMinuteSpinner};
        int rowsAffected = 0;
        // Validate user input and create new customer in DB
        if(!InputValidator.isEmpty(tf) && !InputValidator.nothingSelected(messageDisplayLabel, cb)){
            // Get user selections from combo boxes and datepickers
            int selectedContact = Integer.parseInt(contactBox.getSelectionModel().getSelectedItem().substring(0, 2).stripTrailing());
            int selectedCustomer = Integer.parseInt(custBox.getSelectionModel().getSelectedItem().substring(0, 2).stripTrailing());
            int selectedUser = Integer.parseInt(userBox.getSelectionModel().getSelectedItem().substring(0, 2).stripTrailing());
            
            // Lambda Expression
            ZonedDateTime zonedStartDateTime = dti.gather(startDatePicker.getValue(), startHourSpinner.getValue(), startMinuteSpinner.getValue());
            ZonedDateTime zonedEndDateTime = dti.gather(endDatePicker.getValue(), endHourSpinner.getValue(), endMinuteSpinner.getValue());
            // Validate Appointment Times
            if(!InputValidator.isEmpty(messageDisplayLabel, startDatePicker)
                    && !InputValidator.isEmpty(messageDisplayLabel, endDatePicker)
                    && InputValidator.isDuringOfficeHours(zonedStartDateTime, messageDisplayLabel) 
                    && InputValidator.isDuringOfficeHours(zonedEndDateTime, messageDisplayLabel) 
                    && InputValidator.isInFuture(zonedStartDateTime, messageDisplayLabel)
                    && InputValidator.startIsBeforeEnd(zonedStartDateTime, zonedEndDateTime, messageDisplayLabel)
                    && InputValidator.customerAvailable(zonedStartDateTime, selectedCustomer, messageDisplayLabel)){
            
                Appointment newAppt = new Appointment();
                newAppt.setTitle(titleField.getText());
                newAppt.setType(typeField.getText());
                newAppt.setDescription(descField.getText());
                newAppt.setLocation(locationField.getText());
                newAppt.setCreateDate(ZonedDateTime.now(ZoneId.systemDefault()));
                newAppt.setCreatedBy(this.currentUser.getName());
                newAppt.setLastUpdate(ZonedDateTime.now(ZoneId.systemDefault()));
                newAppt.setLastUpdatedBy(this.currentUser.getName());
                // Get selections from combo boxes
                newAppt.setContactId(selectedContact);
                newAppt.setUserId(selectedUser);
                newAppt.setCustomerId(selectedCustomer);
                // Get date and time from picker and spinners
                newAppt.setStart(zonedStartDateTime);
                newAppt.setEnd(zonedEndDateTime);
                newAppt.formatAllDateTimes();

                rowsAffected = apptImpl.create(newAppt);                    
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
        }           
    }

    /**
     * Not Implemented
     * @param event The ActionEvent
     */
    @FXML
    private void handleContactBox(ActionEvent event) {
    }

    /**
     * Not Implemented
     * @param event The Action Event.
     */
    @FXML
    private void handleCustBox(ActionEvent event) {
    }

    /**
     * Sets the End Date Picker value equal to the Start Date that the user selected.
     * @param event The Action Event.
     */
    @FXML
    private void handleStartDatePick(ActionEvent event) {
        this.endDatePicker.setValue(this.startDatePicker.getValue());
    }

    /**
     * Not implemented.
     * @param event The Action Event
     */
    @FXML
    private void handleEndDatePick(ActionEvent event) {
    }

    /**
     * Not Implemented
     * @param event The Action Event.
     */
    @FXML
    private void handleUserBox(ActionEvent event) {
    }

}
