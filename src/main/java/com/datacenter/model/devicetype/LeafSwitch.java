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
public class LeafSwitch extends Switch {
	
	public LeafSwitch() {
		this.setType(DeviceType.LEAF_SWITCH);
	}

	public void addCabeling(Device device) {
		if (device instanceof ComputeNode || device instanceof LeafSwitch || device instanceof SpineSwitch) {
			super.addCabeling(device);
		} else {
			log.info("Device cannot be linked to Leaf Switch!");
		}
	}
}
