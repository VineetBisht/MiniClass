/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniclass.menu.discuss;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import miniClass.ui.MiniClass;
import miniclass.backend.Factory;
import miniclass.ui.ControlledScreen;
import miniclass.ui.ScreensController;

/**
 * FXML Controller class
 *
 * @author Vineet
 */
public class NewlyController implements ControlledScreen {
    
    Factory ob;
    Socket s;
    private ObjectOutputStream o;
    private ObjectInputStream i;
    ScreensController myController;
   
    @FXML
    private Button post;
    
    @FXML
    private TextArea text;
    
    @FXML
    private TextField ques;
    
    @FXML
    private CheckBox done;
    
    @FXML
    private ImageView back;
    /**
     * Initializes the controller class.
     */
    
    @FXML
    public void initialize() {
        System.out.println("NewlyController INIT");
        
     //subject.getItems().add("Computer");
     ob=new Factory();      
     System.out.println("Newly INIT()");
     try{
      s=new Socket("127.0.0.1",6000);
     o=new ObjectOutputStream(s.getOutputStream());
     i=new ObjectInputStream(s.getInputStream());
     }catch(Exception e){
         System.out.println("Initialization failed!"+ e);
        }
    }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    @FXML
    public void postEventHandler(ActionEvent e){
        ob.setQues(ques.getText());
        ob.setText(text.getText());
        ob.setSubject("computer");
        ob.setType("newly");
        try{
            System.out.println("Sending: "+ob.getText()+ob.getType());
          o.writeObject(ob);
        System.out.println("Sent");
      } catch (Exception ex) {
      ex.printStackTrace();
      }
      ques.setText("");
      text.setText("");
      FadeTransition fadeIn = new FadeTransition(Duration.millis(3000));
      fadeIn.setNode(done);
      fadeIn.setFromValue(0.0);
      fadeIn.setToValue(1.0);
      fadeIn.setCycleCount(1);
      fadeIn.setAutoReverse(true);
      if(!done.isVisible()){
      done.setVisible(true);
      fadeIn.playFromStart();     
      }
    }
    
    @FXML
    public void backEventHandler(MouseEvent me){
        myController.setScreen(MiniClass.discuss);
        try {
            s.close();
        } catch (IOException ex) {
        ex.printStackTrace();
        }
    }
    
}
