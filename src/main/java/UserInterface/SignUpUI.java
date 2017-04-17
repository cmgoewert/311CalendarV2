/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface;

import Controllers.LoginCntl;
import Model.User;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Chandler
 */
public class SignUpUI extends ParentFrame{
    private LoginCntl parentLoginCntl;
    private JLabel usernameLabel,passwordLabel,confirmPassLabel,doesntMatch,instructions,nameLabel;
    private JTextField username,name;
    private JPasswordField password, confirmPass;
    private JPanel mainPanel, navPanel;
    private JButton signup, cancel;
    
    public SignUpUI(LoginCntl theLoginCntl){
        parentLoginCntl = theLoginCntl;
        initComponents();
    }
    
    public void initComponents(){
        this.setSize(900,600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        Font lfont = new Font(Font.SANS_SERIF, Font.BOLD,18);
        Font ifont = new Font(Font.SERIF, Font.ITALIC,16);
        Font font = new Font(Font.SERIF,0,16);
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        confirmPassLabel = new JLabel("Confirm Password:");
        nameLabel = new JLabel("First and Last Name:");
        nameLabel.setFont(lfont);
        usernameLabel.setFont(lfont);
        passwordLabel.setFont(lfont);
        confirmPassLabel.setFont(lfont);
        
        username = new JTextField();
        username.setEditable(true);
        username.setFont(font);
        name = new JTextField();
        name.setEditable(true);
        name.setFont(font);
        password = new JPasswordField();
        password.setFont(font);
        password.setEchoChar('\u2022');
        confirmPass = new JPasswordField();
        confirmPass.setFont(font);
        confirmPass.setEchoChar('\u2022');
        
        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(50, 150, 0, 150), new EmptyBorder(0,0,0,0)));
        mainPanel.setLayout(new GridLayout(6,2,10,3));
        usernameLabel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(150, 0, 150, 0), new EmptyBorder(0,0,0,0)));
        usernameLabel.setHorizontalAlignment(JLabel.RIGHT);
        mainPanel.add(usernameLabel);
        mainPanel.add(username);
        nameLabel.setHorizontalAlignment(JLabel.RIGHT);
        mainPanel.add(nameLabel);
        mainPanel.add(name);
        passwordLabel.setHorizontalAlignment(JLabel.RIGHT);
        mainPanel.add(passwordLabel);
        mainPanel.add(password);
        confirmPassLabel.setHorizontalAlignment(JLabel.RIGHT);
        mainPanel.add(confirmPassLabel);
        mainPanel.add(confirmPass);
        
        instructions = new JLabel("Enter the following information and press sign up to create an account.");
        instructions.setFont(ifont);
        instructions.setHorizontalAlignment(JLabel.CENTER);
        
        doesntMatch = new JLabel("Passwords don't match!");
        mainPanel.add(doesntMatch);
        doesntMatch.setFont(font);
        doesntMatch.setVisible(false);

        signup = new JButton("Sign Up");
        signup.setFont(lfont);
        signup.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(25, 0, 25, 0), new EmptyBorder(0,0,0,0)));
        signup.addActionListener(new SignUpUI.SignUpListener());
        
        cancel = new JButton("Cancel");
        cancel.setFont(lfont);
        cancel.addActionListener(new SignUpUI.CancelListener());
        
        navPanel = new JPanel();
        navPanel.setLayout(new GridLayout(1,2,0,0));
        navPanel.add(cancel);
        navPanel.add(signup);
       
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(instructions, BorderLayout.NORTH);
        this.getContentPane().add(navPanel, BorderLayout.SOUTH);
        this.getContentPane().add(mainPanel, BorderLayout.CENTER);
        this.getRootPane().setDefaultButton(signup);
    }
    
    private void setInvisible(){
        this.setVisible(false);
    }
    
    class SignUpListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            if(password.getText().equals(confirmPass.getText())){
                //System.out.println("equals");
                parentLoginCntl.addNewUser(new User(username.getText(),password.getPassword(),name.getText()));
                setInvisible();
            }
            else
                doesntMatch.setVisible(true);
        }
    }
    
    class CancelListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            setInvisible();
        }
    }
}
