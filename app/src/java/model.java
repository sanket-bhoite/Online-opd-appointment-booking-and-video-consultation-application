package com.Appointment.doctor;

public class model {
    String user,hospital,qualification,name,speciality;
    public model() {

    }

    public model(String user, String hospital, String qualification, String name, String speciality) {
        this.user = user;
        this.hospital = hospital;
        this.qualification = qualification;
        this.name = name;
        this.speciality = speciality;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
