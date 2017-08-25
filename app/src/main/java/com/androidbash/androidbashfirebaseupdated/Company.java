package com.androidbash.androidbashfirebaseupdated;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Company {
    private String companyId;
    private String companyName;
    private String companyDescription;
    private String userId;

    public Company() {

    }

    public Company(String companyId, String companyName, String companyDescription, String userId) {
        this.companyName = companyName;
        this.companyDescription = companyDescription;
        this.companyId = companyId;
        this.userId = userId;
    }

    public String getCompanyName() {
        return companyName;
    }
    public String getCompanyId() {
        return companyId;
    }
    public String getCompanyDescription() {
        return companyDescription;
    }
    public String getUserId() {
        return userId;
    }

}
