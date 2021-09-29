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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class for the add customer scene
 *
 * @author KC Green
 */
public class AddCustomerController implements Initializable {

    /**
     * The field where the user can enter the customer name.
     */
    @FXML
    private TextField nameField;
    /**
     * The field where the user can enter the customer address.
     */    
    @FXML
    private TextField addrField;
    /**
     * The field where the user can enter the customer postal code.
     */    
    @FXML
    private TextField postalField;
    /**
     * The field where the user can enter the customer phone.
     */   
    @FXML
    private TextField phoneField;
    /**
     * The ComboBox where the user can select the customer country.
     */    
    @FXML
    private ComboBox<String> countryBox;
    /**
     * The ComboBox where the user can select the customer first_level_division.
     */        
    @FXML
    private ComboBox<String> divBox;
    /**
     * A label where any errors or custom messages can be displayed.
     */        
    @FXML
    private Label messageDisplayLabel;
    /**
     * The TextField displaying the customer id. This cannot be edited by the user.
     */        
    @FXML
    private TextField idField;
    /**
     * The button to cancel the customer creation and return to the dashboard.
     */    
    @FXML
    private Button cancelBtn;
    /**
     * The button to confirm creation of the customer and return to the dashboard.
     */
    @FXML
    private Button createBtn;

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
     * A localization ResourceBundle for both english and french.
     */
    ResourceBundle rb = ResourceBundle.getBundle("scheduler.l10n", Locale.getDefault());
    /**
     * The selected Division ID.
     */
    private int divID;    


    /**
     * Initializes the controller class.
     * @param url Uniform Resource Locator
     * @param r Resource Bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle r) {
        // Remove auto-focus from the id field
        idField.setFocusTraversable(false);
        //Generate Country combo box options
        countryBox.setPromptText("Country");
        generateCountryOptions();
        divBox.setPromptText(rb.getString("state"));
    }    
    
    /**
     * Get all countries and generate options for the Country ComboBox.
     */
    public void generateCountryOptions(){
        countries = cntryImpl.getAll();
        ObservableList<String> options = FXCollections.observableArrayList();        
        countries.forEach(c-> options.add(c.getName()));
        countryBox.setItems(options);
    }  

    /**
     * Get all divisions and generate options for the Division ComboBox.
     */
    public void generateDivOptions(){
        ObservableList<String> options = FXCollections.observableArrayList();
        this.divisions.forEach(d->{
            options.add(d.getName());
        });
        
        divBox.setItems(options);
    }    

    /**
     * Handles the mouse click event on the Cancel Button.
     * @param event The Mouse Click event.
     */
    @FXML
    private void handleCancelClick(MouseEvent event) {
        try{
            // Confirm Cancel Action
            Alert cancelAlert = new Alert(AlertType.CONFIRMATION);
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
     * Handles the mouse click event on the create button.
     * @param event The mouse click event.
     */
    @FXML
    private void handleCreateClick(MouseEvent event) {
        TextField[] tf = {nameField, addrField, phoneField, postalField};
        ComboBox[] cb = {this.countryBox, this.divBox};
        int rowsAffected = 0;
        // Validate user input and create new customer in DB
        if(!InputValidator.isEmpty(tf) && !InputValidator.nothingSelected(messageDisplayLabel, cb)){
            Customer newCust = new Customer();
            newCust.setName(nameField.getText());
            newCust.setAddress(addrField.getText());
            newCust.setPostalCode(postalField.getText());
            newCust.setPhone(phoneField.getText());
            newCust.setCreateDate(ZonedDateTime.now(ZoneId.systemDefault()));
            newCust.setCreatedBy(this.currentUser.getName());
            newCust.setLastUpdate(ZonedDateTime.now(ZoneId.systemDefault()));
            newCust.setLastUpdatedBy(this.currentUser.getName());
            newCust.setDivisionId(this.divID);

            rowsAffected = custImpl.create(newCust);            
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
     * Handles the selection of an item in the Country box. Generates Division Options based on the selected Country.
     * @param event The Action Event.
     */
    @FXML
    private void handleCountryBox(ActionEvent event) {
        DivisionImpl divImp = new DivisionImpl();
        String selectedCountry = countryBox.getSelectionModel().getSelectedItem();
        countries.forEach(c->{
            if(c.getName().equals(selectedCountry)){
                divisions = divImp.getAllByCountry(c.getId());
            }
        });
        this.generateDivOptions();           
    }

    /**
     * Handles the selection of an item in the Division ComboBox.
     * @param event The Action event.
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

    
}
