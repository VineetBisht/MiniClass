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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;
import miniClass.ui.MiniClass;
import miniclass.backend.Factory;
import miniclass.ui.ControlledScreen;
import miniclass.ui.ScreensController;

/**
 * FXML Controller class
 *
 * @author Vineet
 */
public class LeaderController implements ControlledScreen, Runnable {
    ScreensController myController;
    Thread listener;
    Factory line;
    private Socket s;
    private ObjectOutputStream o;
    private ObjectInputStream i;
    private Connection cn;
    private PreparedStatement pstmt1;
    
   
    @FXML
    private ListView leaders;
    
    @FXML
    private PieChart pie;
    
    @FXML
    private ImageView back;
    
    /**
     * Initializes the controller class.
     */
    public final void setOnShown(EventHandler<WindowEvent> value){
       try{
        s=new Socket("127.0.0.1",6000);
        o=new ObjectOutputStream(s.getOutputStream());
        i=new ObjectInputStream(s.getInputStream());
        listener.start();
         }catch(IOException e){
            e.printStackTrace();
        }
      
  }
    
    @FXML
    public void initialize() {
        System.out.println("Leader INIT");
        line=new Factory();
        listener=new Thread(this);
        try {  Class.forName("com.mysql.jdbc.Driver");
                cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/logindb","root","");
                pstmt1 = cn
				.prepareStatement("SELECT * FROM scores ORDER BY wins DESC");
        }catch(Exception e){
       e.printStackTrace();
        }
    }
    
     @Override
    public void run () { 				//cient listener 
       try {    line.setType("leader");
                o.writeObject(line);
                while(true){
                    line=(Factory)i.readObject();
                    if(!line.getRetType().equalsIgnoreCase("leader"))
                   continue;
                   break;}
               ResultSet rs = pstmt1.executeQuery();
		while(true){
                    if(rs.next()==false)
                    break;
                    leaders.getItems().clear();  
                 Platform.runLater(new Runnable() {
                    @Override public void run() {
                     try{
                         String temp1=rs.getString("userid");
                         int temp2=rs.getInt("wins");
                     leaders.getItems().addAll(temp1+temp2);
                 }catch(Exception e){
                     e.printStackTrace();
                 }
                }
             });
                }
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
