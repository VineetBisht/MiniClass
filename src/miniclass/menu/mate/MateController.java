/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniclass.menu.mate;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
public class MateController implements ControlledScreen {

    ScreensController myController;
    @FXML
    private ChoiceBox subject;
    
    @FXML
    private Button assign;
    
    @FXML
    private Button notes;
    
    @FXML
    private ImageView back;
    
    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        subject.getItems().addAll("AJAX","Introduction CS");
        // TODO
    }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
        
    }

    @FXML
    public void assignEventHandler(ActionEvent e){
        
    }
    
    @FXML
    public void notesEventHandler(ActionEvent e) throws URISyntaxException, IOException{
          if(Desktop.isDesktopSupported())
     Desktop.getDesktop().browse(new URI("http://localhost:8080/WebApplication1/notes.jsp"));
  }
    
    @FXML
    public void backEventHandler(MouseEvent me){
        myController.setScreen(MiniClass.main);
    }
    
}
