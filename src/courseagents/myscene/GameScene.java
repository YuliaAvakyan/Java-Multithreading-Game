/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseagents.myscene;


import courseagents.characters.Agent;
import courseagents.Config;
import courseagents.food.Food;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author Юлия
 */

public class GameScene {
    public static Thread[] AGENT_THREAD;
    public static ArrayList<Agent> agents;
    public static Button stopGame;
    public static Button pauseGame;
    public static Button resumeGame;
    Pane root;
    Food food;
    Scene scene;
    
    /**
     * Конструктор сцены игры
     * В нем происходит создание новой Scene;
     * Внутри него создается панель Pane, устанавливаются размеры и стили;
     * создается объект Food;
     * происходит добавление кнопок stopGame, pauseGame, resumeGame.
     */
    public GameScene(){
        root = new Pane();
        food = new Food(root);
       
        stopGame = new Button("STOP THE GAME");
        stopGame.setStyle(Config.BUTTON_STYLE);
        stopGame.setTranslateX(650);
        stopGame.setTranslateY(500);        
        
        pauseGame = new Button("PAUSE");
        pauseGame.setStyle(Config.BUTTON_STYLE);
        pauseGame.setTranslateX(650);
        pauseGame.setTranslateY(450);       
        
        resumeGame = new Button("RESUME");
        resumeGame.setStyle(Config.BUTTON_STYLE);
        resumeGame.setTranslateX(650);
        resumeGame.setTranslateY(400);          
        
        root.setPrefSize(800, 600);  
        root.setStyle("-fx-background-color: #FCE4EC");
        scene = new Scene(root);
        
    
    }
    
    /**
     * Рестарт игры
     * Внутри метода происходит очищение панели от предыдущей игры,
     * создание новых агентов, создание лейблов для вывода энергии, добавление кнопок на панель.
     */
    public void restart(){
        root.getChildren().clear();
        createAgThr();
        createLabels();      
        root.getChildren().add(stopGame);             
        root.getChildren().add(pauseGame); 
        root.getChildren().add(resumeGame);
    }
    
    /**
     *В данном методе происходит оздание группы агентов, установка координат агента, добавление картинки;
     *Создается массив потоков, каждый агент добавляется в поток.
     */
    public void createAgThr(){
        AGENT_THREAD = new Thread[Config.THREADS];
        agents = new ArrayList<>();
        for(int i = 0; i < Config.THREADS; i++) {
            Image image = new Image(getClass().getResourceAsStream(String.valueOf(i) + ".png"));
            String name = Config.NAMES[i];
            Agent player = new Agent(image, food.getList(), root, name);
            agents.add(player);
            agents.get(0).setTranslateX(Math.floor(Math.random()*549));
            agents.get(0).setTranslateY(Math.floor(Math.random()*499));
            if(i>0){
               agents.get(i).setTranslateX(Math.floor(Math.random()*549));
               agents.get(i).setTranslateY(Math.floor(Math.random()*499));
               if(agents.get(i-1).getTranslateX() == agents.get(i).getTranslateX()){
                   agents.get(i).setTranslateX(agents.get(i-1).getTranslateX() + Config.X_DISTANCE_BETWEEN_AGENTS);
                   agents.get(i).setTranslateY(agents.get(i-1).getTranslateY() + Config.Y_DISTANCE_BETWEEN_AGENTS);
               }
            }
            AGENT_THREAD[i] = new Thread(agents.get(i));
            AGENT_THREAD[i].start();
            
    
                
        }
    }
    
    /**
     * Внутри метода создается массив Label для вывода данных о энергии агента;
     * Энергия агента передается в label внутри AnimationTimer.
     */
    public void createLabels(){
        Label[] label = new Label[Config.THREADS];
        for(int i = 0; i < Config.THREADS; i++){
                    label[i] = new Label();
                    label[i].setTranslateX(580);
                    label[i].setTranslateY(10 + i*20);
                    label[i].setFont(new Font("Lato", 18));
                    root.getChildren().add(label[i]);  
                }
             
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for(int i = 0; i < Config.THREADS; i++){
                   label[i].setText(String.format(" David's energy: %.3f", agents.get(i).getAgCharge()));
                   label[i].setTextFill(Color.web(Config.COLORS[i]));
                }                
            }
        };
        timer.start();     
    }
    
    /**
     *Метод возвращает объект сцены GameScene.
     * @return scene.
     * 
     */
    public Scene getScene(){
        return scene;
    }
    
    /**
     *Метод вовзращает кнпоку stopGame.
     * @return stopGame.
     * 
     */
    public Button getButtonStop(){
        return stopGame;
    }
}
