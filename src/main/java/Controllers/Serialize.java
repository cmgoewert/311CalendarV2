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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author cmg5831
 */
public class Serialize {
    Gson gson = new Gson();
    
    public Serialize(){
 
    }

    public void write(UserList theUsers) {
        String output = gson.toJson(theUsers);
        System.out.println("Write: " + output);
        try {
            PrintWriter pw = new PrintWriter(new File("users.json"));
            pw.write(output);
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public UserList read(){
        UserList tempUserList = null;
        String input = "";
        String thisLine = "";
        try{
            BufferedReader in = new BufferedReader(new FileReader("users.json"));
            while((thisLine = in.readLine()) != null){
                input = input.concat(thisLine);
            }
            System.out.println("Read: " + input);
            tempUserList = gson.fromJson(input, UserList.class);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return tempUserList;
    }
}
