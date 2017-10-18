package com.androidbash.androidbashfirebaseupdated;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class OrdersClient {


    private String companyId;
    private String orderId;
    private String serviceId;
    private String startTime;
    private String userId;



    public OrdersClient(){
        //this constructor is required
    }

    public OrdersClient(String companyId, String orderId, String serviceId, String startTime,String userId) {
        this.companyId = companyId;
        this.orderId = orderId;
        this.serviceId = serviceId;
        this.startTime = startTime;
        this.userId = userId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getOrderId() {
        return orderId;
    }
    public String getServiceId() {
        return serviceId;
    }
    public String getStartTime() {
        return startTime;
    }
    public String getUserId() {
        return userId;
    }
}
