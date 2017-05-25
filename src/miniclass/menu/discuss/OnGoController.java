/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniclass.menu.discuss;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import miniClass.ui.MiniClass;
import miniclass.ui.ControlledScreen;
import miniclass.ui.ScreensController;

/**
 * FXML Controller class
 *
 * @author Vineet
 */
public class OnGoController implements Initializable, ControlledScreen {

    ScreensController myController;
    @FXML
    private ChoiceBox question;
    
    @FXML
    private ListView queries;
    
    @FXML
    private TextField usertext;
    
    @FXML
    private Label username;
    
    @FXML
    private Button post;
    
    @FXML
    private ImageView back;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    @FXML
    public void postEventHandler(ActionEvent e){
        
    }

    @FXML
    public void questionEventHandler(MouseEvent me){
        
    }
    
    @FXML
    public void backEventHandler(MouseEvent me){
        myController.setScreen(MiniClass.discuss);
    }
    
}
