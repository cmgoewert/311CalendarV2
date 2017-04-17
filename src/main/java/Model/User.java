/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author cmg5831
 */
public class User {
    private String username;
    private char[] password;
    private String name;
    
    public User(){
        
    }
    
    public User(String newUsername, char[] newPassword, String newName){
        username = newUsername;
        password = newPassword;
        name = newName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }
    
    
}
