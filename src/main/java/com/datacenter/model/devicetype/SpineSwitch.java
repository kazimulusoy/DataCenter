package com.datacenter.model.devicetype;

import com.datacenter.model.components.Device;
import com.datacenter.model.components.DeviceType;
import com.datacenter.model.components.Switch;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class SpineSwitch extends Switch {
	
	public SpineSwitch() {
		this.setType(DeviceType.SPINE_SWITCH);
	}
    
    public void addCabeling(Device device){
        if (device instanceof LeafSwitch) {
            super.addCabeling(device);
        } else {
        	log.info("Device cannot be linked to Spine Switch!");
        }
    }

}
