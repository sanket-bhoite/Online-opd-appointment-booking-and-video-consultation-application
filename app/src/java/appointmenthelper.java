package com.Appointment.doctor;

public class appointmenthelper {
    String username,fn,e,pn,st,di,ta,pi,date,time,category,name,email,mobile;

    public appointmenthelper()
    {
    }

    public appointmenthelper(String fn,String username, String date, String time, String category, String name, String email, String mobile) {
        this.fn=fn;
        this.username = username;
        this.date = date;
        this.time = time;
        this.category = category;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public appointmenthelper(String username) {
        this.username = username;
    }

    public appointmenthelper(String username, String fn, String e, String pn, String st, String di,String date, String time, String category) {
        this.username = username;
        this.fn = fn;
        this.e = e;
        this.pn = pn;
        this.st = st;
        this.di = di;
        this.ta = ta;
        this.pi = pi;
        this.date = date;
        this.time = time;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getDi() {
        return di;
    }

    public void setDi(String di) {
        this.di = di;
    }

    public String getTa() {
        return ta;
    }

    public void setTa(String ta) {
        this.ta = ta;
    }

    public String getPi() {
        return pi;
    }

    public void setPi(String pi) {
        this.pi = pi;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
