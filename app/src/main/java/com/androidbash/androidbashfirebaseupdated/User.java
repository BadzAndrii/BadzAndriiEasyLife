package com.androidbash.androidbashfirebaseupdated;

/**
 * Created by AndroidBash on 10/07/16
 */
public class User {

    private String id;
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private String company_name;
    private String company_description;


    public User() {
    }

    public User(String id, String name, String phoneNumber, String email, String password, String company_name, String company_description) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.company_name = company_name;
        this.company_description = company_description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompany_name() {
        return company_name;
    }
    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_description() {
        return company_description;
    }
    public void setCompany_description(String company_description) {
        this.company_description = company_description;
    }

}
