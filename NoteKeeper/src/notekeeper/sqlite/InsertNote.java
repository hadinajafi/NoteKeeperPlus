/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notekeeper.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author hadin
 */
public class InsertNote {
    /**
     * 
     * @param title The <b>Title</b> of the note.
     * @param body The <b>Body Text</b> of the note.
     * @param date The <b>Date</b> of the modified file.
     * @param font <b>Font</b> of the current file.
     * @param size Font <i>size</i> of the body text.
     * @param dir <b>Direction</b> of the file. Direction is right-to-left or left-to-right.
     */
    public InsertNote(String title, String body, String date, String font, int size, String dir){
        insertData(title, body, date, font, size, dir);
    }
    
    /**
     * 
     * @param title The <b>Title</b> of the note.
     * @param body The <b>Body Text</b> of the note.
     * @param date The <b>Date</b> of the modified file.
     * @param font <b>Font</b> of the current file.
     * @param size Font <b>size</b> of the body text.
     * @param dir <b>Direction</b> of the file. Direction is right-to-left or left-to-right.
     */
    private void insertData(String title, String body, String date, String font, int size, String dir){
        String url = "jdbc:sqlite:" + CreateDB.DBFILE;
        try(Connection c = DriverManager.getConnection(url)){
            String sql = "INSERT INTO notes (title, body, date, font, size, dir) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, body);
            ps.setString(3, date);
            ps.setString(4, font);
            ps.setInt(5, size);
            ps.setString(6, dir);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
