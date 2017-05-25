package miniclass.menu;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import miniClass.ui.MiniClass;
import miniclass.backend.Factory;
import miniclass.ui.ControlledScreen;
import miniclass.ui.ScreensController;

public class MenuController implements ControlledScreen{  
ScreensController myController;
Factory line;
Socket s;
ObjectOutputStream o;
ObjectInputStream i;
Task task;

@FXML
private Label welcome;

@FXML
private Button discussions;

@FXML
private Button material;

@FXML
private Button quiz;

@FXML
private Button contact;

@FXML
private Button live;

@FXML
private Button schedule;

@FXML
private FlowPane logout;

 @FXML
 public void initialize() throws IOException {
        System.out.println("Menu INIT");
       s=new Socket("127.0.0.1",6000);
        o=new ObjectOutputStream(s.getOutputStream());
        i=new ObjectInputStream(s.getInputStream());
        line=new Factory();
        task = new Task<Void>() {
        @Override public Void call() throws IOException {
        runner();
        return null;}
        };
        new Thread(task).start();
      
   }
    
    @FXML
public void loginEventHandler(ActionEvent e) throws IOException, ClassNotFoundException{
  try{    
       line.setType("menu"); 
     //  o.writeObject(line);
       System.out.println("Sent");
      } catch (Exception ex) {
      ex.printStackTrace();
    } 
}

  public void runner() throws IOException{
  /*try{       while(true){
           line=(Factory) i.readObject();
          if(!(line.getRetType().equalsIgnoreCase("menu")==true))continue;     
           break;
       }
       System.out.println("here");
       welcome.setText(new String((welcome.getText())+" "+line.getUser())); 
       } catch (IOException | ClassNotFoundException ex) {
        i.close();
        o.close();
       ex.printStackTrace();
      }
    */  }
    
    
@Override
 public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
 }   

@FXML
public void discussionsEventHandler(ActionEvent e){
     myController.setScreen(MiniClass.discuss);
}

@FXML
public void materialEventHandler(ActionEvent e){
     myController.setScreen(MiniClass.material);
}

@FXML
public void quizEventHandler(ActionEvent e){
     myController.setScreen(MiniClass.quiz);
}

@FXML
public void contactEventHandler(ActionEvent e){
    myController.setScreen(MiniClass.contact);
}

@FXML
public void liveEventHandler(ActionEvent e){
    myController.setScreen(MiniClass.live);
}

@FXML
public void scheduleEventHandler(ActionEvent e){
    myController.setScreen(MiniClass.schedule);
}
public void mousePressedHandler(MouseEvent me){
}

@FXML
public void logoutEventHandler(MouseEvent me){
    myController.setScreen(MiniClass.login);
}

}

