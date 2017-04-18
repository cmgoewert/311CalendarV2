/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface;

import Controllers.CalendarCntl;

/**
 *
 * @author cmgoe
 */
public class CalendarUI extends ParentFrame{
    private CalendarCntl parentCntl;
    
    
    public CalendarUI(CalendarCntl theCntl){
        parentCntl = theCntl;
        initComponents();
    }
    
    private void initComponents(){
        
    }
}
