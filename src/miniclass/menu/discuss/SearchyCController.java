/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniclass.menu.discuss;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import miniclass.ui.ControlledScreen;
import miniclass.ui.ScreensController;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import miniClass.ui.MiniClass;
import miniclass.backend.Factory;

/**
 * FXML Controller class
 *
 * @author Vineet
 */
public class SearchyCController implements ControlledScreen,Runnable{

    ScreensController myController;
    Thread listener;
    Factory line;
    private Socket s;
    private ObjectOutputStream o;
    private ObjectInputStream i;
    
    @FXML
    private TextArea view;
            
    @FXML
    private Label topic;
    
    @FXML
    private TextField usertext;
    
    @FXML
    private Button post;
    
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
        line =(Factory)i.readObject();
         if(!line.getRetType().equalsIgnoreCase("serchyc"))
             continue;
        topic.setText((line.getCompleted())?"[SOLVED] "+line.searchdone:line.searchdone);
        view.setText(line.getText());
        System.out.println("Received");
      }
    } catch (Exception ex) {
     ex.printStackTrace();
    } finally {
      listener = null;
      
    }
   }

    @FXML
    public void postEventHandler(ActionEvent e){              
          try{line.setType("searchyc");
              line.setAdded(usertext.getText());
          o.writeObject(line);
        System.out.println("Sent");
      } catch (Exception ex) {
      ex.printStackTrace();
      }
        usertext.setText("");
      }
    
    @FXML
    public void backEventHandler(MouseEvent me) throws IOException{
        myController.setScreen(MiniClass.searchy);
        s.close();
        i.close();
        o.close();
    }

    @FXML
    public void initialize() {
        System.out.println("SearchyC INIT");
        try{
        s=new Socket("127.0.0.1",6000);
        o=new ObjectOutputStream(s.getOutputStream());
        i=new ObjectInputStream(s.getInputStream());
        }catch(IOException e){
            e.printStackTrace();
        }
        line=new Factory();
        usertext.requestFocus(); 
        listener=new Thread(this);
        listener.start();
    }
    }
