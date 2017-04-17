/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface;

import Controllers.ContactsCntl;
import Model.Contact;
import Model.User;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
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
public class NewContactUI extends ParentFrame{
    private ContactsCntl parentCntl;
    private JLabel firstName,lastName,address,phoneNumber,relationship,instructions;
    private JTextField firstNameField,lastNameField,addressField,phoneField,relationField;
    private JPanel mainPanel, navPanel;
    private JButton save,cancel;
    private JPanel[] labelCells;
    private JPanel labelPanel;
    
    public NewContactUI(ContactsCntl theCntl){
        parentCntl = theCntl;
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
        firstName = new JLabel("First Name:");
        lastName = new JLabel("Last Name:");
        address = new JLabel("Address:");
        phoneNumber = new JLabel("Phone Number:");
        relationship = new JLabel("Relationship:");
        firstName.setFont(lfont);
        lastName.setFont(lfont);
        address.setFont(lfont);
        phoneNumber.setFont(lfont);
        relationship.setFont(lfont);
        firstName.setHorizontalAlignment(JLabel.RIGHT);
        lastName.setHorizontalAlignment(JLabel.RIGHT);
        address.setHorizontalAlignment(JLabel.RIGHT);
        phoneNumber.setHorizontalAlignment(JLabel.RIGHT);
        relationship.setHorizontalAlignment(JLabel.RIGHT);
        firstName.setBorder(new EmptyBorder(0, 50, 0, 5));
        lastName.setBorder(new EmptyBorder(0, 50, 0, 5));
        address.setBorder(new EmptyBorder(0, 50, 0, 5));
        phoneNumber.setBorder(new EmptyBorder(0, 50, 0, 5));
        relationship.setBorder(new EmptyBorder(0, 50, 0, 5));
        
        firstNameField = new JTextField();
        firstNameField.setEditable(true);
        firstNameField.setFont(font);
        firstNameField.setPreferredSize(new Dimension(512,45));
        lastNameField = new JTextField();
        lastNameField.setEditable(true);
        lastNameField.setFont(font);
        lastNameField.setPreferredSize(new Dimension(512,45));
        addressField = new JTextField();
        addressField.setFont(font);
        addressField.setEditable(true);
        addressField.setPreferredSize(new Dimension(512,45));
        phoneField = new JTextField();
        phoneField.setFont(font);
        phoneField.setEditable(true);
        phoneField.setPreferredSize(new Dimension(512,45));
        relationField = new JTextField();
        relationField.setFont(font);
        relationField.setEditable(true);
        relationField.setPreferredSize(new Dimension(512,45));
        
        labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(5,1,0,0));
        labelPanel.add(firstName);
        labelPanel.add(lastName);
        labelPanel.add(address);
        labelPanel.add(phoneNumber);
        labelPanel.add(relationship);
        
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5,1,0,0));
        labelCells = new JPanel[5];
        for (int i = 0; i < 5; i++) {
            JPanel nextPanel = new JPanel();
            labelCells[i] = nextPanel;
            mainPanel.add(nextPanel);
            this.addFieldsPadding(nextPanel);
        }
        labelCells[0].add(firstNameField);
        labelCells[1].add(lastNameField);
        labelCells[2].add(addressField);
        labelCells[3].add(phoneField);
        labelCells[4].add(relationField);
        
        instructions = new JLabel("Enter the following information and press save to create a new contact.");
        instructions.setFont(ifont);
        instructions.setHorizontalAlignment(JLabel.CENTER);
        instructions.setBorder(new EmptyBorder(10, 0, 20, 0));

        save = new JButton("Save");
        save.setFont(lfont);
        save.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(25, 0, 25, 0), new EmptyBorder(0,0,0,0)));
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        
        cancel = new JButton("Cancel");
        cancel.setFont(lfont);
        cancel.addActionListener(new CancelListener());
        
        navPanel = new JPanel();
        navPanel.setLayout(new GridLayout(1,2,0,0));
        navPanel.add(cancel);
        navPanel.add(save);
       
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(instructions, BorderLayout.NORTH);
        this.getContentPane().add(navPanel, BorderLayout.SOUTH);
        this.getContentPane().add(mainPanel, BorderLayout.CENTER);
        this.getContentPane().add(labelPanel,BorderLayout.WEST);
        this.getRootPane().setDefaultButton(save);
    }
    
    private void setInvisible(){
        this.setVisible(false);
        
    }
    
    private void addFieldsPadding(JComponent cmp) {
        cmp.setBorder(new EmptyBorder(20, 0, 20, 20));
    }
    
     private void saveButtonActionPerformed(java.awt.event.ActionEvent evt){ 
        parentCntl.addNewContact(new Contact(firstNameField.getText(),lastNameField.getText(),addressField.getText(),phoneField.getText(),relationField.getText()));
        this.parentCntl.getTableModel().fireTableDataChanged();
    }
    
    class CancelListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            setInvisible();
        }
    }
}
