package com.duxetech.mycontacts;
/**
 * Created by Karthik Swamy on 12/11/18.
 */

public class Contacts {
    String first_name, last_name, mail, address;
    String mobile;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Contacts(){

    }

    public Contacts(String first_name, String last_name, String mobile, String mail,String address){
        this.first_name = first_name;
        this.last_name = last_name;
        this.mobile = mobile;
        this.mail = mail;
        this.address = address;
    }


    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


}

