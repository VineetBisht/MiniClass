/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniclass.menu.contact;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.VPos;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import miniClass.ui.MiniClass;
import miniclass.ui.ControlledScreen;
import miniclass.ui.ScreensController;

/**
 * FXML Controller class
 *
 * @author Vineet
 */
public class ContactUsController implements Initializable, ControlledScreen {

    ScreensController myController;
    
    @FXML
    private ImageView back;
    
    @FXML
    private VBox bye;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String message="Vineet Singh Bisht\nSubham Soni\nJaivardhan Singh Rana\nChirag Gulati";
             
Text textRef=new Text(message);
textRef.setVisible(true);
textRef.setLayoutY(150);
textRef.setTextOrigin(VPos.BOTTOM);
textRef.setTextAlignment(TextAlignment.CENTER);
textRef.setWrappingWidth(350);
textRef.setFill(Color.DARKGOLDENROD);
textRef.setFont(Font.font("Arial Black",FontWeight.LIGHT,24));
InnerShadow innerShadow = new InnerShadow();
innerShadow.setColor(Color.web("0x3b596d"));
textRef.setEffect(innerShadow);
TranslateTransition transTransition=new TranslateTransition(new Duration(25000),textRef);
transTransition.setToY(-150);
transTransition.setFromY(250);
transTransition.setInterpolator(Interpolator.LINEAR);
transTransition.setCycleCount(Timeline.INDEFINITE);
bye.getChildren().add(textRef);
bye.setClip(new Rectangle(560,185));
transTransition.play();
// TODO
    }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    @FXML
    public void backEventHandler(MouseEvent me){
        myController.setScreen(MiniClass.main);
    }
    
    
}
