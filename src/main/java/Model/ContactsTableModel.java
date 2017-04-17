/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Chandler
 */
public class ContactsTableModel extends AbstractTableModel{
       
    private static String[] columnNames={"First Name","Last Name","Address","Phone Number","Relationship"};
    private ArrayList<Contact> contactTableData;
    
    public ContactsTableModel(ArrayList<Contact> theMediaTableData){
        this.contactTableData = theMediaTableData;
    }
    
    public int getColumnCount() {
        return columnNames.length;
    }
    
    public int getRowCount(){
       return contactTableData.size();

    }
    
    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    public Object getValueAt(int row, int col){
        Object objectToReturn = new Object();
        switch(col){
            case 0: objectToReturn = contactTableData.get(row).getFirstName();break;
            case 1: objectToReturn = contactTableData.get(row).getLastName();break;
            case 2: objectToReturn = contactTableData.get(row).getAddress();break;
            case 3: objectToReturn = contactTableData.get(row).getPhone();break;
            case 4: objectToReturn = contactTableData.get(row).getRelationship();break;
        }
        return objectToReturn;
    }
    
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}
