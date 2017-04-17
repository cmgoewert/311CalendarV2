/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface;

import Controllers.LoginCntl;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author cmg5831
 */
public class LoginUI extends ParentFrame{
    private LoginCntl parentLoginCntl;
    private JLabel usernameLabel, passwordLabel, incorrect;
    private JTextField username;
    private JPasswordField password;
    private JButton login, signup;
    private JPanel mainPanel, navPanel;
    
    public LoginUI(LoginCntl parentCntl){
        parentLoginCntl = parentCntl;
        this.setTitle("Task Manager Login");
        initComponents();
        this.setVisible(true);
    }
    
    private void initComponents(){
        
        Font lfont = new Font(Font.SANS_SERIF, Font.BOLD,18);
        Font font = new Font(Font.SERIF,0,16);
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        usernameLabel.setFont(lfont);
        passwordLabel.setFont(lfont);
        
        username = new JTextField();
        username.setEditable(true);
        username.setFont(font);
        password = new JPasswordField();
        password.setFont(font);
        password.setEchoChar('\u2022');
        
        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(100, 150, 180, 150), new EmptyBorder(0,0,0,0)));
        mainPanel.setLayout(new GridLayout(6,1,0,0));
        mainPanel.add(usernameLabel);
        mainPanel.add(username);
        mainPanel.add(passwordLabel);
        mainPanel.add(password);
        incorrect = new JLabel("Incorrect username or password!");
        mainPanel.add(incorrect);
        incorrect.setFont(font);
        incorrect.setVisible(false);
        
        
        login = new JButton("Log In");
        login.setFont(lfont);
        login.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(25, 0, 25, 0), new EmptyBorder(0,0,0,0)));
        login.addActionListener(new LoginListener());
        
        signup = new JButton("Sign Up");
        signup.setFont(lfont);
        signup.addActionListener(new SignUpListener());
        
        navPanel = new JPanel();
        navPanel.setLayout(new GridLayout(1,2,0,0));
        navPanel.add(signup);
        navPanel.add(login);
       
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(navPanel, BorderLayout.SOUTH);
        this.getContentPane().add(mainPanel, BorderLayout.CENTER);
        this.getRootPane().setDefaultButton(login);
    }
    
    private void addIncorrectLabel(){
        incorrect.setVisible(true);
    }
    
     class LoginListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            boolean auth = parentLoginCntl.requestAuthenticate(username.getText(), password.getPassword());
            if(auth){
                System.out.println("authenticated!");
                parentLoginCntl.requestNavCntl();
            }
            else{
                addIncorrectLabel();
            }
        }
     }
     
     class SignUpListener implements ActionListener{
         public void actionPerformed(ActionEvent event){
             parentLoginCntl.requestSignUp();
         }
     }
}
