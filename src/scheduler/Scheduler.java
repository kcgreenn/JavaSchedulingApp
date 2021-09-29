/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler;

import DAOImplementation.AppointmentImpl;
import Utility.DB;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * An appointment scheduling application for a company with operations in multiple counties/time zones
 * @see <a href="https://docs.oracle.com/javase/8/javafx/api/javafx/fxml/doc-files/introduction_to_fxml.html">JavaFX</a>
 * @see <a href="https://docs.gluonhq.com/scenebuilder/">SceneBuilder</a>
 * @author KC Green
 */
public class Scheduler extends Application{

    /**
     * A ResourceBundle object allowing for localization between English and French.
     */
    ResourceBundle rb = ResourceBundle.getBundle("scheduler.l10n", Locale.getDefault());
    /**
     * Initialize the application. Connect to the database
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Open connection to DB when applications starts
        DB.startConnection();  
        
        AppointmentImpl apptImpl = new AppointmentImpl();
        apptImpl.getEachMonth();
        
        launch(args);
        
        // Close connection to DB when user exits application
        DB.closeConnection();
    }
    
    /**
     * Creates the login scene 
     * @param stage The JavaFX Stage object
     * @throws Exception IOException
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/LoginScreen.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(rb.getString("appTitle"));
        
        stage.show();
    }
    
}

