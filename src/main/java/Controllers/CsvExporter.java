/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Contact;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author cmg5831
 */
public class CsvExporter {

    public void writeToCSV(ArrayList<Contact> theContacts) {
        try {
            PrintWriter pw = new PrintWriter(new File("contacts.csv"));
            StringBuilder sb = new StringBuilder();
            sb.append("Name,Given Name,Additional Name,Family Name,Address,E-mail 1 - Value,Phone 1 - Value");
            sb.append(System.getProperty("line.separator"));
            
            for(int i = 0; i < theContacts.size(); i++){
                Contact tempContact = theContacts.get(i);
                sb.append(tempContact.toString()+",");
                sb.append(tempContact.getFirstName()+",,");
                sb.append(tempContact.getLastName()+",");
                sb.append(tempContact.getAddress()+",,");
                sb.append(tempContact.getPhone());
                sb.append(System.getProperty("line.separator"));
            }

//            sb.append("Chandler Goewert,");
//            sb.append("Chandler,,");
//            sb.append("Goewert,");
//            sb.append("138 S Atherton Street State College,");
//            sb.append("cmgoewert@aol.com,");
//            sb.append("(610) 757-5964");
//            sb.append('\n');

            pw.write(sb.toString());
            pw.close();
            System.out.println("done!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
//        FileOutputStream out = null;
//        File file;
//        String content = "This is the text content";
//
//        try {
//
//            file = new File(System.getProperty("user.home") + "/Desktop/contacts.csv");
//            //file = new File("contacts.csv");
//            out = new FileOutputStream(file);
//
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//
//            // if file doesnt exists, then create it
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//
//            // get the content in bytes
//            byte[] contentInBytes = content.getBytes();
//
//            out.write(contentInBytes);
//            out.flush();
//            out.close();
//
//            System.out.println("Done");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
