/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Contact;
import Model.User;
import Model.UserList;
import UserInterface.MainMenuUI;
import java.util.ArrayList;

/**
 *
 * @author Chandler
 */
public class NavigationCntl {
    private MainMenuUI theMainMenuUI;
    private LoginCntl parentCntl;
    private ContactsCntl contactCntl;
    private TaskCntl theTaskCntl;
    private CalendarCntl theCalendarCntl;
    private Serialize serialize;
    private UserList theUserList;
    private String username;
    private User currUser;
    
    public NavigationCntl(LoginCntl newLoginCntl, String username){
        serialize = new Serialize();
        theUserList = serialize.read();
        this.username = username;
        currUser = theUserList.getCurrUser(username);
        theMainMenuUI = new MainMenuUI(this);
        theMainMenuUI.setVisible(true);
        parentCntl = newLoginCntl;
        
    }
    
    public void requestContactCntl(){
        contactCntl = new ContactsCntl(this,true,currUser);
        theMainMenuUI.setVisible(false);
    }
    
    public void requestCalendarCntl(){
        theCalendarCntl = new CalendarCntl(this,currUser);
        theMainMenuUI.setVisible(false);
    }
    
    public void requestTaskCntl(){
        theTaskCntl = new TaskCntl(this,currUser);
        theMainMenuUI.setVisible(false);
    }
    
    public void requestThisCntl(){
        theMainMenuUI.setVisible(true);
    }
    
    public ArrayList<Contact> requestContactList(){
        if(contactCntl == null){
            contactCntl = new ContactsCntl(this,false,currUser);
        }
        return theUserList.getCurrUser(username).getContacts();
    }
    
    public UserList getUserList(){
        return theUserList;
    }
    
    public void setUserList(UserList newUserList){
        theUserList = newUserList;
    }
    
}
