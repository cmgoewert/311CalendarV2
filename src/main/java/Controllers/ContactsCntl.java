/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Contact;
import Model.ContactsTableModel;
import Model.User;
import UserInterface.ContactsUI;
import UserInterface.NewContactUI;
import java.util.ArrayList;


/**
 *
 * @author Chandler
 */
public class ContactsCntl {
    private NavigationCntl parentCntl;
    private ContactsUI theContactsUI;
    private NewContactUI theNewUI;
    private ArrayList<Contact> theContactList;
    private ContactsTableModel contactTableModel;
    private User currUser;
    
    public ContactsCntl(NavigationCntl theCntl, boolean display, User currUser){
        parentCntl = theCntl;
        this.currUser = currUser;
        theContactList = currUser.getContacts();
        if(theContactList == null){
            theContactList = new ArrayList<>();
            for(int i = 0; i < 15;i++){
                theContactList.add(new Contact("Gerald","Contact"+i,"The Moon",Integer.toString(i),"Father"));
                System.out.println("Gerald"+"Contact"+i+"The Moon"+Integer.toString(i)+"Father");
            }
        }
        contactTableModel = new ContactsTableModel(theContactList);
        theContactsUI = new ContactsUI(this);
        if(display){
            
        }
        else{
            theContactsUI.setVisible(false);
        }
        
    }
    
    public void requestMainMenu(){
        theContactsUI.setVisible(false);
        parentCntl.requestThisCntl();
    }
    
    public User getUser(){
        return currUser;
    }
    
    public void updateUsers(){
        new Serialize().write(parentCntl.getUserList());
        parentCntl.setUserList(new Serialize().read());
    }
    
    public void requestNewContact(int contactIndex){
        theNewUI = new NewContactUI(this, contactIndex);
    }
    
    public void changeContact(Contact newContact, int index){
        theContactList.set(index, newContact);
        currUser.setContacts(theContactList);
        updateUsers();
    }
    
    public void exportContact(int[] index){
        CsvExporter export = new CsvExporter();
        ArrayList<Contact> exportList = new ArrayList<>();
        for(int i = 0; i< index.length; i++){
            exportList.add(theContactList.get(index[i]));
        }
        export.writeToCSV(exportList);
    }
    
    public void addNewContact(Contact newContact){
        if(theContactList == null){
            theContactList = new ArrayList<Contact>();
            for(int i = 0; i < 15;i++){
                theContactList.add(new Contact("Gerald","Contact"+i,"The Moon",Integer.toString(i),"Father"));
            }
        }
        theContactList.add(newContact);
        currUser.setContacts(theContactList);
        new Serialize().write(parentCntl.getUserList());
        parentCntl.setUserList(new Serialize().read());
        //System.out.println(newContact.getAddress());//testline
        theNewUI.dispose();
    }
    
    public ContactsTableModel getTableModel(){
        return contactTableModel;
    }
    
    public ArrayList<Contact> getContactList(){
        return theContactList;
    }
}

    
