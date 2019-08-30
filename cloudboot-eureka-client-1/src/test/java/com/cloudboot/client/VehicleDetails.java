package com.cloudboot.client;

/**
 * @program: cloudboot
 * @description: ddd
 * @author: zhangzhihong@joyschool.cn
 * @create: 2019-08-30 13:51
 **/
public class VehicleDetails {

    private String make;
    private String model;

    public VehicleDetails(String make, String model) {
        this.make = make;
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
