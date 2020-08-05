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

public class GameOverScene {
    
    Scene gameOverScene;
    Pane root = new Pane();
    Label gameOver;
    Button repeat;
    Button exit;
    ImageView img, imgW, imgP;
    
    /**
     *Конструктор сцены завершения игры
     * В нем происходит создание новой Scene
     * установка размеров и стиля панели;
     * создание кнопок repeat и exit;
     * добавление картинок и текста.
     */
    public GameOverScene(){
        
        root.setPrefSize(800, 600);
        root.setStyle("-fx-background-color: #FCE4EC");
        
        gameOver = new Label("GAME OVER!");
        gameOver.setStyle(Config.TEXT_STYLE);
        gameOver.setTranslateX(300);
        gameOver.setTranslateY(240);
        root.getChildren().add(gameOver);
        
        repeat = new Button("AGAIN?");
        repeat.setTranslateX(300);
        repeat.setTranslateY(320);
        repeat.setStyle(Config.BUTTON_STYLE);
        root.getChildren().add(repeat);
        
        exit = new Button("EXIT");
        exit.setTranslateX(440);
        exit.setTranslateY(320);
        exit.setStyle(Config.BUTTON_STYLE);
        root.getChildren().add(exit);
         
        Image image = new Image(getClass().getResourceAsStream("pisa.png"));
        img = new ImageView(image);
        img.setTranslateX(5);
        img.setTranslateY(450);
        root.getChildren().add(img);
        
        Image image1 = new Image(getClass().getResourceAsStream("wine.png"));
        imgW = new ImageView(image1);
        imgW.setTranslateX(730);
        imgW.setTranslateY(5);
        root.getChildren().add(imgW);
        
        Image image2 = new Image(getClass().getResourceAsStream("piz.png"));
        imgP = new ImageView(image2);
        imgP.setTranslateX(680);
        imgP.setTranslateY(15);
        root.getChildren().add(imgP);
        
        gameOverScene = new Scene(root);       
                                
    }
    
    /**
     *Метод возвращает объект: сцена завершения игры.
     * @return gameOverScene.
     * 
     */
    public Scene getScene(){
        return gameOverScene;
    }
    
    /**
     *Метод возвращает объект: кнопка exit.
     * @return exit 
     */
    public Button getExitButton(){
        return exit;
    }
    
    /**
     *Метод возвращает объект: кнопка return.
     * @return repeat
     * 
     */
    public Button getRepeatButton(){
        return repeat;
    }
}
