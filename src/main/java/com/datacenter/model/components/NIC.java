package com.datacenter.model.components;

public class NIC extends Card{
    private final int totalPorts = 2;
    
    public NIC() {
    	this.setNumberOfPorts(totalPorts);
    }
}
