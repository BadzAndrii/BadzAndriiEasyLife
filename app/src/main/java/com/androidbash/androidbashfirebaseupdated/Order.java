package com.androidbash.androidbashfirebaseupdated;

import com.google.firebase.database.IgnoreExtraProperties;
@IgnoreExtraProperties
public class Order {
    private String orderId;
    private String companyId;
    private String companyName;
    private String serviceId;
    private String serviceName;
    private String startTime;
    private String userId;

    public Order() {

    }

    public Order(String orderId , String companyId,String companyName, String serviceId, String serviceName, String startTime, String userId) {
        this.orderId = orderId;
        this.companyId = companyId;
        this.companyName = companyName;
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.startTime = startTime;
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCompanyId() {
        return companyId;
    }
    public String getCompanyName() {
        return companyName;
    }
    public String getServiceId() {
        return serviceId;
    }
    public String getServiceName() {
        return serviceName;
    }
    public String getUserId() {
        return userId;
    }
    public String getStartTime() {
        return startTime;
    }

}
