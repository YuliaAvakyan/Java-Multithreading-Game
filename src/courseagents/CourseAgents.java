/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseagents;

import courseagents.myscene.GameOverScene;
import courseagents.myscene.GameScene;
import static courseagents.myscene.GameScene.agents;
import courseagents.myscene.StartScene;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
/**
 *
 * @author Юлия
 */
public class CourseAgents extends Application {
    GameScene mscene;
    StartScene stscene;
    GameOverScene oscene;    
    Media sound;
    MediaPlayer mediaPlayer;
    String musicFile;
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        Config.getValues(); 
        
        musicFile = "Links.mp3";
        sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound); //создание медиаплеера для воспроизведения аудиофайла
        
        stscene = new StartScene(); //создание стартовой сцены
        //установка параметров для primaryStage
        primaryStage.setTitle("DAVID LOVES PIZZA");
        primaryStage.setScene(stscene.getScene());
        primaryStage.setResizable(false);
        primaryStage.show();

        mscene = new GameScene(); //создание сцены игры
        oscene = new GameOverScene(); //создание сцены завершения игры
        
        //при нажатии на кнопку Start the game рестарт игровой сцены, запуск медиаплеера
        stscene.getButtonStart().setOnAction((ActionEvent event) -> { 
            mscene.restart();
            primaryStage.setTitle("DAVID VS PIZZA");
            primaryStage.setScene(mscene.getScene());
            mediaPlayer.play();
        });
        
        //при нажатии на кнопку Stop
        mscene.getButtonStop().setOnAction((ActionEvent event) -> {
            mediaPlayer.stop(); //остановка медиаплеера
            primaryStage.setTitle("GAME OVER");
            primaryStage.setScene(oscene.getScene()); //установка сцены завершения игры
                
           /* Database db = new Database();
                for(int i = 0; i < Config.THREADS; i++){
                    db.insert(agents.get(i)); //запись в БД
                }
                db.selectAll(); //вывод из БД
            try {
                db.closeDB(); //закрытие соединения с БД
            } catch (SQLException ex) {
                Logger.getLogger(CourseAgents.class.getName()).log(Level.SEVERE, null, ex);
            } */         
                
        });
        //при нажатии на кнопку repaet
        oscene.getRepeatButton().setOnAction((ActionEvent event) -> {
                    mscene.restart(); //рестарт игровой сцены
                    primaryStage.setTitle("DAVID VS PIZZA");
                    primaryStage.setScene(mscene.getScene()); //установка игровой сцены
                    mediaPlayer.play();
                     
                });
                //при нажатии кнопки exit: остановка медиаплеера, закрытие primaryStage
                oscene.getExitButton().setOnAction((ActionEvent event) -> {
                    mediaPlayer.stop();
                    primaryStage.close();
                });
        
        
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException IOException 
     */
    public static void main(String[] args) throws IOException, Exception {
        launch(args);
    }
    
}
