/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.TableView;
import javax.swing.text.TableView.TableCell;

/**
 *
 * @author cmg5831
 */
public class TasksTableModel extends AbstractTableModel {

    private static String[] columnNames = {"Title", "Description", "Due Date", "Collaborators", "Urgency"};
    private ArrayList<Task> taskTableData;

    public TasksTableModel(ArrayList<Task> theTaskTableData) {
        this.taskTableData = theTaskTableData;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return taskTableData.size();

    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        Object objectToReturn = new Object();
        switch (col) {
            case 0:
                objectToReturn = taskTableData.get(row).getTitle();
                break;
            case 1:
                objectToReturn = taskTableData.get(row).getDescription();
                break;
            case 2:
                try{
                    objectToReturn = taskTableData.get(row).getDueDate().toString();
                }
                catch(Exception e){
                    objectToReturn = taskTableData.get(row).getDateString();
                }
                
                break;
            case 3:
                try{
                    objectToReturn = taskTableData.get(row).getCollaborator().toString();
                }
                catch(Exception e){
                    objectToReturn = "No collaborators";
                }
                break;
            case 4:
                objectToReturn = String.valueOf(taskTableData.get(row).getUrgency());
                break;
        }
        return objectToReturn;
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}
