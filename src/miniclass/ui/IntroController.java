/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniclass.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import miniClass.ui.MiniClass;

/**
 * FXML Controller class
 *
 * @author Vineet
 */
public class IntroController implements Initializable, ControlledScreen {
    @FXML
    private MediaView vid; 
    ScreensController myController;
    /**
     * Initializes the controller class.
     */
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    String source="/miniclass/resources/Intro.mp4";
    Media media=new Media(source);
    MediaPlayer mediaPlayer=new MediaPlayer(media);
    mediaPlayer.setAutoPlay(true);
    vid=new MediaView(mediaPlayer);
    login();
    }    
    
    private  void login(){
        myController.setScreen(MiniClass.login);
    }
    
}
