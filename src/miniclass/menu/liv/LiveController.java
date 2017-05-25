/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniclass.menu.liv;

import java.awt.Desktop;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.WindowEvent;
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
public class LiveController implements ControlledScreen, Runnable {

    ScreensController myController;
    Factory line;
    Socket s;
    ObjectOutputStream o;
    ObjectInputStream i;
    Thread listener;
    String download1="http://localhost:8080/WebApplication1/";
    String join1="http://localhost:5080/demos/simpleSubscriber.html";
    FileChooser file;
    private Connection cn;
    private Statement st;
    
    @FXML
    private ChoiceBox online;
    
    @FXML
    private Button download;
    
    @FXML
    private Button join;
    
    @FXML
    private ImageView back;
    boolean yes;
    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
         System.out.println("Live INIT");
           try{
        s=new Socket("127.0.0.1",6000);
        o=new ObjectOutputStream(s.getOutputStream());
        i=new ObjectInputStream(s.getInputStream());
        }catch(IOException e){
            e.printStackTrace();
        }
              online.getItems().addAll(new String("Professor x"),new String("Professor Y"));
        line=new Factory();
        yes=false;
        listener=new Thread(this);
         }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    @FXML
    public void downloadEventHandler(ActionEvent e) throws URISyntaxException, IOException{
       if(Desktop.isDesktopSupported())
{
  Desktop.getDesktop().browse(new URI(download1));
} 
    }
    
    @FXML
    public void joinEventHandler(ActionEvent e) throws URISyntaxException, IOException{
            if(Desktop.isDesktopSupported())
{
  Desktop.getDesktop().browse(new URI(join1));
} 
    }
    
    public final void setOnShown(EventHandler<WindowEvent> value){
             try{   Class.forName("com.mysql.cj.jdbc.Driver");
                cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/logindb","root","");
                st=cn.createStatement();
              listener.start();
              
       }catch(Exception e){
                 e.printStackTrace();
             }
    }
   
    @Override
  public void run(){
         while(true){ 
                 try{ 
                     
                Boolean d=false;
                ResultSet rs=st.executeQuery("select * from live where online=1");
                while(rs.next()){ 
                  online.getItems().add(rs.getString("userid"));  
                  yes=true;
                }
                if(yes){
                    join.setDisable(false);
                }
                line.setType("check");
                o.writeObject(line);
                line=(Factory) i.readObject();
                 if(!line.getRetType().equalsIgnoreCase("check"))
                     continue;
                  if(line.professor){
                    join.setText("Host");
                 join1="http://localhost:5080/demos/simpleBroadcaster.html";   
                 download.setVisible(false);
                 }
                
        }catch(Exception e){
            e.printStackTrace();
         }
 
    }
}
        @FXML
    public void backEventHandler(MouseEvent me){
        myController.setScreen(MiniClass.main);
    }
}
