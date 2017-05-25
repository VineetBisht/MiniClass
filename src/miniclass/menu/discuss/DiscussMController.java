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
public class DiscussMController implements ControlledScreen, Runnable {

    Factory line;
    Socket s;
    private ObjectOutputStream o;
    private ObjectInputStream i;
    ScreensController myController;
    Thread listener;
    int in;
   
    @FXML
    private Button newly;
    
    @FXML
    private Button search;
    
    @FXML
    private ImageView back;
    
    @FXML 
    private Button ongoing;
    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        System.out.println("DiscussM INIT");
        try{
        s=new Socket("127.0.0.1",6000);
        o=new ObjectOutputStream(s.getOutputStream());
        i=new ObjectInputStream(s.getInputStream());
        }catch(IOException e){
            e.printStackTrace();
        }
        line=new Factory();
        listener=new Thread(this);
        in=0;
        listener.start();
  }
     
    @Override
    public void run () { 				//cient listener 
       try {    line.setType("check");
                o.writeObject(line);
                while(true){
                    line=(Factory)i.readObject();
                    if(line.getRetType().equalsIgnoreCase("check"))
                    break;
                }
                  if(line.professor) 
                      newly.setVisible(false);
                }catch(Exception e){
                    e.printStackTrace();
                }
       }
             
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    @FXML
    public void newlyEventHandler(ActionEvent e){
        myController.setScreen(MiniClass.newly);
    }
    
    @FXML
    public void ongoingEventHandler(ActionEvent e){
        myController.setScreen(MiniClass.ongo);
      
    }
    
    @FXML
    public void searchEventHandler(ActionEvent e){
      myController.setScreen(MiniClass.searchy);  
    }
    
    @FXML
     public void backEventHandler(MouseEvent me){
        myController.setScreen(MiniClass.main);
    }
   
    
} 
