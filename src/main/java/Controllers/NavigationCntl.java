/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Contact;
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
    private Serialize serialize;
    
    public NavigationCntl(LoginCntl newLoginCntl){
        theMainMenuUI = new MainMenuUI(this);
        theMainMenuUI.setVisible(true);
        parentCntl = newLoginCntl;
        serialize = new Serialize(parentCntl.getUserList());
    }
    
    public void requestContactCntl(){
        contactCntl = new ContactsCntl(this,true);
        theMainMenuUI.setVisible(false);
    }
    
    public void requestTaskCntl(){
        theTaskCntl = new TaskCntl(this);
        theMainMenuUI.setVisible(false);
    }
    
    public void requestThisCntl(){
        theMainMenuUI.setVisible(true);
    }
    
    public ArrayList<Contact> requestContactList(){
        if(contactCntl == null){
            contactCntl = new ContactsCntl(this,false);
        }
        return contactCntl.getContactList();
    }
    
}
