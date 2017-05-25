/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniclass.menu.sche;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
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
public class ScheController implements ControlledScreen {

    ScreensController myController;
    @FXML
    private TableView scheduler;
    
    @FXML
    private ListView updates;
    
    @FXML
    private CheckBox notifications;
    
    @FXML
    private ImageView back;
    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        updates.getItems().addAll(new String("Math Assignment: 16 Apr"),new String("Eng Assignment: 21 Jun"),new String("Seminar: 31 Aug"));// TODO
    }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
   @FXML
   public void notificationsEventHandler(ActionEvent e){
       
   }
   
   @FXML
    public void backEventHandler(MouseEvent me){
        myController.setScreen(MiniClass.main);
    }
}
