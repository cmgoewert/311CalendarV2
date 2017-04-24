/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface;

import java.net.URL;
import javax.swing.*;

/**
 *
 * @author cmg5831
 */
public class ParentFrame extends JFrame{
    URL imageUrl;
    
    public ParentFrame(){
        this.setVisible(true);
        this.setSize(1000, 700);
        this.setTitle("Task Manager");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon("calendar.png").getImage());
    }
}
