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

/**
 *
 * @author hadin
 */
public class CreateDB {
    private final String filepath;
    private static final String ROOT = System.getProperty("user.home") + "/.NoteKeeper/";
    public CreateDB(String fileName){
        filepath = ROOT + fileName;
        createDB();
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
}
