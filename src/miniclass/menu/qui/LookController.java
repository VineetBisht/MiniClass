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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class LookController implements ControlledScreen, Runnable {
    ScreensController myController;
    Socket s;
    ObjectOutputStream o;
    ObjectInputStream i;
    Factory line;
    Thread listener;
    Connection cn;
    
    /**
     * Initializes the controller class.
     */
    @FXML
    private Button done;
    
    @FXML
    private ImageView loading;
    
    @FXML
    public void initialize() {
         System.out.println("Look INIT");
           try{
        s=new Socket("127.0.0.1",6000);
        o=new ObjectOutputStream(s.getOutputStream());
        i=new ObjectInputStream(s.getInputStream());
        line=new Factory();
        listener=new Thread(this);
         listener.start();
        o.writeObject(line);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void run(){
     try{
         Class.forName("com.mysql.cj.jdbc.Driver");   
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/logindb","root","");
         Statement st=cn.createStatement();
                Boolean d=false;
                ResultSet rs=st.executeQuery("select * from live where online=1 AND professor=0");
                rs.last();
                if(rs.getRow()>1) 
                 myController.setScreen(MiniClass.quizC);
               
           }catch(Exception e){
               e.printStackTrace();
           }
    }
 
    
     public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    @FXML
    public void backEventHandler(MouseEvent me){
        myController.setScreen(MiniClass.quiz);
    }
    
    
}
