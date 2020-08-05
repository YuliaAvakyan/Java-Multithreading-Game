/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseagents.characters;
import courseagents.myscene.GameScene;
import courseagents.Config;
import java.util.ArrayList;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.image.Image;

/**
 *
 * @author Юлия
 */
public class Agent extends Pane implements Runnable{

   
    final double rnd = rnd(Config.MIN, Config.MAX);
    
    private double agCharge = rnd; //Энергия агента  
    Image image;
    
    //параметры Rectangle
    int offsetX = 0;
    int offsetY = 0;
    int width = 50;
    int height = 64;
    Rectangle removeRect = null;
    
    ArrayList<Rectangle> eat;
    Pane root;
    int way = 1;
    boolean flagPause = false;
    String name;
    
    /**
     *Конструктор с параметрами для класса Agent;
     * В качестве параметров принимает Image, ArrayList, Pane, String,
     * которые определяют изображение, массив с бонусами, панель для размещения и имя объекта.
     * @param image изображение для агента.
     * @param eat ArrayList с бонусами.
     * @param root объект Pane.
     * @param name имя агента.
     * 
     * 
     * 
     */
    public Agent(Image image, ArrayList<Rectangle> eat, Pane root, String name) {
        this.image = image;
        ImageView imageView = new ImageView(image);
        imageView.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
        this.root = root;
        getChildren().addAll(imageView);
        this.eat = eat;
        this.name = name;
    }
    
    /**
     *
     * Метод генерирует рандомное число в указанном диапазоне.
     * @param min минимальная граница диапазона.
     * @param max максимальная граница диапазона.
     * @return рандомное число типа double.
     */
    public static double rnd(double min, double max){
        max -= min;
        return (double) (Math.random() * ++max) + min;
    }
    
    /**
     *Метод возвращает значение true, если энергия объекта класса Agent меньше или равна нулю;
     * обнуляет энергию и удаляет объект с панели;
     * Иначе возвращает false.
     * @return boolean.
     * 
     */
    public boolean killDavid(){
        if(agCharge <= 0){
            agCharge = 0;
            root.getChildren().remove(this);
            return true;
        }
        return false;
    }
    
    /**
     *Метод вовзращает значение энергии объекта за каждый шаг;
     * Энергия уменьшается на величину значения ENERGY_LOSS_PER_STEP.
     * @return agCharge.
     * 
     */
    public double lossEnergy(){
        agCharge -= Config.ENERGY_LOSS_PER_STEP;
        return agCharge;
    }
    
    /**
     *Метод описывает перемещение объекта по оси OX;
     * Если x больше 0, перемещение на 1 вправо, иначе на 1 влево;
     * Внутри метода вызваются метод lossEnergy() и isBonusedEat().
     * @param x координата x.
     * 
     */
    public void moveX(double x){
        boolean right = x>0 ? true : false;
        for(int i = 0; i < Math.abs(x); i++) {
            if(right)
                this.setTranslateX(this.getTranslateX() + 1);
            else
                this.setTranslateX(this.getTranslateX() - 1);
            lossEnergy();
            isBonuseEat();
        }
    }
    
    /**
     *Метод описывает перемещение объекта по оси OY;
     * Если y больше 0, перемещение на 1 вверх, иначе на 1 вниз;
     * Внутри метода вызваются метод lossEnergy() и isBonusedE.
     * @param y координата y.
     * 
     */
    public void moveY(double y) {
        boolean down = y > 0 ? true : false;
        for (int i = 0; i < Math.abs(y); i++) {
            if(down)
                this.setTranslateY(this.getTranslateY() + 1);
            else
                this.setTranslateY(this.getTranslateY() - 1);
            lossEnergy();
            isBonuseEat();
        }
    }
    
    /**
     * Метод описывает поведение объекта класса Agent;
     * Проверяется, достиг ли объект края сцены;
     * Если да, то меняет направление движения с помощью переменной way;
     * Default value: way = 1;
     * Проверяется уровень энергии объекта: если он меньше MIN_ENERGY, объект движется в сторону ближайшего объекта класса Food. 
     */
    public void update() {
        if (eat.size() < 1)
        {
            return;
        }
       
        double xP = getTranslateX();
        double yP = getTranslateY();
        double xE = eat.get(0).getX();
        double yE = eat.get(0).getY();
        Rectangle index = eat.get(0);
        double minS = Math.abs(xE - xP) + Math.abs(yE - yP);
        
        
        if((xP >= 550)||(yP >= 500)){            
            way = way*(-1);
        }
        if((xP <= 0)||(yP <= 0)){
            way = way*(-1);
        }
        if(agCharge < Config.MIN_ENERGY){
        for (Rectangle i: eat)
        {
            xE = i.getX();
            yE = i.getY();
            double test = Math.abs(xE - xP) + Math.abs(yE - yP);
            if (test < minS){
                minS = test;
                index = i;
            }
        }
        xE = index.getX();
        yE = index .getY();
        double x = (xE - xP);
        double y = (yE - yP);
        if (y < 0) {
            moveY(-1);
        } else if (y > 0) {
            moveY(1);
        } else if (x > 0) {
            moveX(1);
        } else if (x < 0) {
            moveX(-1);
        }
        }
        else{
            moveX(way*Math.random()*0.5);
            moveY(way*Math.random()*0.5);
        }
       }
    
    /**
     * В данном методе происходит увеличение энергии объекта Agent за счет столкновения с объектами класса Food;
     * Энергия увеличивается на значение ENERGY_ADDED_ON_EAT;
     * Объект класса Food удаляется с панели игры.
     */
    public void isBonuseEat(){
        eat.forEach((rect) -> {
            if (this.getBoundsInParent().intersects(rect.getBoundsInParent())) {
                removeRect = rect;
                agCharge += Config.ENERGY_ADDED_ON_EAT;                
                System.out.println(agCharge);
            }
        });
        eat.remove(removeRect);
        root.getChildren().remove(removeRect);
    }
            
    /**
     *
     * Вовзращает занчение энергии объекта Agent.
     * @return agCharge
     */
    public double getAgCharge(){
        return agCharge;
    }
    
    /**
     *Вовзращает имя объекта Agent.
     * @return name
     * 
     */
    public String getName(){
        return name;
    }
    
    /**
     *Установка флага паузы.
     * @param flagPause
     * 
     */
    public void setFlagPause(boolean flagPause){
        this.flagPause = flagPause;
    }
    
    /**
     *Получение значения флага паузы.
     * @return flagPause
     * 
     */
    public boolean getFlagPause(){
        return flagPause;
    }
        
    
 
    @Override
    public void run() {
        Platform.runLater(() -> {
                root.getChildren().add(this);
        System.out.println(agCharge);   
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                
                if(GameScene.pauseGame.isPressed()){
                    flagPause = true;
                }
                
                if(GameScene.resumeGame.isPressed()){
                    flagPause = false;
                }
                
                if (flagPause == false){
                    update();
                    if(killDavid()){
                    stop();
                    }
                }
                if(GameScene.stopGame.isPressed()){
                    stop();
                }
                System.out.println(agCharge);
            }
        };
        timer.start();
    });
    }
}


