/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface;

import Controllers.CalendarCntl;
import Model.Task;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cmgoe
 */
public class CalendarUI extends ParentFrame{
    private CalendarCntl parentCntl;
    private JButton backButton;
    private JPanel buttonPanel;
    private JLabel monthLabel, yearLabel;
    private JButton nextButton, prevButton;
    private JTable calendarTable;
    private JComboBox yearBox;
    private Container pane;
    private DefaultTableModel calendarModel;
    private JScrollPane calendarPane;
    private JPanel calendarPanel;
    private int year,month,day,currYear,currMonth;
    private int[][] taskInfo;
    
    public CalendarUI(CalendarCntl theCntl){
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
        
        monthLabel = new JLabel("January");
        yearLabel = new JLabel("Change year:");
        yearBox = new JComboBox();
        prevButton = new JButton("<<");
        nextButton = new JButton(">>");
        calendarModel = new DefaultTableModel(){
            public boolean isCellEditable(int rowIndex, int mColIndex){
                return false;
            }
        };
        calendarTable = new JTable(calendarModel);
        calendarPane = new JScrollPane(calendarTable);
        calendarPanel = new JPanel();
        calendarPanel.setLayout(new BorderLayout());
        
        calendarPanel.setBorder(BorderFactory.createTitledBorder("Calendar"));
        
        prevButton.addActionListener(new NextListener());
        nextButton.addActionListener(new PrevListener());
        yearBox.addActionListener(new YearListener());
        
        JPanel topPanel = new JPanel();
        topPanel.add(prevButton);
        topPanel.add(monthLabel);
        topPanel.add(nextButton);
        
        JPanel buttomPanel = new JPanel();
        buttomPanel.add(yearLabel);
        buttomPanel.add(yearBox);
        
        calendarPanel.add(topPanel,BorderLayout.NORTH);
        calendarPanel.add(buttomPanel,BorderLayout.SOUTH);
        calendarPanel.add(calendarPane,BorderLayout.CENTER);
       
        
        //calendarPanel.setBounds(0, 0, 320, 335);
//        monthLabel.setBounds(160-monthLabel.getPreferredSize().width/2, 25, 100, 25);
//        yearLabel.setBounds(10, 305, 80, 20);
//        yearBox.setBounds(230, 305, 80, 20);
//        prevButton.setBounds(10, 25, 50, 25);
//        nextButton.setBounds(260, 25, 50, 25);
//        calendarPane.setBounds(10, 50, 500, 400);
        
        GregorianCalendar cal = new GregorianCalendar();
        day = cal.get(GregorianCalendar.DAY_OF_MONTH);
        month = cal.get(GregorianCalendar.MONTH);
        year = cal.get(GregorianCalendar.YEAR);
        
        currMonth = month;
        currYear = year;
        
        String[] days = {"Sun","Mon","Tues","Wed","Thu","Fri","Sat"};
        for(int i = 0; i<7; i++){
            calendarModel.addColumn(days[i]);
        }
        calendarTable.getParent().setBackground(calendarTable.getBackground());
        calendarTable.setColumnSelectionAllowed(true);
        calendarTable.setRowSelectionAllowed(true);
        calendarTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        calendarTable.setRowHeight(74);
        calendarModel.setColumnCount(7);
        calendarModel.setRowCount(6);
        
        for(int i = year-50; i < year+100; i++){
            yearBox.addItem(String.valueOf(i));
        }
        
        yearBox.setSelectedItem("2017");
        
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                refresh(month,year);
//            }
//        });

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 0, 0));
        buttonPanel.add(backButton);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        this.getContentPane().add(calendarPanel,BorderLayout.CENTER);
        
        refresh(month,year);
        //System.out.println("called from initconmponents");

    }
    
    public void refresh(int rmonth, int ryear){
        System.out.println("Calendar refreshed");
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int numDays,firstDayOfMonth;
        nextButton.setEnabled(true);
        prevButton.setEnabled(true);
        
        if(rmonth == 0 && ryear <= year-10){
            prevButton.setEnabled(false);
        }
        if(rmonth == 11 && ryear >= year+50){
            nextButton.setEnabled(false);
        }
        
        monthLabel.setText(months[rmonth]);
        yearBox.setSelectedItem(String.valueOf(year));
        
        for(int i = 0; i<6; i++){
            for(int j = 0; j < 7; j++){
                calendarModel.setValueAt(null, i, j);
            }
        }
        
        GregorianCalendar cal = new GregorianCalendar(year, month,1);
        numDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        firstDayOfMonth = cal.get(GregorianCalendar.DAY_OF_WEEK);
        taskInfo = parentCntl.requestTasks();
        for(int i = 1; i<=numDays; i++){
            int row = new Integer((i+firstDayOfMonth-2)/7);
            int column = (i+firstDayOfMonth-2)%7;
            calendarModel.setValueAt(i,row,column);
//            ArrayList<Task> theTasks = parentCntl.requestTasks();
            
        }
        
        calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0),new CalendarRenderer());
    }
    
    public int[][] getTasks(){
        return taskInfo;
    }
    
    public ArrayList<Task> getTaskList(){
        return parentCntl.getTaskList();
    }
    
    class CalendarRenderer extends DefaultTableCellRenderer{
        public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            JPanel cellPanel = new JPanel();
            cellPanel.setLayout(new BorderLayout());
            JLabel dateValue = new JLabel("date");
            try{
                dateValue.setText(value.toString());
            }
            catch(Exception e){
               dateValue.setText("");
            }
            
            dateValue.setFont(new Font(Font.SANS_SERIF, Font.BOLD,18));
            cellPanel.add(dateValue,BorderLayout.WEST);
            
            if (column == 0 || column == 6){ 
                cellPanel.setBackground(new Color(255, 220, 220));
            }
            else{ 
                cellPanel.setBackground(new Color(255, 255, 255));
            }
            if (value != null){
                if (Integer.parseInt(value.toString()) == day && currMonth == month && currYear == year){ //Today
                    cellPanel.setBackground(new Color(220, 220, 255));
                }
                int[][] theTasks = getTasks();
                ArrayList<Task> taskList = getTaskList();
                for(int i = 0; i < theTasks.length; i++){
//                    System.out.println(theTasks[i][0]+" Day:"+theTasks[i][1]);
//                    System.out.println(value.toString()+ " Here is the day value");
//                    System.out.println(currMonth + "this is the current month, shold equal first thing above");
                    
                    
                    if(Integer.parseInt(value.toString())==theTasks[i][1] && month == theTasks[i][0]){
                        Task currTask = taskList.get(i);
                        switch(currTask.getUrgency()){
                            case 0:
                                cellPanel.setBackground(new Color(255,0,0));
                                break;
                            case 1:
                                cellPanel.setBackground(new Color(255,255,0));
                                break;
                            case 2:
                                cellPanel.setBackground(new Color(0,255,0));
                                break;
                        }
                        JLabel title = new JLabel(currTask.getTitle());
                        title.setFont(new Font(Font.SANS_SERIF, Font.ITALIC,10));
                        title.setBorder(new EmptyBorder(0,5,0,0));
                        cellPanel.add(title,BorderLayout.CENTER);
                    }
                }
                
            }
            
            
  
            cellPanel.setBorder(new LineBorder(Color.black));
            cellPanel.setForeground(Color.black);
            return cellPanel;
        }
    }
    
    private void setInvisible(){
        this.setVisible(false);
    }

    class BackListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            parentCntl.requestMenuCntl();
            setInvisible();
        }
    }
    
    class NextListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            if (month == 0) {
                month = 11;
                year -= 1;
            } else {
                month -= 1;
            }
//            SwingUtilities.invokeLater(new Runnable() {
//                public void run() {
//                    refresh(month, year);
//                    //System.out.println("called from nextListener");
//                }
//            });
            refresh(month, year);
        }
    }
    
    class PrevListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            if (month == 11){ 
                month = 0;
                year += 1;
            }
            else{ 
                month += 1;
            }
//            SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                refresh(month,year);
//                //System.out.println("called from prevlistener");
//            }
//        });
refresh(month,year);
        }
    }
    
    class YearListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            if (yearBox.getSelectedItem() != null) {
                String yearString = yearBox.getSelectedItem().toString();
                year = Integer.parseInt(yearString);
                refresh(month, year);
            }
        }
    }
}
