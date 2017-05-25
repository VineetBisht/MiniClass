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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class QuizEController implements ControlledScreen, Runnable {

    ScreensController myController;
    Thread listener;
    Factory line;
    private Socket s;
    private ObjectOutputStream o;
    private ObjectInputStream i;
    
    @FXML
    private Label own;
    
    @FXML
    private Label opscore;
    
    @FXML
    private Label result;
    
    @FXML
    private Button again;
    
    @FXML
    private ImageView back;
   
          
    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
      System.out.println("QuizE INIT");
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
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    @Override
    public void run () { 				//cient listener 
       try {    line.setType("quize");
                o.writeObject(line);
                while(true){
                    line=(Factory)i.readObject();
                     if(!line.getRetType().equalsIgnoreCase("quize"))
                         continue;
                    own.setText(""+line.score);
                }
       }catch(Exception e){
           e.printStackTrace();
       }
    }
    
    @FXML
    public void againEventHandler(ActionEvent e){
         myController.setScreen(MiniClass.quiz);
       
    }
    
    @FXML
    public void backEventHandler(MouseEvent me){
        myController.setScreen(MiniClass.quiz);
    }
    
}
