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
public class DeleteNote {

    public DeleteNote(String filePath) {
        
    }
    
    private void DeleteNote(int id){
        String url = "jdbc:sqlite:" + CreateDB.DBFILE;
        try(Connection c = DriverManager.getConnection(url)){
            String sql = "DELETE FORM notes WHERE id = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
}
