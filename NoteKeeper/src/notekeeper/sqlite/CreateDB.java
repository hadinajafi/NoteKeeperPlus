/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notekeeper.sqlite;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author hadin
 */
public class CreateDB {
    private final String filepath;
    public CreateDB(String fileName){
        filepath = notekeeper.NoteKeeper.ROOT + fileName;
        createDB();
        createNewTable();
    }
    public String getPath(){
        return filepath;
    }
    
    private void createDB(){
        String url = "jdbc:sqlite:" + filepath;
        try(Connection conn = DriverManager.getConnection(url)){
            if(conn != null){
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("A new database has been created");
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    private void createNewTable(){
        String url = "jdbc:sqlite:" + filepath;
        String sql = "CREATE TABLE IF NOT EXIST notes (\n"
                + "id INTEGER PRIMARY KEY autoincrement, "
                + "title TEXT, "
                + "body TEXT, "
                + "date TEXT, "
                + "font TEXT NOT NULL, "
                + "size INTEGER NOT NULL, "
                + "dir TEXT NOT NULL;";
        try (Connection conn = DriverManager.getConnection(url)){
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
