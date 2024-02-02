package com.example.whatever;

public class ClassEntry {
    private String name;
    private String time;
    private String instructor;
    private String location;

    public ClassEntry(String name, String time, String instructor, String location) {
        this.name = name;
        this.time = time;
        this.instructor = instructor;
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Course: " + name + "\nTime: " + time + "\nInstructor: " + instructor + "\nLocation: " + location;
    }
}
