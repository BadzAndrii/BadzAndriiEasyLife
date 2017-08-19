package com.androidbash.androidbashfirebaseupdated;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Belal on 2/26/2017.
 */
@IgnoreExtraProperties
public class Company {
    private String companyId;
    private String companyName;
    private String companyDescription;

    public Company() {

    }

    public Company(String companyId, String companyName, String companyDescription) {
        this.companyName = companyName;
        this.companyDescription = companyDescription;
        this.companyId = companyId;
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


}
