/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseagents.myscene;

import courseagents.Config;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 *
 * @author Юлия
 */
//Стартовая сцена
public class StartScene {
    Button startBt;
    Scene startScene;
    Pane root = new Pane();
    Label title;
    ImageView img, imgP;
    
    /**
     * Конструктор стартовой сцены StartScene
     * В нем происходит создание новой Scene;
     * установка размеров и стиля панели;
     * создание кнопки startBt;
     * добавление картинок и текста.
     */
    public StartScene(){
        
        title = new Label("R U READY?");
        title.setTranslateX(320);
        title.setTranslateY(230);
        title.setStyle(Config.TEXT_STYLE);
        root.getChildren().add(title);
        startBt = new Button("START THE GAME");
        startBt.setTranslateX(340);
        startBt.setTranslateY(320);
        startBt.setStyle(Config.BUTTON_STYLE);
        root.getChildren().add(startBt);
        root.setPrefSize(800, 600);
        root.setStyle("-fx-background-color: #FCE4EC");
        Image image = new Image(getClass().getResourceAsStream("it128.png"));
        img = new ImageView(image);
        img.setTranslateX(5);
        img.setTranslateY(450);
        root.getChildren().add(img);
        
        Image imageP = new Image(getClass().getResourceAsStream("piz.png"));
        imgP = new ImageView(imageP);
        imgP.setTranslateX(110);
        imgP.setTranslateY(500);
        root.getChildren().add(imgP);
        
        startScene = new Scene(root);
    }
    
    /**
     *Метод возвращает кнопку startBt.
     * @return startBt
     * 
     */
    public Button getButtonStart(){
        return startBt;
    }
    
    /**
     *Метод возвращает объект стартовой сцены startScene.
     * @return startScene
     */
    public Scene getScene(){
        return startScene;
    }
}
