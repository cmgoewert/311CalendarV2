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
public class UserList {
     private ArrayList<User> theListOfUsers;
    
    public UserList(){
        if(theListOfUsers == null){
            buildTestUserList();
        }    
        
    }
    
    public void buildTestUserList(){
        theListOfUsers = new ArrayList();
        for (int i = 0; i < 100; i++){
            String username = ("TestUser" + i);
            char[] password = {'t','e','s','t'};
            User newUser = new User(username, password, "Tester");
            theListOfUsers.add(newUser);
        }
        System.out.println("made new users");
    }
    
    public boolean authenticate(String usernameToCheck, char[] passwordToCheck){
        boolean authenticated = false;
        boolean nameMatch = false;
        boolean passwordMatch = false;
        for (int i = 0; i < theListOfUsers.size(); i++){
            nameMatch = usernameToCheck.equals(theListOfUsers.get(i).getUsername());
            passwordMatch = String.valueOf(passwordToCheck).equals(String.valueOf(theListOfUsers.get(i).getPassword()));
            if(nameMatch && passwordMatch){
                authenticated = true;
                break;
            }
        }
        
        return authenticated;
    }
    
    public void addUser(User userToAdd){
        theListOfUsers.add(userToAdd);
    }
    
}
