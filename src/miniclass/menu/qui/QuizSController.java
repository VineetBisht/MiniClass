/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniclass.menu.qui;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import miniClass.ui.MiniClass;
import miniclass.backend.Factory;
import miniclass.ui.ControlledScreen;
import miniclass.ui.ScreensController;

/**
 * FXML Controller class
 *
 * @author Vineet
 */
public class QuizSController implements ControlledScreen, Runnable {
ScreensController myController;
    Thread listener;
    Factory line;
    private Socket s;
    private ObjectOutputStream o;
    private ObjectInputStream i;@FXML
    private Button solo;
    
    @FXML
    private ImageView back;
    
    @FXML
    private Button multi;
    
    @FXML
    private Button leader;
    
    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        System.out.println("QuizS INIT");
        
          try{
        s=new Socket("127.0.0.1",6000);
        o=new ObjectOutputStream(s.getOutputStream());
        i=new ObjectInputStream(s.getInputStream());
        }catch(IOException e){
            e.printStackTrace();
        }
        line=new Factory();
        listener=new Thread(this);
        listener.start();
    }
    
     public void run () { 				//cient listener 
       try {    line.setType("leader");
                o.writeObject(line);
                while(true){
                    line=(Factory)i.readObject();
                     if(!line.getRetType().equalsIgnoreCase("leader"))
                  continue;
                    if(line.professor){
                       solo.setVisible(false); 
                    }
                }
                }catch(Exception e){
                    e.printStackTrace();
                }
     }
                
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    @FXML
    public void soloEventHandler(ActionEvent e){
        myController.setScreen(MiniClass.quizC);
    }
    
    @FXML
    public void multiEventHandler(ActionEvent e){
        myController.setScreen(MiniClass.look);
    }
    
    @FXML
    public void leaderEventHandler(ActionEvent e){
        myController.setScreen(MiniClass.leader);
    }
    
    @FXML
    public void backEventHandler(MouseEvent me){
        myController.setScreen(MiniClass.main);
    }

}
