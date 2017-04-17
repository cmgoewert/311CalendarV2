/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDateTime;

/**
 *
 * @author Chandler
 */
public class Task {
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private String date;
    private Contact collaborator;
    private int urgency;
    
    public Task(String theTitle, String theDescrip, LocalDateTime theDueDate,Contact theCollaborator, int theUrgency){
        title = theTitle;
        description = theDescrip;
        dueDate = theDueDate;
        collaborator = theCollaborator;
        urgency = theUrgency;
    }
    
    public Task(String theTitle, String theDescrip, String theDueDate,Contact theCollaborator, int theUrgency){
        title = theTitle;
        description = theDescrip;
        date = theDueDate;
        collaborator = theCollaborator;
        urgency = theUrgency;
    }
    
    public Task(String theTitle, String theDescrip, LocalDateTime theDueDate, int theUrgency){
        title = theTitle;
        description = theDescrip;
        dueDate = theDueDate;
        collaborator = null;
        urgency = theUrgency;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }
    
    public String getDateString(){
        return date;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public Contact getCollaborator() {
        return collaborator;
    }

    public void setCollaborator(Contact collaborator) {
        this.collaborator = collaborator;
    }

    public int getUrgency() {
        return urgency;
    }

    public void setUrgency(int urgency) {
        this.urgency = urgency;
    }
}
