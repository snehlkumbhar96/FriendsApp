package com.example.myapplication;

public class FriendsModel {

    private String contact_id;
    private String first_Name;
    private String last_Name;
    private String mobile_No;
    private String email_Id;



    // Constructor
    public FriendsModel( String contact_id ,String first_Name,String last_Name,String mobile_No,String email_Id) {
        this.contact_id=contact_id;
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.mobile_No = mobile_No;
        this.email_Id = email_Id;

    }

    // Getter and Setter
    public String getFirst_Name() {
        return first_Name;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public String getLast_Name() {
        return last_Name;
    }

    public void setLast_Name(String last_Name) {
        this.last_Name = last_Name;
    }

    public String getMobile_No() {
        return mobile_No;
    }

    public void setMobile_No(String mobile_No) {
        this.mobile_No = mobile_No;
    }

    public String getEmail_Id() {
        return email_Id;
    }

    public void setEmail_Id(String email_Id) {
        this.email_Id = email_Id;
    }

    public String getContact_id() {
        return contact_id;
    }

    public void setContact_id(String contact_id) {
        this.contact_id = contact_id;
    }

}
