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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author Chandler
 */
public class TasksUI extends ParentFrame{
    private TaskCntl parentCntl;
    private JButton backButton,newTaskButton,deleteTaskButton,editButton;
    private JTable theTasksTable;
    private JPanel buttonPanel,tablePanel,searchPanel;
    private JScrollPane theScrollPane;
    private JComboBox theUrgencyBox;
    private JLabel searchLabel;
    private JTextField searchText;
    private TableRowSorter<TableModel> rowSorter;
                    
    
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
        
        deleteTaskButton = new JButton("Delete");
        deleteTaskButton.setFont(lfont);
        deleteTaskButton.addActionListener(new DeleteListener());
        
        editButton = new JButton("View & Edit");
        editButton.setFont(lfont);
        editButton.addActionListener(new EditListener());
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,4,0,0));
        buttonPanel.add(backButton);
        buttonPanel.add(newTaskButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteTaskButton);
        
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
        
        searchLabel = new JLabel("Search Tasks:");
        searchLabel.setFont(lfont);
        
        searchText = new JTextField();
        searchText.setEditable(true);
        searchText.setPreferredSize(new Dimension(350,35));
        
        searchPanel = new JPanel();
        searchPanel.add(searchLabel);
        searchPanel.add(searchText);
        
        rowSorter = new TableRowSorter<>(theTasksTable.getModel());  
        theTasksTable.setRowSorter(rowSorter);
        searchText.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = searchText.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = searchText.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

        });
        
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        this.getContentPane().add(tablePanel,BorderLayout.CENTER);
        this.getContentPane().add(searchPanel,BorderLayout.NORTH);
    }
    
    class BackListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            parentCntl.requestMainMenu();
        }
    }
    
    class NewListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            parentCntl.requestNewTask(-1);
        }
    }
    
    class EditListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            if (theTasksTable.getSelectedRow() == -1) {

                JOptionPane.showMessageDialog(null, "Please select a task!");
            } else {
                int selectedTableRow = theTasksTable.getSelectedRow();
                int selectedModelRow = theTasksTable.convertRowIndexToModel(selectedTableRow);
                parentCntl.requestNewTask(selectedModelRow);
            }
        }
    }

        class DeleteListener implements ActionListener {

            public void actionPerformed(ActionEvent event) {
                int selectedTableRow = theTasksTable.getSelectedRow();
                int selectedModelRow = theTasksTable.convertRowIndexToModel(selectedTableRow);
                //MediaListUI.this.theMediaCntl.getMediaList().getListOfMedia().remove(selectedModelRow);
                parentCntl.getUser().getTasks().remove(selectedModelRow);
                parentCntl.updateUsers();
                parentCntl.getTableModel().fireTableDataChanged();
            }
        }
    }
