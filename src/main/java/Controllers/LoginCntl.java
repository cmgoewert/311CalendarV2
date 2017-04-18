/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.User;
import Model.UserList;
import UserInterface.LoginUI;
import UserInterface.SignUpUI;

/**
 *
 * @author cmg5831
 */
public class LoginCntl {
    private LoginUI theLoginUI;
    private UserList theUserList;
    private NavigationCntl theNavigationCntl;
    private SignUpUI theSignUpUI;
    
    public LoginCntl(){
        theLoginUI = new LoginUI(this);
        theUserList = new Serialize().read();
//        if(theUserList == null){
//            theUserList = new UserList();
//        } 
    }
    
    public boolean requestAuthenticate(String usernameToCheck, char[] passwordToCheck){
        boolean result = theUserList.authenticate(usernameToCheck, passwordToCheck);
        return result;
    }
    
    public void requestNavCntl(String username){
        theNavigationCntl = new NavigationCntl(this, username);
        theLoginUI.setVisible(false);
        
    }
    
    public void requestSignUp(){
        theSignUpUI = new SignUpUI(this);
    }
    
    public void addNewUser(User userToAdd){
        theUserList.addUser(userToAdd);
        new Serialize().write(theUserList);
        theUserList = new Serialize().read();
    }
    
    public UserList getUserList(){
        return theUserList;
    }
}
