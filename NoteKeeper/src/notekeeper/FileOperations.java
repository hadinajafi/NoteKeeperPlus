/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notekeeper;

import java.io.File;
import java.util.Properties;

/**
 *
 * @author hadin
 */
public class FileOperations {
    private File profile;
    private File file;
    private String root;
    private String fileName;
    private Properties profileData;
    private Properties fileData;

    public FileOperations() {
        profileData = new Properties();
        fileData = new Properties();
        root = System.getProperty("user.home") + "/.NoteKeeper/";
        createAppDirectories();
    }
    
    private void createAppDirectories(){
        file = new File(root);
        if(!file.exists()){
            file.mkdirs();
        }
    }
    
    private Properties getProfileData(){
        return this.profileData;
    }
    
    private void setProfileData(Properties data){
        this.profileData = data;
    }
    
    private Properties getUserData(){
        return fileData;
    }
    
    private void setUserData(Properties data){
        this.fileData = data;
    }
    
    private void writeProfileFile(){
        
    }
    
    private void writeUserDataFile(){
        
    }
    
}
