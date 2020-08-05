/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseagents;

import courseagents.characters.Agent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author Юлия
 */
public class Database {
    
     private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(Config.URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
     
    /**
     *Метод позволяет добавлять записи в базу данных: имя агента, энергию и его координаты;
     * Осуществляется проверка соединения с базой данных
     * @param a объект класса Agent.
     * 
     */
    public void insert(Agent a) {
        
        double energy = a.getAgCharge();
        String name = a.getName();
        double x = a.getTranslateX();
        double y = a.getTranslateY();
        String sql = "INSERT INTO agentval(name, energy, x, y) VALUES('"  + name + "','" + energy + "','" + x + "','" + y + "')";
        
        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
            System.out.println("Added!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Выбор записей из базы данных и вывод их в консоль.
     */
    public void selectAll(){
        String sql = "SELECT id, name, energy, x, y FROM agentval";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" + 
                                   rs.getString("name") + "\t" +
                                   rs.getDouble("energy") + "\t" +
                                   rs.getDouble("x") + "\t" +
                                   rs.getDouble("y"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }        
    }

    /**
     *Метод закрывает соединение с базой данных.
     * @throws SQLException
     * 
     */
    public void closeDB() throws SQLException{
            connect().close();
        }
}
