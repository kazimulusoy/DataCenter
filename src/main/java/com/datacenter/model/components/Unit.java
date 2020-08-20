package com.datacenter.model.components;

import java.util.ArrayList;
import java.util.List;

import com.datacenter.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Unit {
    private String id;
    private List<Device> devices;

    public Unit() {
        this.id = UUID.unique();
        this.devices = new ArrayList<>();
    }

    public void addDevice(Device device){
        if (device != null) {
            this.devices.add(device);
        }
    }
}
