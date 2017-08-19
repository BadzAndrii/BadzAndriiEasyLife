package com.androidbash.androidbashfirebaseupdated;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Belal on 2/26/2017.
 */
@IgnoreExtraProperties
public class Service {
    private String serviceId;
    private String serviceName;

    public Service(){
        //this constructor is required
    }

    public Service(String serviceId, String serviceName) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
    }

    public String getServiceId() {
        return serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

}
