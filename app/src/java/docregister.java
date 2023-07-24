package com.Appointment.doctor;

public class docregister {
    String name,email,license,user,password,mobile,qualification,hospital,speciality,departmentname,address,UpiId,Amount,category;

    public docregister() {
    }

    public docregister(String name, String email, String license, String user, String password, String mobile, String qualification, String hospital, String speciality, String departmentname, String address, String upiId, String amount, String category) {
        this.name = name;
        this.email = email;
        this.license = license;
        this.user = user;
        this.password = password;
        this.mobile = mobile;
        this.qualification = qualification;
        this.hospital = hospital;
        this.speciality = speciality;
        this.departmentname = departmentname;
        this.address = address;
        UpiId = upiId;
        Amount = amount;
        this.category = category;
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

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUpiId() {
        return UpiId;
    }

    public void setUpiId(String upiId) {
        UpiId = upiId;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
