/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniClass.ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import miniclass.ui.ScreensController;
/**
 *
 * @author Vineet
 */
public class MiniClass extends Application {
    
   // public static String intro="intro";
   // public static String introFile="/miniclass/ui/Intro.fxml";
    public static String login="login";
    public static String loginFile="/miniclass/ui/Login.fxml";
    public static String main="main";
    public static String mainFile="/miniclass/menu/Menu.fxml";
    public static String discuss="discuss";
    public static String discussFile="/miniclass/menu/discuss/DiscussM.fxml";
    public static String contact="contact";
    public static String contactFile="/miniclass/menu/contact/ContactUs.fxml";
    public static String live="live";
    public static String liveFile="/miniclass/menu/liv/Live.fxml";
    public static String quiz="quiz";
    public static String quizFile="/miniclass/menu/qui/QuizS.fxml";
    public static String schedule="schedule";
    public static String scheduleFile="/miniclass/menu/sche/Sche.fxml";
    public static String material="material";
    public static String materialFile="/miniclass/menu/mate/Mate.fxml";
    public static String newly="newly";
    public static String newlyFile="/miniclass/menu/discuss/Newly.fxml";
    public static String ongo="ongo";
    public static String ongoFile="/miniclass/menu/discuss/OnGo.fxml";
    public static String searchy="searchy";
    public static String searchyFile="/miniclass/menu/discuss/Searchy.fxml";
    public static String searchyC="searchyc";
    public static String searchyCFile="/miniclass/menu/discuss/SearchyC.fxml";
    public static String leader="leader";
    public static String leaderFile="/miniclass/menu/qui/Leader.fxml";
    public static String quizC="quizc";
    public static String quizCFile="/miniclass/menu/qui/QuizC.fxml";
    public static String quizE="quize";
    public static String quizEFile="/miniclass/menu/qui/QuizE.fxml";
    public static String look="look";
    public static String lookFile="/miniclass/menu/qui/Look.fxml";
    
    @Override
    public void start(Stage stage) throws IOException {
    ScreensController mainContainer=new ScreensController();
   // mainContainer.loadScreen(MiniClass.intro,MiniClass.introFile,new Double(600),new Double(400));
    mainContainer.loadScreen(MiniClass.login,MiniClass.loginFile,new Double(646),new Double(420));
    mainContainer.loadScreen(MiniClass.main,MiniClass.mainFile,new Double(752),new Double(540));
    mainContainer.loadScreen(MiniClass.discuss,MiniClass.discussFile,new Double(600),new Double(430));
    mainContainer.loadScreen(MiniClass.quiz,MiniClass.quizFile,new Double(580),new Double(543));
    mainContainer.loadScreen(MiniClass.look,MiniClass.lookFile,new Double(580),new Double(543));
    mainContainer.loadScreen(MiniClass.material,MiniClass.materialFile,new Double(600),new Double(459));
    mainContainer.loadScreen(MiniClass.schedule,MiniClass.scheduleFile,new Double(626),new Double(645));
    mainContainer.loadScreen(MiniClass.contact,MiniClass.contactFile,new Double(605),new Double(435));
    mainContainer.loadScreen(MiniClass.live,MiniClass.liveFile,new Double(600),new Double(400));
    mainContainer.loadScreen(MiniClass.quizC,MiniClass.quizCFile,new Double(600),new Double(553));
    mainContainer.loadScreen(MiniClass.quizE,MiniClass.quizEFile,new Double(600),new Double(539));
    mainContainer.loadScreen(MiniClass.leader,MiniClass.leaderFile,new Double(620),new Double(549));
    mainContainer.loadScreen(MiniClass.newly,MiniClass.newlyFile,new Double(610),new Double(430));
    mainContainer.loadScreen(MiniClass.ongo,MiniClass.ongoFile,new Double(600),new Double(460));
    mainContainer.loadScreen(MiniClass.searchy,MiniClass.searchyFile,new Double(695),new Double(600));
    mainContainer.loadScreen(MiniClass.searchyC,MiniClass.searchyCFile,new Double(600),new Double(410));
    mainContainer.setScreen(MiniClass.login);
    mainContainer.setStage(stage);
   
    Group root=new Group();
    root.getChildren().addAll(mainContainer);
    Scene scene=new Scene(root);
    scene.setFill(Color.TRANSPARENT);
    stage.setScene(scene);
    stage.setAlwaysOnTop(true);
    stage.setResizable(true);
    //stage.setFullScreen(false);
    stage.initStyle(StageStyle.UTILITY);
    stage.show();
    
    }
    
    public static void main(String[] args) {
        launch(args);
    }
  
}
