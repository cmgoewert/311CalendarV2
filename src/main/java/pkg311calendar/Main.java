/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg311calendar;

import Controllers.LoginCntl;
import Controllers.NavigationCntl;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author Chandler
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            
//          UIManager.setLookAndFeel(
//              UIManager.getSystemLookAndFeelClassName());
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            //UIManager.put("InternalFrame.activeTitleBackground", new ColorUIResource(Color.black));
        }   
        catch(Exception e){
            
        }
        LoginCntl theLoginCntl = new LoginCntl();
        char[] password = {'t','e','s','t'};
        boolean auth = theLoginCntl.requestAuthenticate("TestUser1", password);
        if(auth){
            theLoginCntl.requestNavCntl();
        }
        
    }
    
}
