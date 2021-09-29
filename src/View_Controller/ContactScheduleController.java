/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import DAOImplementation.AppointmentImpl;
import Model.Appointment;
import Model.Contact;
import Utility.LoginHandler;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class for the contact schedule scene.
 *
 * @author KC Green
 */
public class ContactScheduleController implements Initializable {
    /**
     * The Label used to display the Contact's Name.
     */
    @FXML
    private Label contactNameLabel;
    /**
     * The TableView used to display the Contact's Appointments.
     */
    @FXML
    private TableView<Appointment> scheduleTable;
    /**
     * The Appointment ID TableColumn.
     */
    @FXML
    private TableColumn<?, ?> apptIdCol;
    /**
     * The Appointment Title TableColumn.
     */    
    @FXML
    private TableColumn<?, ?> titleCol;
    /**
     * The Appointment Type TableColumn.
     */    
    @FXML
    private TableColumn<?, ?> typeCol;
    /**
     * The Appointment Description TableColumn.
     */    
    @FXML
    private TableColumn<?, ?> descCol;
    /**
     * The Appointment Start TableColumn.
     */    
    @FXML
    private TableColumn<?, ?> startCol;
    /**
     * The Appointment End TableColumn.
     */    
    @FXML
    private TableColumn<?, ?> endCol;
    /**
     * The Appointment's Customer ID TableColumn.
     */    
    @FXML
    private TableColumn<?, ?> custIDCol;
    /**
     * The Return Button used to return to the Dashboard.
     */
    @FXML
    private Button returnBtn;
    /**
     * A JavaFX Stage object.
     */
    Stage stage;
    /**
     * A JavaFX Parent object.
     */
    Parent scene;
    /**
     * The Contact that was selected by the user.
     */
    Contact selectedContact;
    /**
     * An ObservableList containing all of the Contact's Appointments.
     */
    ObservableList<Appointment> apptList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class of the ContactSchedule scene.
     * @param url Uniform Resource Locator
     * @param rb Resource Bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }   
    
    /**
     * Send the selected Contact to the ContactScheduleController, use to generate the schedule TableView.
     * @param contact The contact that was selected by the user in the Dashboard scene.
     */
    public void sendContact(Contact contact){
        this.selectedContact = contact;
        contactNameLabel.setText(selectedContact.getName());
        this.generateScheduleTable(selectedContact.getId());
    }
    
    /**
     * Generate a schedule TableView with all of the selected Contact's Appointments.
     * @param contactId The ID of the Contact that was selected by the user.
     */
    public void generateScheduleTable(int contactId){
        AppointmentImpl apptImpl = new AppointmentImpl();
        ObservableList<Appointment> apptList = FXCollections.observableArrayList();
        apptList.setAll(apptImpl.getAllByContact(contactId));
        
        if(apptList.size() == 0){
            System.out.println(apptList.size());
        }else{            
            custIDCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            apptIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            startCol.setCellValueFactory(new PropertyValueFactory<>("formattedStartDateTime"));
            endCol.setCellValueFactory(new PropertyValueFactory<>("formattedEndDateTime"));

            scheduleTable.setItems(apptList);
            scheduleTable.refresh();        
        }

    }

    /**
     * Return the user to the Dashboard scene when the return button is clicked.
     * @param event The mouse click event
     */
    @FXML
    private void handleReturnClick(MouseEvent event) {
                try{
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
                    
                }catch(IOException e){
                    System.out.println(e.getMessage());
                }        
    }
    
}
