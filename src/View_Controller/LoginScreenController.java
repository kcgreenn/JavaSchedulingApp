package View_Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import Utility.InputValidator;
import Utility.Logger;
import Utility.LoginHandler;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Locale;
import javafx.scene.text.Text;

/**
 * FXML Controller class for the login scene
 *
 * @author KC Green
 */
public class LoginScreenController implements Initializable {
    /**
     * A TextField for the User Name
     */
    @FXML
    private TextField nameField;
    /**
     * A TextField for the User Password.
     */
    @FXML
    private TextField passwordField;
    /**
     * A Label user to display the user's Default Locale.
     */
    @FXML
    private Label localDisplayLabel;
    /**
     * The Login Button, passes the user input to the Login Handler.
     */
    @FXML
    private Button loginBtn;
    /**
     * A Label used to display any errors arising from the login process.
     */
    @FXML
    private Label errorDisplayLabel;
    /**
     * A Label for the username TextField.
     */
    @FXML
    private Label usernameLabel;
    /**
     * A Label for the password TextField.
     */
    @FXML
    private Label passwordLabel;
    /**
     * A Label for the Application title.
     */
    @FXML
    private Text titleLabel;    
    /**
     * The user after a successful login attempt.
     */
    User currentUser;
    /**
     * A JavaFX Stage object.
     */
    Stage stage;
    /**
     * A JavaFX Parent object.
     */
    Parent scene;
    /**
     * A localization ResourceBundle for both english and french.
     */
    ResourceBundle rb = ResourceBundle.getBundle("scheduler.l10n", Locale.getDefault());



    /**
     * Initializes the controller class.
     * @param url Uniform Resource Locator
     * @param rb Resource Bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.localizeScreen();
    }  
    
    /**
     * Localize the content of the login scene using the system's default settings
     */
    public void localizeScreen(){
        //Show user's locale
        titleLabel.setText(rb.getString("appTitle"));
        localDisplayLabel.setText(rb.getString("userLocation")+" "+java.time.ZoneId.systemDefault().toString());
        this.usernameLabel.setText(rb.getString("userNameLabel"));
        this.passwordLabel.setText(rb.getString("passwordLabel"));
        this.loginBtn.setText(rb.getString("loginBtn"));        
    }

    /**
     * Not implemented.
     * @param event The Key press event.
     */
    @FXML
    private void nameFieldHandler(KeyEvent event) {
    }

    /**
     * Not Implemented.
     * @param event The Key press event.
     */
    @FXML
    private void passwordFieldHandler(KeyEvent event) {
    }

    /**
     * Handles the clicking of the login button, validates the username and password
     * @param event The Mouse Click Event.
     */
    @FXML
    private void loginBtnHandler(MouseEvent event) {
        
        TextField[] tf = {nameField, passwordField};
        // Check that text fields are not empty
        if(!InputValidator.isEmpty(tf)){
            // Try to get the user from the database
            LoginHandler.getUserByName(nameField.getText(), passwordField.getText());
            
                try{
                    if(LoginHandler.isLoggedIn()){                    
                        // Write login attempts to log file
                        Logger.logAttempt(nameField.getText(), LocalDate.now(), LocalTime.now(), true);

                        // Open Dashboard with logged in user
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/View_Controller/Dashboard.fxml"));
                        loader.load();

                        DashboardController DController = loader.getController();
                        DController.sendUser(LoginHandler.getCurrentUser());

                        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                        Parent scene = loader.getRoot();
                        stage.setScene(new Scene(scene));
                        stage.show();
                    }else{
                        // Write login attempts to log file
                        Logger.logAttempt(nameField.getText(), LocalDate.now(), LocalTime.now(), false);
                        this.errorDisplayLabel.setText(rb.getString("loginError"));
                    }
                }catch(IOException e){
                    System.out.println(e.getMessage());
                }
            
        }
    }
}
