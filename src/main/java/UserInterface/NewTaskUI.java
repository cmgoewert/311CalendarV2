/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface;

import Controllers.TaskCntl;
import Model.Contact;
import Model.Task;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxEditor;

/**
 *
 * @author cmg5831
 */
public class NewTaskUI extends ParentFrame{
    private TaskCntl parentCntl;
    private JLabel title, description, dueDate, collaborators, urgency, instructions;
    private JTextField titleField, descField;
    private SpinnerDateModel dateField;
    private JSpinner newSpin;
    private JComboBox collabBox,urgencyField;
    private JPanel mainPanel, navPanel, labelPanel;
    private JButton save, cancel;
    private JPanel[] labelCells;
    
    public NewTaskUI(TaskCntl theCntl){
        parentCntl = theCntl;
        initComponents();
    }
    
    private void initComponents(){
        this.setSize(900,600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        Font lfont = new Font(Font.SANS_SERIF, Font.BOLD,18);
        Font ifont = new Font(Font.SERIF, Font.ITALIC,16);
        Font font = new Font(Font.SERIF,0,16);
        
        title = new JLabel("Task Title:");
        title.setFont(lfont);
        title.setBorder(new EmptyBorder(0, 50, 0, 5));
        title.setHorizontalAlignment(JLabel.RIGHT);
        
        description = new JLabel("Description:");
        description.setFont(lfont);
        description.setHorizontalAlignment(JLabel.RIGHT);
        description.setBorder(new EmptyBorder(0, 50, 0, 5));
        
        dueDate = new JLabel("Due Date:");
        dueDate.setFont(lfont);
        dueDate.setHorizontalAlignment(JLabel.RIGHT);
        dueDate.setBorder(new EmptyBorder(0, 50, 0, 5));
        
        collaborators = new JLabel("Collaborators:");
        collaborators.setFont(lfont);
        collaborators.setHorizontalAlignment(JLabel.RIGHT);
        collaborators.setBorder(new EmptyBorder(0, 50, 0, 5));
        
        urgency = new JLabel("Level of Urgency:");
        urgency.setFont(lfont);
        urgency.setHorizontalAlignment(JLabel.RIGHT);
        urgency.setBorder(new EmptyBorder(0, 50, 0, 5));
        
        titleField = new JTextField();
        titleField.setFont(font);
        titleField.setEditable(true);
        titleField.setPreferredSize(new Dimension(512,45));
        
        descField = new JTextField();
        descField.setFont(font);
        descField.setEditable(true);
        descField.setPreferredSize(new Dimension(512,45));
        
        dateField = new SpinnerDateModel();
        
        newSpin = new JSpinner(dateField);
        newSpin.setEditor(new JSpinner.DateEditor(newSpin,"MM/dd/yyyy hh:mm a"));
        newSpin.setFont(font);
        newSpin.setPreferredSize(new Dimension(512,45));
        
        collabBox = new JComboBox();
        collabBox.setFont(font);
        collabBox.setEditable(true);
        ArrayList<Contact> contacts = parentCntl.requestContacts();
        for(int i = 0; i < contacts.size(); i++){
            collabBox.addItem(contacts.get(i).toString());
        }
        collabBox.setPreferredSize(new Dimension(512,45));
        
        urgencyField = new JComboBox();
        urgencyField.setFont(font);
        urgencyField.setRenderer(new ComboBoxRenderer());
        urgencyField.setEditable(true);
        //urgencyField.setEditor(new MyComboBoxEditor());
        urgencyField.addItem("Most Urgent");
        urgencyField.addItem("More Urgent");
        urgencyField.addItem("Less Urgent");
        urgencyField.setPreferredSize(new Dimension(512,45));
        
        labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(5,1,0,0));
        labelPanel.add(title);
        labelPanel.add(description);
        labelPanel.add(dueDate);
        labelPanel.add(collaborators);
        labelPanel.add(urgency);
        
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5,1,0,0));
        labelCells = new JPanel[5];
        for (int i = 0; i < 5; i++) {
            JPanel nextPanel = new JPanel();
            labelCells[i] = nextPanel;
            mainPanel.add(nextPanel);
            this.addFieldsPadding(nextPanel);
        }
        labelCells[0].add(titleField);
        labelCells[1].add(descField);
        labelCells[2].add(newSpin);
        labelCells[3].add(collabBox);
        labelCells[4].add(urgencyField);
 
        
        instructions = new JLabel("Enter the following information and press save to create a new task.");
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
        navPanel.setLayout(new GridLayout(1, 2, 0, 0));
        navPanel.add(cancel);
        navPanel.add(save);
        navPanel.setBorder(new EmptyBorder(20, 10, 10, 10));

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(instructions, BorderLayout.NORTH);
        this.getContentPane().add(navPanel, BorderLayout.SOUTH);
        this.getContentPane().add(labelPanel, BorderLayout.WEST);
        this.getContentPane().add(mainPanel, BorderLayout.CENTER);
        this.getRootPane().setDefaultButton(save);
    }

    private void addFieldsPadding(JComponent cmp) {
        cmp.setBorder(new EmptyBorder(20, 0, 20, 20));
    }

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {
        ArrayList<Contact> contactList = parentCntl.requestContacts();
        Contact newContact = null;
        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).toString().equals(collabBox.getSelectedItem().toString())) {
                newContact = contactList.get(i);
            }
        }

        Task taskToAdd = new Task(titleField.getText(), descField.getText(), newSpin.getValue().toString(), newContact, urgencyField.getSelectedIndex());
        parentCntl.addNewTask(taskToAdd);
        this.parentCntl.getTableModel().fireTableDataChanged();
    }

    class CancelListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            setInvisible();
        }
    }

    private void setInvisible() {
        this.setVisible(false);
    }

    class ComboBoxRenderer extends JLabel implements ListCellRenderer {

        public ComboBoxRenderer() {
            setOpaque(true);
            setForeground(Color.BLACK);
            setFont(new Font(Font.SERIF, 0, 16));
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value,
                int index, boolean isSelected, boolean cellHasFocus) {
            if (index == 0) {
                this.setBackground(Color.RED);
            } else if (index == 1) {
                this.setBackground(Color.YELLOW);
            } else if (index == 2) {
                this.setBackground(Color.GREEN);
            }
            this.setBorder(new EmptyBorder(5, 20, 5, 0));
            setText(value.toString());
            return this;
        }
    }

    class MyComboBoxEditor extends BasicComboBoxEditor {

        private JLabel label = new JLabel();
        private JPanel panel = new JPanel();
        private Object selectedItem;

        public MyComboBoxEditor() {

            label.setOpaque(false);
            label.setFont(new Font("Arial", Font.BOLD, 14));
            label.setForeground(Color.BLACK);

            panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 2));
            panel.add(label);
            panel.setBackground(Color.GREEN);
        }

        public Component getEditorComponent() {
            return this.panel;
        }

        public Object getItem() {
            return "[" + this.selectedItem.toString() + "]";
        }

        public void setItem(Object item) {
            this.selectedItem = item;
            label.setText(item.toString());
        }

    }

}
