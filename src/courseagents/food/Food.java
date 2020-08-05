/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseagents.food;

import courseagents.myscene.GameScene;
import java.util.ArrayList;
import javafx.scene.shape.Rectangle;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;

public class Food {
    
    private final ArrayList<Rectangle> bonuses;
    Image image1; 
    Pane root;
    boolean paused = false;
    
    /**
     *Конструктор класса Food;
     * В качестве параметра передается панель Pane;
     * Создается ArrayList для хранения объектов Food;
     * Внутри конструктора создается AnimationTimer, в котором вызывается метод генерации еды;
     * происходит обработка кнопок Pause и Resume.
     * @param root
     * 
     */
    public Food(Pane root){
        this.root = root;
        bonuses = new ArrayList<>();
        image1 = new Image(getClass().getResourceAsStream("pizza.png"));
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(GameScene.resumeGame.isPressed()){
                    paused = false;
                }
                if(GameScene.pauseGame.isPressed()){
                    paused = true;
                }
                if (paused == false){
                    image1 = new Image(getClass().getResourceAsStream("pizza.png"));
                    bonus(image1);
                }
            }
        };
        timer.start();
    }
    
    /**
     *Метод генерирует координаты для объектов Food, создает Rectangle и заносит его в ArrayList;
     * Созданные Rectangle добавляются на панель.
     * @param image image.
     * 
     */
    public void bonus(Image image){
        int random = (int)Math.floor(Math.random()*30);
        int x = 10 + (int)Math.floor(Math.random()*550);
        int y = 10 + (int)Math.floor(Math.random()*550);
        if(random == 5){
           Rectangle rect = new Rectangle(20,20);
           rect.setFill(new ImagePattern(image));
           rect.setX(x);
           rect.setY(y);
           bonuses.add(rect);
           root.getChildren().addAll(rect);            
        }
    }
    
    /**
     *Возвращает ArrayList c Rectangle из класса Food.
     * @return bonuses.
     * 
     */
    public ArrayList<Rectangle> getList(){
        return bonuses;
    }

}
