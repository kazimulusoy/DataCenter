package com.datacenter.model.devicetype;

import com.datacenter.model.components.Device;
import com.datacenter.model.components.DeviceType;
import com.datacenter.model.components.NIC;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class ComputeNode extends Device {
	private NIC[] slots;
	private final int maxNICCount = 4;
	private int NICCounter = 0;
	private int portCounter = 0;

	public ComputeNode() {
		this.slots = new NIC[this.maxNICCount];
		this.setType(DeviceType.COMPUTE_NODE);
	}

	public void addNIC(NIC nic) {
		if (this.NICCounter < this.maxNICCount) {
			this.slots[this.NICCounter] = nic;
			this.NICCounter = this.NICCounter + 1;
			this.portCounter = this.portCounter + 2;
		} else {
			log.info("Reached to max NIC number");
		}
	}

	public void removeNIC(NIC nic) {
		if (this.slots.length != 0) {
			for (int index = 0; index < this.maxNICCount; index++) {
				if (this.slots[index] != null && this.slots[index].equals(nic)) {
					this.slots[index] = null;
					this.NICCounter = this.NICCounter - 1;
					this.portCounter = this.portCounter - 2;
				}
			}
		}
	}

	public void addCabeling(Device device) {
		if (device instanceof LeafSwitch) {
			super.addCabeling(device);
		} else {
			log.info("Device cannot be linked to Compute Node!");
		}
	}
}