/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Contact;
import Model.Task;
import Model.UserList;
import com.google.gson.Gson;
import java.util.ArrayList;

/**
 *
 * @author cmg5831
 */
public class Serialize {
    Gson gson = new Gson();
    
    public Serialize(UserList theusers){
        String output = gson.toJson(theusers);
           System.out.println(output);
    }
    
    public Serialize(UserList theusers, ArrayList<Task> theTasks, ArrayList<Contact> theContacts){
        
    }
}
