package com.datacenter.model.components;

import java.util.ArrayList;
import java.util.List;

import com.datacenter.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Device {
    private String id;
    private DeviceType type;
    private String systemName;
    private String managementIP;
    private List<Device> cabeling;
    
    public Device() {
    	this.id = UUID.unique();
    	this.cabeling = new ArrayList<Device>();
    }
    
    public void addCabeling(Device device) {
    	this.cabeling.add(device);
    }
}
