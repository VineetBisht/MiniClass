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
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
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
public class SearchyController implements ControlledScreen, Runnable {

    ScreensController myController;
    Factory ob;
    Socket s;
    private ObjectOutputStream o;
    private ObjectInputStream i;
    private List<String> res;
    Thread listener;
    
    @FXML
    private ChoiceBox subject;
    
    @FXML
    private TextField look;
    
    @FXML
    private ListView result;
    
    @FXML
    private ImageView back;
    
    /**
     * Initializes the controller class.
     */
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
          public void run () { 				//cient listener 
       try {
      while (true) {
          ob =(Factory)i.readObject();
           if(!ob.getRetType().equalsIgnoreCase("search"))
               continue;
                     System.out.println("Received");
          res=ob.getList();
           result.getItems().clear();  
           Platform.runLater(new Runnable() {
                 @Override public void run() {
                     result.getItems().addAll(res);
                 }
             });
      }
    } catch (Exception ex) {
     ex.printStackTrace();
    } finally {
      listener = null;     
    }
   }
    
    @FXML
    public void lookEventHandler(KeyEvent ke){
       ob.setSearch(look.getText());
        ob.setType("search");
        try{
          o.writeObject(ob);
        System.out.println("Sent");
       } catch (Exception ex) {
      ex.printStackTrace();
      }
    }
    
    @FXML
    public void resultEventHandler(MouseEvent me){
          ob.setSearch(look.getText());
        ob.setType("searchdone");
        try{
          o.writeObject(ob);
        System.out.println("Sent");
      } catch (Exception ex) {
      ex.printStackTrace();
      }
       myController.setScreen(MiniClass.searchyC);
    }
    
    @FXML
    public void subjectEventHandler(MouseEvent me){
        
    }
    
    @FXML
    public void backEventHandler(MouseEvent me){
        myController.setScreen(MiniClass.discuss);
       try{
           s.close();
       }catch(IOException e){e.printStackTrace();}
    }
    
   @FXML 
    public void initialize() {
        res=new ArrayList<String>();
        System.out.println("Searchy INIT");
       ob=new Factory();      
       try{
      s=new Socket("127.0.0.1",6000);
     o=new ObjectOutputStream(s.getOutputStream());
     i=new ObjectInputStream(s.getInputStream());
     }catch(Exception e){
         System.out.println("Initialization failed!"+ e);
        }
     listener=new Thread(this);
     listener.start();
    }
    
}
