package courseagents;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



/**
 *
 * @author Юлия
 */
public class Config {
    public static int THREADS;
    public static double X_DISTANCE_BETWEEN_AGENTS;
    public static double Y_DISTANCE_BETWEEN_AGENTS;
    public static String[] COLORS;
    public static String[] NAMES;
    public static double START_ENERGY;
    public static double MIN_ENERGY;
    public static double ENERGY_ADDED_ON_EAT;
    public static double ENERGY_LOSS_PER_STEP;
    public static double MIN;
    public static double MAX;
    public static String BUTTON_STYLE;
    public static String TEXT_STYLE;
    public static String URL;
    
    /**
     *Метод получает значения из файла конфигурации.
     */
    public static void getValues() {
	
		final Properties props = new Properties();
                try {
                InputStream inputStream = new FileInputStream("config.properties");            
                props.load(inputStream);
                
		THREADS = Integer.parseInt(props.getProperty("threads"));
                X_DISTANCE_BETWEEN_AGENTS = Integer.parseInt(props.getProperty("xdistance"));
                Y_DISTANCE_BETWEEN_AGENTS = Integer.parseInt(props.getProperty("ydistance"));
		
		final String[] parts = props.getProperty("colors").split(";");
		COLORS = new String[parts.length];
		for(int i = 0; i < parts.length; i++)
		{
                    COLORS[i] = parts[i];
		}
                
                final String[] names = props.getProperty("names").split(";");
		NAMES = new String[names.length];
		for(int i = 0; i < names.length; i++)
		{
                    NAMES[i] = names[i];
		}
                
		START_ENERGY = Double.parseDouble(props.getProperty("startenergy"));
                MIN_ENERGY = Double.parseDouble(props.getProperty("minenergy"));
                ENERGY_ADDED_ON_EAT = Double.parseDouble(props.getProperty("energeadded"));
                ENERGY_LOSS_PER_STEP = Double.parseDouble(props.getProperty("energyloss"));
                MIN = Double.parseDouble(props.getProperty("min"));
                MAX = Double.parseDouble(props.getProperty("max"));
                BUTTON_STYLE = String.valueOf(props.getProperty("buttonStyle"));
                TEXT_STYLE = String.valueOf(props.getProperty("textStyle"));
                URL = String.valueOf(props.getProperty("url"));
                 } catch (IOException | NumberFormatException ex) {
            
        }
    }

}