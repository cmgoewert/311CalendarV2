/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface;

import Controllers.LoginCntl;
import Controllers.NavigationCntl;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Chandler
 */
public class MainMenuUI extends ParentFrame{
    private NavigationCntl parentNavigationCntl;
    private JButton viewCalendar, viewContacts, viewTasks, logout;
    private JPanel mainPanel, navPanel;
    
    public MainMenuUI(NavigationCntl newNavCntl){
        parentNavigationCntl = newNavCntl;
        this.setVisible(true);
        this.setTitle("Task Manager");
        initComponents();
    }
    
    public void initComponents(){
        Font lfont = new Font(Font.SANS_SERIF, Font.BOLD,18);
        Font font = new Font(Font.SERIF,0,16);
        viewCalendar = new JButton("View Calendar");
        viewCalendar.setFont(font);
        viewCalendar.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(25, 0, 25, 0), new EmptyBorder(0,0,0,0)));
        
        viewContacts = new JButton("View Contacts");
        viewContacts.setFont(font);
        viewContacts.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(25, 0, 25, 0), new EmptyBorder(0,0,0,0)));
        viewContacts.addActionListener(new ContactListener());
        
        viewTasks = new JButton("View Tasks");
        viewTasks.setFont(font);
        viewTasks.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(25, 0, 25, 0), new EmptyBorder(0,0,0,0)));
        viewTasks.addActionListener(new TaskListener());
        
        logout = new JButton("Logout");
        logout.setFont(lfont);
        logout.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(25, 0, 25, 0), new EmptyBorder(0,0,0,0)));
        logout.addActionListener(new LogoutListener());
        
        navPanel = new JPanel();
        navPanel.setLayout(new GridLayout(1,2,0,0));
        navPanel.add(logout);
        
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6,1,0,0));
        mainPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(100, 150, 180, 150), new EmptyBorder(0,0,0,0)));
        mainPanel.add(viewCalendar);
        mainPanel.add(viewContacts);
        mainPanel.add(viewTasks);
        
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(navPanel, BorderLayout.SOUTH);
        this.getContentPane().add(mainPanel, BorderLayout.CENTER);
    }
    
    private void logout(){
        this.setVisible(false);
    }
    
    class LogoutListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            LoginCntl theLogin = new LoginCntl();
            logout();
        }
     }
    
    class ContactListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            parentNavigationCntl.requestContactCntl();
        }
    }
    
    class TaskListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            parentNavigationCntl.requestTaskCntl();
        }
    }
}
