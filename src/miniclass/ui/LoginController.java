/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniclass.ui;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Date;
import javafx.animation.FadeTransition;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import miniClass.ui.MiniClass;
import miniclass.backend.Factory;

/**
 * FXML Controller class
 *
 * @author Vineet
 */

public class LoginController implements ControlledScreen{

ScreensController myController;
Factory line;
Socket s;
ObjectOutputStream o;
ObjectInputStream i;
String name;
Task task;

@FXML
private TextField username;

@FXML
private PasswordField password;

@FXML
private Button login;

@FXML
private CheckBox professor;

@FXML
private TextField error;
private Stage stage;
private boolean loginsuccessful;

@Override
  public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

   public void initialize() throws IOException {
        System.out.println("Login INIT");
        s=new Socket("127.0.0.1",6000);
        o=new ObjectOutputStream(s.getOutputStream());
        i=new ObjectInputStream(s.getInputStream());
        line =new Factory();
        task = new Task<Void>() {
        @Override public Void call() {
        runner();
        return null;}
        };
        new Thread(task).start();
   }
    
    
@FXML
public void loginEventHandler(ActionEvent e) throws IOException, ClassNotFoundException, SQLException{
  try{    
       line.setUser(username.getText());
       line.setPass(password.getText());
       line.professor=(boolean)(this.professor).isSelected();
       line.setType("login");
       o.writeObject(line);
        System.out.println("Sent");
      } catch (Exception ex) {
      ex.printStackTrace();
      } 
}
     
public void runner(){
    try{ while(true){
         line =(Factory)i.readObject();
         System.out.println("Received login");
         if(!(line.getRetType().equalsIgnoreCase("login")==true))continue;     
         if(line.authenticated==true){
             myController.setScreen(MiniClass.main);
             break;
         }
         else{error.setText("Invalid Credentials!");
             FadeTransition fadeIn = new FadeTransition(Duration.millis(1500),error);
             fadeIn.setFromValue(0.0);
             fadeIn.setToValue(1.0);
             fadeIn.setAutoReverse(true);
         if(!error.isVisible()){
             error.setVisible(true);
             fadeIn.playFromStart(); 
            }
         }
         }
         }catch (Exception ex) { ex.printStackTrace();}
          finally {
        System.out.println("Closing login stream");
            try{
            s.close();
            i.close();
            o.close();
            } catch(Exception e){e.printStackTrace();
            }
           }     
    }
 }
