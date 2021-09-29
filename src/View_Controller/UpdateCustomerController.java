/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import DAOImplementation.CountryImpl;
import DAOImplementation.CustomerImpl;
import DAOImplementation.DivisionImpl;
import Model.Country;
import Model.Customer;
import Model.Division;
import Model.User;
import Utility.InputValidator;
import Utility.LoginHandler;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class of the UpdateCustomer scene.
 *
 * @author KC Green
 */
public class UpdateCustomerController implements Initializable {
    /**
     * A TextField for the Customer ID. Not editable by the user.
     */
    @FXML
    private TextField idField;
    /**
     * A TextField for the Customer Name.
     */
    @FXML
    private TextField nameField;
    /**
     * A TextField for the Customer Address.
     */
    @FXML
    private TextField addrField;
    /**
     * A TextField for the Customer Postal Code.
     */
    @FXML
    private TextField postalField;
    /**
     * A TextField for the Customer Phone Number.
     */
    @FXML
    private TextField phoneField;
    /**
     * A ComboBox for the Customer Country.
     */
    @FXML
    private ComboBox<String> countryBox;
    /**
     * A ComboBox for the Customer Division.
     */
    @FXML
    private ComboBox<String> divBox;
    /**
     * A button to cancel the Customer Update and return to the dashboard.
     */
    @FXML
    private Button cancelBtn;
    /**
     * A button to confirm the Customer Update and return to the dashboard.
     */
    @FXML
    private Button updateBtn;
    /**
     * A Label used to display any errors or custom messages to the user.
     */
    @FXML
    private Label messageDisplayLabel;
    /**
     * A JavaFX Stage object.
     */
    Stage stage;
    /**
     * A JavaFX Parent object.
     */
    Parent scene;
    /**
     * An instance of the CountryImplementation class.
     */
    CountryImpl cntryImpl = new CountryImpl();    
    /**
     * An instance of the CustomerImplementation class.
     */    
    CustomerImpl custImpl = new CustomerImpl();
    /**
     * An ObservableList containing all countries from the database.
     */
    ObservableList<Country> countries = FXCollections.observableArrayList();    
    /**
     * An ObservableList containing all division in the selected country from the database.
     */    
    ObservableList<Division> divisions = FXCollections.observableArrayList();
    /**
     * The currently logged in user.
     */
    User currentUser = LoginHandler.getCurrentUser();
    /**
     * The Customer that was selected to be updated.
     */
    Customer updatedCustomer;
    /**
     * The customer's Division
     */
    Division custDiv;
    /**
     * The Customer's Country.
     */
    Country custCntry;
    /**
     * A localization ResourceBundle for both english and french.
     */
    ResourceBundle rb = ResourceBundle.getBundle("scheduler.l10n", Locale.getDefault());
    /**
     * The ID of the Division that was selected.
     */
    private int divID;     

    /**
     * Initializes the controller class.
     * @param url Uniform Resource Locator.
     * @param r Resource Bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle r) {
        // Remove auto-focus from the id field and set its id value
        idField.setFocusTraversable(false);

        //Generate Country and Division combo box options
        countryBox.setPromptText(rb.getString("country"));
        generateCountryOptions();
        divBox.setPromptText(rb.getString("state"));
    }    
    
    /**
     * Receives the customer selected by the user to be updated, and prepopulates the TextFields with customer information.
     * @param customer The customer selected by the user to be updated.
     */
    public void sendCustomer(Customer customer){
        DivisionImpl divImpl = new DivisionImpl();
        this.updatedCustomer = customer;
        // Input customer data into text fields
        idField.setText(Integer.toString(updatedCustomer.getId()));
        nameField.setText(updatedCustomer.getName());
        addrField.setText(updatedCustomer.getAddress());
        postalField.setText(updatedCustomer.getPostalCode());
        phoneField.setText(updatedCustomer.getPhone());
        
        // Get country name and select from country combo box
        custDiv = divImpl.get(updatedCustomer.getDivisionId());
        custCntry = this.cntryImpl.get(custDiv.getCountryId());
        countryBox.setValue(custCntry.getName());
        
        // Get division name and select from division combo box
        this.getDivisionsFromCountry();
        this.divBox.setValue(custDiv.getName());
    }
    
    /**
     * Gets all Countries and generates options for the Country ComboBox.
     */
    public void generateCountryOptions(){
        countries = cntryImpl.getAll();

        ObservableList<String> options = FXCollections.observableArrayList();
        countries.forEach(c->{
            options.add(c.getName());
        });
        countryBox.setItems(options);
    }  

    /**
     * Gets all First_Level_Division and generates options for the Division ComboBox.
     */    
    public void generateDivOptions(){
        ObservableList<String> options = FXCollections.observableArrayList();
        this.divisions.forEach(d->{
            options.add(d.getName());
        });
        
        divBox.setItems(options);
    }       

    /**
     * When the user selects an option from the Country ComboBox, all Divisions in that Country are queried from database.
     * @param event The ComboBox selection event.
     */
    @FXML
    private void handleCountryBox(ActionEvent event) {
        this.getDivisionsFromCountry();
    }
    
    /**
     * Gets all Divisions from the Country selected by the User, than generates the Division options for the Division ComboBox.
     */
    private void getDivisionsFromCountry(){
        DivisionImpl divImpl = new DivisionImpl();
        String selectedCountry = countryBox.getSelectionModel().getSelectedItem();
        countries.forEach(c->{
            if(c.getName().equals(selectedCountry)){
                divisions = divImpl.getAllByCountry(c.getId());
            }
        });
        this.generateDivOptions();          
    }

    /**
     * Handles the selection of a Division from the Division ComboBox.
     * @param event The ComboBox selection event.
     */
    @FXML
    private void handleDivBox(ActionEvent event) {
        String selectedDiv = divBox.getSelectionModel().getSelectedItem();
        this.divisions.forEach(d->{
            if(d.getName().equals(selectedDiv)){
                this.divID = d.getId();
            }
        });        
    }

    /**
     * Handles the Cancel Button click event. Returns the user to the Dashboard scene.
     * @param event The mouse click event.
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
     * Handles the clicking of the Update Button. Sends an Update query to the database, with the information entered by the user.
     * @param event The mouse click event.
     */
    @FXML
    private void handleCreateClick(MouseEvent event) {
        TextField[] tf = {nameField, addrField, phoneField, postalField};
            int rowsAffected = 0;
            // Validate user input and create new customer in DB
            if(!InputValidator.isEmpty(tf)){
                this.messageDisplayLabel.setText("");

                updatedCustomer.setName(nameField.getText());
                updatedCustomer.setAddress(addrField.getText());
                updatedCustomer.setPostalCode(postalField.getText());
                updatedCustomer.setPhone(phoneField.getText());
                updatedCustomer.setCreateDate(ZonedDateTime.now(ZoneId.systemDefault()));
                updatedCustomer.setCreatedBy(this.currentUser.getName());
                updatedCustomer.setLastUpdate(ZonedDateTime.now(ZoneId.systemDefault()));
                updatedCustomer.setLastUpdatedBy(this.currentUser.getName());
                // Get selected first level division
                String selectedDiv = divBox.getSelectionModel().getSelectedItem();
                this.divisions.forEach(d->{
                    if(d.getName().equals(selectedDiv)){
                        this.divID = d.getId();
                    }
                }); 
                updatedCustomer.setDivisionId(this.divID);

                rowsAffected = custImpl.update(updatedCustomer);            
            
                // Return to dashboard if successful
                if(rowsAffected > 0){
                    try{
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
                    }catch(IOException e){
                        System.out.println(e.getMessage());
                    }
                }else{
                    messageDisplayLabel.setText(rb.getString("dbError"));
                }   
            }
        }
    
}
