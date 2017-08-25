package com.androidbash.androidbashfirebaseupdated;

import com.google.firebase.database.IgnoreExtraProperties;
@IgnoreExtraProperties
public class Order {
    private String orderId;
    private String companyId;
    private String serviceId;
    private String startTime;
    private String userId;

    public Order() {

    }

    public Order(String orderId , String companyId, String serviceId, String startTime, String userId) {
        this.orderId = orderId;
        this.companyId = companyId;
        this.serviceId = serviceId;
        this.startTime = startTime;
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCompanyId() {
        return companyId;
    }
    public String getServiceId() {
        return serviceId;
    }
    public String getUserId() {
        return userId;
    }
    public String getStartTime() {
        return startTime;
    }

}
