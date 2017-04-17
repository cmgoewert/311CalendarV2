/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface;


import Controllers.ContactsCntl;
import Controllers.TaskCntl;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
/**
 *
 * @author Chandler
 */
public class TasksUI extends ParentFrame{
    private TaskCntl parentCntl;
    private JButton backButton,newTaskButton;
    private JTable theTasksTable;
    private JPanel buttonPanel,tablePanel;
    private JScrollPane theScrollPane;
    private JComboBox theUrgencyBox;
                
    
    public TasksUI(TaskCntl theCntl){
        parentCntl = theCntl;
        initComponents();
    }
    
    private void initComponents(){
        Font font = new Font(Font.SERIF,0,16);
        Font lfont = new Font(Font.SANS_SERIF, Font.BOLD,18);
        Font tableFont = new Font(Font.SANS_SERIF,0,18);
        
        backButton = new JButton("Back To Main Menu");
        backButton.setFont(lfont);
        backButton.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(25, 0, 25, 0), new EmptyBorder(0,0,0,0)));
        backButton.addActionListener(new BackListener());
        
        newTaskButton = new JButton("New Task");
        newTaskButton.setFont(lfont);
        newTaskButton.addActionListener(new NewListener());
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,2,0,0));
        buttonPanel.add(backButton);
        buttonPanel.add(newTaskButton);
        
        theTasksTable = new JTable(this.parentCntl.getTableModel());
        theTasksTable.setFont(tableFont);
        theTasksTable.setRowHeight(25);
        theTasksTable.getTableHeader().setFont(lfont);
        
        theUrgencyBox = new JComboBox();
        theUrgencyBox.addItem("Most Urgent");
        theUrgencyBox.addItem("More Urgent");
        theUrgencyBox.addItem("Less Urgent");
        TableColumn sportColumn = theTasksTable.getColumnModel().getColumn(4);
        sportColumn.setCellEditor(new DefaultCellEditor(theUrgencyBox));
        
        theScrollPane = new JScrollPane(theTasksTable);
        theScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        theScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        theScrollPane.setPreferredSize(new Dimension(500, 320));
        theTasksTable.setFillsViewportHeight(true);

        tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(theScrollPane, BorderLayout.CENTER);
        
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        this.getContentPane().add(tablePanel,BorderLayout.CENTER);
    }
    
    class BackListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            parentCntl.requestMainMenu();
        }
    }
    
    class NewListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            parentCntl.requestNewTask();
        }
    }
}
