package com.Appointment.doctor;

public class model1 {
    String fn,date,time,e,category;

    public model1() {
    }

    public model1(String fn, String date, String time, String e, String category) {
        this.fn = fn;
        this.date = date;
        this.time = time;
        this.e = e;
        this.category = category;
    }

    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}


