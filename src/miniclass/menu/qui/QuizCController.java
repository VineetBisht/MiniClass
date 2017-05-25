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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;
import miniClass.ui.MiniClass;
import miniclass.backend.Factory;
import miniclass.backend.Refresh;
import miniclass.ui.ControlledScreen;
import miniclass.ui.ScreensController;

/**
 * FXML Controller class
 *
 * @author Vineet
 */
public class QuizCController implements ControlledScreen, Runnable {

    ScreensController myController;
    Thread listener;
    Factory line;
    private Socket s;
    private ObjectOutputStream o;
    private ObjectInputStream i;
    String answer;
    Connection cn;
    PreparedStatement pstmt1;
    String option11="StringBuilder";
    String option22="StringBuffer";
    String option33="String";
    String option44="All are equal";
    String ques1="What is faster?";
    
    int in;
    
    @FXML
    private Label question;
    
    @FXML
    private Button option1;
    
    @FXML
    private Button option2;
    
    @FXML
    private Button option3;
    
    @FXML
    private Button option4;
    
    
    @FXML
    private ImageView back;
    @FXML
    private CheckBox oppo;
    
    @FXML
    private ProgressBar timer;
    
    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        System.out.println("QuizC INIT");
        
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
        oppo.setVisible(false);   
    }
    
    public final void setOnShown(EventHandler<WindowEvent> value){
             try{   Class.forName("com.mysql.cj.jdbc.Driver");
                cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/logindb","root","");
              pstmt1 = cn.prepareStatement(" UPDATE scores \n" +
"   SET score = ?,wins= ? " +
" WHERE userid=?");
              listener.start();
              
       }catch(Exception e){
                 e.printStackTrace();
             }
    }
    @Override
    public void run () { 				//cient listener 
       /*try {    line.setType("quiz");
                o.writeObject(line);
                
		while(true){
               line=(Factory)i.readObject();
                  if(!line.getRetType().equalsIgnoreCase("quiz"))
                 continue;
               break; }
          Refresh quiz = new Refresh();
          for(int i=0;i<10;i++){
          int id=1 + (int)(Math.random() * ((30- 1) + 1));
          quiz.getValues(id);
         */ question.setText("What is the default static value of short?");
          option1.setText("0");
          option2.setText("NULL");
          option3.setText("0.0");
          option4.setText("None of the above");
          /*answer=quiz.answer;
          new Quizzing().test(line.getUser(),quiz,answer);
           }
          PreparedStatement pstmt2 = cn
				.prepareStatement("SELECT * FROM scores WHERE userid = ?");
          pstmt2.setString(1,line.getUser());
          ResultSet rs1 = pstmt2.executeQuery();
          int score=rs1.getInt("score");
          pstmt1.setInt(1,0);
          pstmt1.setInt(2,rs1.getInt("wins")+score);
          pstmt1.setString(3, line.getUser());
          ResultSet rs = pstmt1.executeQuery();
          line.score=score;
          line.setType("score");
          o.writeObject(line);
               myController.setScreen(MiniClass.quizE);
          
    } catch (Exception ex) {
     ex.printStackTrace();
    } finally {
      listener = null;
      */
    
   }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    @FXML
    public void option1EventHandler(ActionEvent e){
      /*  try{answer=option2.getText();
          o.writeObject(line);
        }catch(Exception e1){
            e1.printStackTrace();
        }*/
        question.setText(ques1);
          option1.setText(option11);
          option2.setText(option22);
          option3.setText(option33);
          option4.setText(option44);
    }
    
    @FXML
    public void option2EventHandler(ActionEvent e){
        /*  try{answer=option2.getText();
          o.writeObject(line);
        }catch(Exception e1){
            e1.printStackTrace();
        }*/
       question.setText(ques1);
          option1.setText(option11);
          option2.setText(option22);
          option3.setText(option33);
          option4.setText(option44);
    }
    
    @FXML
    public void option3EventHandler(ActionEvent e){
        /*  try{answer=option2.getText();
          o.writeObject(line);
        }catch(Exception e1){
            e1.printStackTrace();
        }*/
       question.setText(ques1);
          option1.setText(option11);
          option2.setText(option22);
          option3.setText(option33);
          option4.setText(option44);
    }
    
    @FXML
    public void option4EventHandler(ActionEvent e){
         /*  try{answer=option2.getText();
          o.writeObject(line);
        }catch(Exception e1){
            e1.printStackTrace();
        }*/
        question.setText(ques1);
          option1.setText(option11);
          option2.setText(option22);
          option3.setText(option33);
          option4.setText(option44);
    }
    
    @FXML
    public void oppoEventHandler(ActionEvent e){
        
    }
    
    @FXML
    public void backEventHandler(MouseEvent me){
        myController.setScreen(MiniClass.quiz);
    }
    
}
