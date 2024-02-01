package com.example.whatever;

public class AssignmentData {
    private String title;
    private String dueDate;
    private String associatedClass;
    public AssignmentData(String title, String dueDate, String associatedClass) {
        this.title = title;
        this.dueDate = dueDate;
        this.associatedClass = associatedClass;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getAssociatedClass() {
        return associatedClass;
    }

    public void setAssociatedClass(String associatedClass) {
        this.associatedClass = associatedClass;
    }

    @Override
    public String toString() {
        return "Name: " + title + "\nDue Date: " + dueDate + "\nAssociated Class: " + associatedClass;
    }
}
