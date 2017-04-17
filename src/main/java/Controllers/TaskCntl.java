/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Contact;
import Model.Task;
import Model.TasksTableModel;
import UserInterface.ContactsUI;
import UserInterface.NewTaskUI;
import UserInterface.TasksUI;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Chandler
 */
public class TaskCntl {
    private NavigationCntl parentCntl;
    private TasksUI theTasksUI;
    private NewTaskUI theNewUI;
    private ArrayList<Task> theTasksList;
    private TasksTableModel taskTableModel;
    
    public TaskCntl(NavigationCntl theCntl){
        parentCntl = theCntl;
        
        if(theTasksList == null){
            theTasksList = new ArrayList<>();
            for(int i = 0; i<50;i++){
                theTasksList.add(new Task("Task"+i,"This is the task description.",LocalDateTime.now().plusMonths(3),null,2));
                System.out.println("Task"+i + "This is the task description." + LocalDateTime.now().plusMonths(3) + null + 2);
            }
        }
        taskTableModel = new TasksTableModel(theTasksList);
        theTasksUI = new TasksUI(this);
        theTasksUI.setVisible(true);
    }
    
    public void requestMainMenu(){
        theTasksUI.setVisible(false);
        parentCntl.requestThisCntl();
    }
    
    public void requestNewTask(){
        theNewUI = new NewTaskUI(this);
    }
    
    public void addNewTask(Task newTask){
        if(theTasksList == null){
            theTasksList = new ArrayList<>();
            for(int i = 0; i<50;i++){
                theTasksList.add(new Task("Task"+i,"This is the task description.",LocalDateTime.now().plusMonths(3),null,2));
                System.out.println("Task"+i + "This is the task description." + LocalDateTime.now().plusMonths(3) + null + 2);
            }
        }
        theTasksList.add(newTask);
        //System.out.println(newContact.getAddress());//testline
        theNewUI.dispose();
    }
    
    public TasksTableModel getTableModel(){
        return taskTableModel;
    }
    
    public ArrayList<Contact> requestContacts(){
        return parentCntl.requestContactList();
    }
}
