/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import DAOImplementation.AppointmentImpl;
import DAOImplementation.ContactImpl;
import DAOImplementation.CustomerImpl;
import Model.Appointment;
import Model.Contact;
import Model.Customer;
import Model.User;
import Utility.GroupCount;
import Utility.LoginHandler;
import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class for the dashboard scene. The main scene when the user is logged in.
 * @author KC Green
 */
public class DashboardController implements Initializable {

    @FXML
    private Label userNameLabel;
    @FXML
    private AnchorPane dashboardPane;
    @FXML
    private TitledPane apptPane;
    @FXML
    private TableView<Appointment> weeklyApptTable;
    @FXML
    private TableColumn<?, ?> weeklyIdCol;
    @FXML
    private TableColumn<?, ?> weeklyTitleCol;
    @FXML
    private TableColumn<?, ?> weeklyDescCol;
    @FXML
    private TableColumn<?, ?> weeklyLocCol;
    @FXML
    private TableColumn<?, ?> weeklyContactCol;
    @FXML
    private TableColumn<?, ?> weeklyTypeCol;
    @FXML
    private TableColumn<?, ?> weeklyStartCol;
    @FXML
    private TableColumn<?, ?> weeklyEndCol;
    @FXML
    private TableColumn<?, ?> weeklyCustIdCol;
    @FXML
    private TableView<Appointment> monthlyApptTable;
    @FXML
    private TableColumn<?, ?> monthlyIdCol;
    @FXML
    private TableColumn<?, ?> monthlyTitleCol;
    @FXML
    private TableColumn<?, ?> monthlyDescCol;
    @FXML
    private TableColumn<?, ?> monthlyLocCol;
    @FXML
    private TableColumn<?, ?> monthlyContactCol;
    @FXML
    private TableColumn<?, ?> monthlyTypeCol;
    @FXML
    private TableColumn<?, ?> monthlyStartCol;
    @FXML
    private TableColumn<?, ?> monthlyEndCol;
    @FXML
    private TableColumn<?, ?> monthlyCustIdCol;
    @FXML
    private Button apptCreateBtn;
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<?, ?> custIdCol;
    @FXML
    private TableColumn<?, ?> custNameCol;
    @FXML
    private TableColumn<?, ?> custAddrCol;
    @FXML
    private TableColumn<?, ?> custPostalCol;
    @FXML
    private TableColumn<?, ?> custPhoneCol;
    @FXML
    private TableColumn<?, ?> custCreateDateCol;
    @FXML
    private TableColumn<?, ?> custCreatedByCol;
    @FXML
    private TableColumn<?, ?> custLastUpdateCol;
    @FXML
    private TableColumn<?, ?> custLastUpdateByCol;
    @FXML
    private TableColumn<?, ?> custDivCol;
    @FXML
    private Button deleteCustBtn;
    @FXML
    private Button updateCustBtn;
    @FXML
    private Button createCustBtn;
    @FXML
    private Label apptWarningLabel;
    @FXML
    private Accordion accordianPane;
    @FXML
    private Button weekApptDeleteBtn;
    @FXML
    private Button weekApptUpdateBtn;
    @FXML
    private Button apptCreateBtn1;
    @FXML
    private Button monthApptDeleteBtn;
    @FXML
    private Button monthApptUpdateBtn;
    @FXML
    private Label custTableLabel;
    @FXML
    private Label weeklyTableLabel;
    @FXML
    private Label monthlyTableLabel;
    private TableView<GroupCount> totalByTypeTable;
    private TableColumn<?, ?> typeCountTypeCol;
    private TableColumn<?, ?> typeCountCountCol;
    private TableColumn<?, ?> monthCountMonthCol;
    private TableColumn<?, ?> monthCountCountCol;
    private TableView<GroupCount> totalByMonthTable;
    @FXML
    private TableView<Contact> contactTable;
    @FXML
    private TableColumn<?, ?> contactIDCol;
    @FXML
    private TableColumn<?, ?> contactNameCol;
    @FXML
    private Button getContactSchedBtn;
    @FXML
    private TableColumn<?, ?> contactEmailCol;
    private TableColumn<?, ?> weeklyStartDateCol;
    private TableColumn<?, ?> weeklyStartTimeCol;
    private TableColumn<?, ?> weeklyEndDateCol;
    private TableColumn<?, ?> weeklyEndTimeCol;
    private TableColumn<?, ?> monthlyStartDateCol;
    private TableColumn<?, ?> monthlyStartTimeCol;
    private TableColumn<?, ?> monthlyEndDateCol;
    private TableColumn<?, ?> monthlyEndTimeCol;
    @FXML
    private AnchorPane reportsPane;    
    
    Stage stage;
    Parent scene;
    
    ResourceBundle rb = ResourceBundle.getBundle("scheduler.l10n", Locale.getDefault());
    
    AppointmentImpl apptImpl = new AppointmentImpl();
    CustomerImpl custImpl = new CustomerImpl();
    
    private Map<Integer, String> months = new HashMap<>();
    
    User currentUser;
    Appointment upcomingAppt;
    ObservableList<Appointment> weeklyApptList = FXCollections.observableArrayList();
    ObservableList<Appointment> monthlyApptList = FXCollections.observableArrayList();
    ObservableList<Customer> customerList = FXCollections.observableArrayList();
    @FXML
    private ListView<String> monthList;
    @FXML
    private ComboBox<String> monthCombo;


    /**
     * Initializes the controller class. Sets the current user, generates the TableView data and report charts.
     * @param url Uniform Resource Locator
     * @param rb Resource Bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.currentUser = LoginHandler.getCurrentUser();
        // Fill customer table view with relevent data
        this.generateCustomerTable();
        
        // Have appointments pane expanded by default
        this.accordianPane.setExpandedPane(apptPane);
        
        this.generateReportChart();
        this.generateMonthOptions();
    }   
    
    /**
     * Receives the currently logged in user. Used to display personalized data on the dashboard.
     * @param loggedInUser The currently logged in user
     */
    public void sendUser(User loggedInUser){
        this.currentUser = loggedInUser;
        
        // Display custom welcome message
        this.userNameLabel.setText(this.currentUser.getName());
        
        // Tell user about upcoming appointment
        alertUpcomingAppt();
        
        // Fill Appointment table views with user's appointments
        this.generateWeekApptTable();
        this.generateMonthApptTable();      
        
        // Generate Reports
//        this.generateTypeCountTable();
//        this.generateMonthCountTable();
        this.generateContactTable();
    }
    
    /**
     * Fills the Weekly Appointment Table View with any appointments that are scheduled for the current week.
     */
    private void generateWeekApptTable() {
        weeklyApptList.removeAll();
        weeklyApptList.setAll(this.apptImpl.getAllByWeek());

        weeklyIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        weeklyTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        weeklyDescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        weeklyLocCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        weeklyContactCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        weeklyTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        weeklyStartCol.setCellValueFactory(new PropertyValueFactory<>("formattedStartDateTime"));
        weeklyEndCol.setCellValueFactory(new PropertyValueFactory<>("formattedEndDateTime"));
        weeklyCustIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        weeklyApptTable.setItems(weeklyApptList);
        weeklyApptTable.refresh();
    }
    
    /**
     * Fills the Monthly Appointment Table View with any appointments that are scheduled for the current month.
     */
    private void generateMonthApptTable(){
        monthlyApptList.clear();
        monthlyApptList.setAll(this.apptImpl.getAllByMonth());
        
        monthlyIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        monthlyTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        monthlyDescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        monthlyLocCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        monthlyContactCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        monthlyTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        monthlyStartCol.setCellValueFactory(new PropertyValueFactory<>("formattedStartDateTime"));
        monthlyEndCol.setCellValueFactory(new PropertyValueFactory<>("formattedEndDateTime"));
        monthlyCustIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        monthlyApptTable.setItems(monthlyApptList);
        monthlyApptTable.refresh();
    }  
    
    /**
     * Fills the Customer Table View with all customer records in the database.
     */
    private void generateCustomerTable(){
        customerList.setAll(this.custImpl.getAll());
        
        custIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        custNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        custAddrCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        custPostalCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        custPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        custCreateDateCol.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        custCreatedByCol.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        custLastUpdateCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        custLastUpdateByCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
        custDivCol.setCellValueFactory(new PropertyValueFactory<>("divisionId"));
        
        customerTable.setItems(customerList);
        customerTable.refresh();
    } 
        
    /**
     * Fills the contact table with all of the contact records in the database.
     */
    private void generateContactTable(){
        ContactImpl contImpl = new ContactImpl();
        ObservableList<Contact> contactList = FXCollections.observableArrayList();
        contactList.setAll(contImpl.getAll());
        
        contactIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        contactNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        contactEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        contactTable.setItems(contactList);
        contactTable.refresh();
    }
    
    /**
     * Alert the user if they have an appointment scheduled in the next fifteen minutes.
     */
    private void alertUpcomingAppt(){
        // Get any appointments that are scheduled for the next fifteen minutes
        apptWarningLabel.setText(rb.getString("noUpcoming"));
        
        ObservableList<Appointment> upcomingApptList = FXCollections.observableArrayList();
        upcomingApptList.setAll(this.apptImpl.getAllByWeek());
        
        ZonedDateTime currentTime = ZonedDateTime.now();
        upcomingApptList.forEach(a->{
            long timeDifference = ChronoUnit.MINUTES.between(currentTime, a.getStart());
            if(timeDifference > 0 && timeDifference <= 15 && a.getUserId() == this.currentUser.getId()){
                apptWarningLabel.setText(rb.getString("upcoming")+" ID: "+a.getAppointmentId()+rb.getString("startingAt")+" "+a.getFormattedStartDateTime());
            }
        });        
    }
    
    public void generateMonthOptions(){
        ObservableList<String> options = FXCollections.observableArrayList();  
        months.put(1, "January");
        months.put(2, "February");
        months.put(3, "March");
        months.put(4, "April");
        months.put(5, "May");
        months.put(6, "June");
        months.put(7, "July");
        months.put(8, "August");
        months.put(9, "September");
        months.put(10, "October");
        months.put(11, "November");
        months.put(12, "December");        
        
        months.forEach((k, v)->{
            options.add(v);
        });
        monthCombo.setItems(options);
    }

    /**
     * Deletes the appointment that is selected from either the weekly appointment table or the monthly appointment table.
     * @param event The mouse click event
     */
    private void handleApptDeleteClick(MouseEvent event) {
        // Check if any appointment is selected from the table
        if(this.weeklyApptTable.getSelectionModel().isEmpty() && this.monthlyApptTable.getSelectionModel().isEmpty()){
            Alert noSelectionAlert = new Alert(Alert.AlertType.INFORMATION);
            noSelectionAlert.headerTextProperty().set(rb.getString("noApptSelected"));
            Optional<ButtonType> result = noSelectionAlert.showAndWait();
        }else{
            Appointment selectedAppt = this.weeklyApptTable.getSelectionModel().getSelectedItem();
            // Confirm Cancel Action
            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAlert.headerTextProperty().set(rb.getString("apptDeleteConfirm"));    
            Optional<ButtonType> result = deleteAlert.showAndWait();
            
            if(result.isPresent() && result.get() == ButtonType.OK){
                // Delete appointment
                this.apptImpl.delete(selectedAppt);
                
                // Refresh customer table with updated list
                this.weeklyApptList.clear();
                this.weeklyApptList.setAll(apptImpl.getAllByWeek());
                this.weeklyApptTable.setItems(weeklyApptList);
                this.weeklyApptTable.refresh();
            }
        }           
    }
    
    /**
     * Handles the mouse click event on the delete button. Deletes the selected Appointment.
     * @param event The Mouse click event.
     */
    @FXML
    private void handleWeekApptDeleteClick(MouseEvent event) {
        // Check if any appt is selected from the table
        if(this.weeklyApptTable.getSelectionModel().isEmpty()){
            Alert noSelectionAlert = new Alert(Alert.AlertType.INFORMATION);
            noSelectionAlert.headerTextProperty().set(rb.getString("noApptSelected"));
            Optional<ButtonType> result = noSelectionAlert.showAndWait();
        }else{
            Appointment selectedAppt = this.weeklyApptTable.getSelectionModel().getSelectedItem();
            // Confirm Cancel Action
            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAlert.headerTextProperty().set(rb.getString("apptDeleteConfirm"));    
            Optional<ButtonType> result = deleteAlert.showAndWait();
            
            if(result.isPresent() && result.get() == ButtonType.OK){
                // Delete customer's appointments and customer itself
                this.apptImpl.delete(selectedAppt);
                
                // Refresh appointment table with updated list
                this.weeklyApptList.clear();
                this.weeklyApptList.setAll(apptImpl.getAllByWeek());
                this.weeklyApptTable.setItems(weeklyApptList);
                this.weeklyApptTable.refresh();
                String deleteMessage = "ID: "+selectedAppt.getAppointmentId()+" - Type: "+selectedAppt.getType()+" "+rb.getString("apptDeleted");
                this.weeklyTableLabel.setText(deleteMessage);
            }
        }              
    }

    /**
     * Handles the mouse click event on the update button. Updates the selected appointment.
     * @param event The Mouse Click Event.
     */
    @FXML
    private void handleWeekApptUpdateClick(MouseEvent event) {
        try{
            // Check if user selected an appointment
            if(this.weeklyApptTable.getSelectionModel().isEmpty()){
                Alert selectPartWarning = new Alert(Alert.AlertType.WARNING);
                selectPartWarning.headerTextProperty().set(rb.getString("noApptUpdate"));
                selectPartWarning.showAndWait();
                return;
            }
            Appointment selectedAppointment = this.weeklyApptTable.getSelectionModel().getSelectedItem();
            // Open update appointment screen with selected appointment
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View_Controller/UpdateAppointment.fxml"));
            loader.load();

            UpdateAppointmentController UAController = loader.getController();
            UAController.sendAppointmentAndUser(selectedAppointment, this.currentUser);

            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }catch(IOException e){
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }              
    }

    /**
     * Handles the mouse click event on the delete button. Deletes the selected Appointment.
     * @param event The Mouse click event.
     */    
    @FXML
    private void handleMonthApptDeleteClick(MouseEvent event) {
        // Check if any appointment is selected from the table
        if(this.monthlyApptTable.getSelectionModel().isEmpty()){
            Alert noSelectionAlert = new Alert(Alert.AlertType.INFORMATION);
            noSelectionAlert.headerTextProperty().set(rb.getString("noApptSelected"));
            Optional<ButtonType> result = noSelectionAlert.showAndWait();
        }else{
            Appointment selectedAppt = this.monthlyApptTable.getSelectionModel().getSelectedItem();
            // Confirm Cancel Action
            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAlert.headerTextProperty().set(rb.getString("apptDeleteConfirm"));    
            Optional<ButtonType> result = deleteAlert.showAndWait();
            
            if(result.isPresent() && result.get() == ButtonType.OK){
                // Delete appointments 
                this.apptImpl.delete(selectedAppt);
                
                // Refresh customer table with updated list
                this.monthlyApptList.clear();
                this.monthlyApptList.setAll(apptImpl.getAllByMonth());
                this.monthlyApptTable.setItems(monthlyApptList);
                this.monthlyApptTable.refresh();
                String deleteMessage = "Appointment ID: "+selectedAppt.getAppointmentId()+" - Type: "+selectedAppt.getType()+" "+rb.getString("apptDeleted");
                this.monthlyTableLabel.setText(deleteMessage);
            }
        }         
    }

     /**
     * Handles the mouse click event on the update button. Updates the selected appointment.
     * @param event The Mouse Click Event.
     */
    @FXML
    private void handleMonthApptUpdateClick(MouseEvent event) {
        try{
            // Open Update AppointmentScreen with the selected appointment
            Appointment selectedAppointment = this.monthlyApptTable.getSelectionModel().getSelectedItem();
            // Tell User If No Part Is Selected
            if(selectedAppointment == null){
                Alert selectPartWarning = new Alert(Alert.AlertType.WARNING);
                selectPartWarning.headerTextProperty().set(rb.getString("noApptUpdate"));
                selectPartWarning.showAndWait();
                return;
            }

            // Open update appointment Screen for Selected appointment
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View_Controller/UpdateAppointment.fxml"));
            loader.load();

            UpdateAppointmentController UAController = loader.getController();
            UAController.sendAppointmentAndUser(selectedAppointment, this.currentUser);

            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }        
    }    

    /**
     * Handles the mouse click event on the appointment create button. Opens the scene to create a new appointment.
     * @param event The mouse click event.
     */
    @FXML
    private void handleApptCreateClick(MouseEvent event) {
        try{      
            // Open add appointment Screen for Selected appointment
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View_Controller/AddAppointment.fxml"));
            loader.load();

            AddAppointmentController AAController = loader.getController();
            AAController.sendUser(this.currentUser);

            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }         
    }

    /**
     * Handles the mouse click event on the customer delete button. Deletes the selected customer.
     * @param event The mouse click event.
     */
    @FXML
    private void handleDeleteCustClick(MouseEvent event) {
        // Check if any customer is selected from the table
        if(this.customerTable.getSelectionModel().isEmpty()){
            Alert noSelectionAlert = new Alert(Alert.AlertType.INFORMATION);
            noSelectionAlert.headerTextProperty().set(rb.getString("noCustSelected"));
            Optional<ButtonType> result = noSelectionAlert.showAndWait();
        }else{
            Customer selectedCustomer = this.customerTable.getSelectionModel().getSelectedItem();            
            // Confirm Cancel Action
            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAlert.headerTextProperty().set(rb.getString("custDeleteConfirm"));    
            Optional<ButtonType> result = deleteAlert.showAndWait();
            
            if(result.isPresent() && result.get() == ButtonType.OK){
                // Delete customer's appointments and customer itself
                this.apptImpl.deleteByCustomerID(selectedCustomer.getId());
                this.custImpl.delete(selectedCustomer);
                // Refresh customer table with updated list
                this.customerList.clear();
                this.customerList.setAll(custImpl.getAll());
                this.customerTable.setItems(customerList);
                this.customerTable.refresh();
                
                this.custTableLabel.setText(rb.getString("custDeleted"));
            }
        }        
    }

    /**
     * Handles the mouse click event on the customer update button. Opens the customer update scene.
     * @param event The mouse click event.
     */
    @FXML
    private void handleUpdateCustClick(MouseEvent event) {
        try{
            // Open Update Customer Screen with the selected customer
            Customer selectedCustomer = this.customerTable.getSelectionModel().getSelectedItem();
            // Tell User If No Part Is Selected
            if(selectedCustomer == null){
                Alert selectPartWarning = new Alert(Alert.AlertType.WARNING);
                selectPartWarning.headerTextProperty().set(rb.getString("noCustUpdate"));
                selectPartWarning.showAndWait();
                return;
            }

            // Open Modify Part Screen for Selected Part
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View_Controller/UpdateCustomer.fxml"));
            loader.load();

            UpdateCustomerController UCController = loader.getController();
            UCController.sendCustomer(selectedCustomer);

            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }catch(IOException e){
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
    }

    /**
     * Handles the mouse click event on the create customer button. Opens the create customer scene.
     * @param event The mouse click event.
     */
    @FXML
    private void handleCreateCustClick(MouseEvent event) {
        try{    
            System.out.println("in add handler");
            // Open Add customer screen
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View_Controller/AddCustomer.fxml"));

            stage.setScene(new Scene(scene));
            stage.show();
        }catch(IOException ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getCause());
        }        
    }

    /**
     * Handles the mouse click event on the contact schedule button. Opens the contact schedule scene.
     * @param event The mouse click event.
     */
    @FXML
    private void handleGetContactSched(MouseEvent event) {
        // Inform user if no contact is 
        if(contactTable.getSelectionModel().isEmpty()){
            Alert selectPartWarning = new Alert(Alert.AlertType.WARNING);
            selectPartWarning.headerTextProperty().set(rb.getString("noContSelected"));
            selectPartWarning.showAndWait();
            return;            
        }
        try{
            Contact selectedContact = contactTable.getSelectionModel().getSelectedItem();
            // Open contact schedule scene
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View_Controller/ContactSchedule.fxml"));
            loader.load();

            ContactScheduleController CSController = loader.getController();
            CSController.sendContact(selectedContact);            
            
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();                    
        }catch(IOException e){
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
    }
    
    /**
     * Generates the custom report for A.3.f of the Task. Charts the number of appointments scheduled for each hour of the business day.
     */
    @SuppressWarnings("unchecked")
    public void generateReportChart(){
        ObservableList<GroupCount> totalByTimeList = apptImpl.totalByTime();
        
        // Create axes
        NumberAxis xAxis = new NumberAxis(rb.getString("timeOfDay"), 7, 23, 1);
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel(rb.getString("numberOfAppt"));
        // Create Line Chart
        LineChart lineChart;       
        lineChart = new LineChart(xAxis, yAxis);

        //Create a new data object for the line chart
        XYChart.Series data = new XYChart.Series<Number, Number>();
        // Add all time, count pairs to data object
        totalByTimeList.forEach(t->{
            data.getData().add(new XYChart.Data<>(Integer.parseInt(t.getValue()), t.getCount()));
        });
        
        lineChart.getData().add(data);
        
        // Add Line Chart to the Reports Pane
        reportsPane.getChildren().add(lineChart);
        lineChart.setLayoutX(430);
        lineChart.setLayoutY(30);   
        lineChart.setMaxHeight(250);
        lineChart.legendVisibleProperty().set(false);
        lineChart.setMaxWidth(250);
        
    }    

    @FXML
    private void handleMonthSelect(ActionEvent event) {
        String selectedMonth = monthCombo.getSelectionModel().getSelectedItem();
      
        this.months.forEach((k,v)->{
            if(selectedMonth == v){
                ObservableList<String> typesPerMonth = apptImpl.getTypeCountPerMonth(k);
                monthList.setItems(typesPerMonth);
            }
        });
        
    }
}
