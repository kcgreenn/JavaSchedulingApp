/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author kcgre
 */
public class MonthAndTypeController implements Initializable {

    @FXML
    private ListView<?> January;
    @FXML
    private ListView<?> February;
    @FXML
    private ListView<?> April;
    @FXML
    private ListView<?> March;
    @FXML
    private ListView<?> May;
    @FXML
    private ListView<?> June;
    @FXML
    private ListView<?> July;
    @FXML
    private ListView<?> August;
    @FXML
    private ListView<?> October;
    @FXML
    private ListView<?> September;
    @FXML
    private ListView<?> November;
    @FXML
    private ListView<?> December;
    @FXML
    private Button returnBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleReturn(MouseEvent event) {
    }
    
}
