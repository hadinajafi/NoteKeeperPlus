/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notekeeper.sqlite;

import java.io.File;
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
    public static final String DBFILE = System.getProperty("user.home") + "/.NoteKeeper/notes.db";
    
    public CreateDB(){
        File path = new File(System.getProperty("user.home") + "/.NoteKeeper/");    //path for making directories
        path.mkdirs();  //create directories for database
        path = new File(DBFILE);    //create new file to ckeck existance of the db file
        if(path.exists())   //stop overwriting the new database with the old one
            return;
        createDB(); //create new notes.db file
        createNewTable();   //create notes table inside notes.db file.
    }
    
    /**
     * Create new sqlite database notes.db in the <i>DBFIlE</i> path.
     */
    private void createDB(){
        String url = "jdbc:sqlite:" + DBFILE;   //db url
        try(Connection conn = DriverManager.getConnection(url)){
            if(conn != null){
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("A new database has been created");
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    /**
     * Create New Table <b>notes</b> in database.
     */
    private void createNewTable(){
        String url = "jdbc:sqlite:" + DBFILE;
        String sql = "CREATE TABLE IF NOT EXISTS notes (\n"
                + "id INTEGER PRIMARY KEY autoincrement, "
                + "title TEXT, "
                + "body TEXT, "
                + "date TEXT, "
                + "font TEXT NOT NULL, "
                + "size INTEGER NOT NULL, "
                + "dir TEXT NOT NULL);";
        try (Connection conn = DriverManager.getConnection(url)){
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
