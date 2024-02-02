package com.example.whatever;

public class ExamEntry {
    private String date;
    private String time;
    private String location;

    public ExamEntry(String date, String time, String location) {
        this.date = date;
        this.time = time;
        this.location = location;
    }

    public String toString() {
        return "Date: " + time + "\nTime: " + date + "\nExam Location: " + location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
