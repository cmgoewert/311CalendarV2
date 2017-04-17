/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author cmg5831
 */
public class User {
    private String username;
    private char[] password;
    private String name;
    private ArrayList<Task> tasks;
    private ArrayList<Contact> contacts;
    
    public User(){
        
    }
    
    public User(String newUsername, char[] newPassword, String newName){
        username = newUsername;
        password = newPassword;
        name = newName;
        tasks = new ArrayList<>();
        contacts = new ArrayList<>();
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
    
    public ArrayList<Task> getTasks(){
        return tasks;
    }
    
    public ArrayList<Contact> getContacts(){
        return contacts;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }
    
    
}
