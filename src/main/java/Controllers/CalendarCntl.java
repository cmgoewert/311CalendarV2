/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Task;
import Model.User;
import UserInterface.CalendarUI;
import java.util.ArrayList;

/**
 *
 * @author cmgoe
 */
public class CalendarCntl {
    private NavigationCntl parentCntl;
    private User currUser;
    private CalendarUI theCalendarUI;
    
    public CalendarCntl(NavigationCntl theCntl, User currUser){
        this.currUser = currUser;
        parentCntl = theCntl;
        theCalendarUI = new CalendarUI(this);
    }
    
    public void requestMenuCntl(){
        parentCntl.requestThisCntl();
    }
    
    public int[][] requestTasks(){
        ArrayList<Task> theTasks = currUser.getTasks();
        int[][]taskInfo = new int[theTasks.size()][2];
        for(int j = 0; j< theTasks.size(); j++){
                String date = theTasks.get(j).getDateString().substring(8, 10);
                System.out.println(date);
                String taskMonth = theTasks.get(j).getDateString().substring(4,7);
                System.out.println(taskMonth);
                String[] monthNames = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
                for(int i = 0; i < monthNames.length; i++){
                    if(taskMonth.equalsIgnoreCase(monthNames[i])){
                        taskInfo[j][0] = i;
                    }
                }
                taskInfo[j][1] = Integer.parseInt(date);
            }
//        for(int j = 0; j<theTasks.size(); j++){
//            System.out.println(theTasks.get(j).getDescription() + j);
//        }
        
        
        return taskInfo;
    }
}
