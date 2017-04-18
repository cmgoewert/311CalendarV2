/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.User;
import UserInterface.CalendarUI;

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
}
