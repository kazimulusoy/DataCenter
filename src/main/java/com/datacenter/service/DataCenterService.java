package com.datacenter.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.datacenter.dto.CardDto;
import com.datacenter.dto.DeviceDto;
import com.datacenter.dto.Response;
import com.datacenter.dto.ResponseMessage;
import com.datacenter.model.components.Card;
import com.datacenter.model.components.Device;
import com.datacenter.model.components.DeviceType;
import com.datacenter.model.components.NIC;
import com.datacenter.model.components.Rack;
import com.datacenter.model.components.Switch;
import com.datacenter.model.components.Unit;
import com.datacenter.model.devicetype.ComputeNode;
import com.datacenter.model.devicetype.LeafSwitch;
import com.datacenter.model.devicetype.SpineSwitch;
import com.datacenter.model.domain.DataCenter;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * DataCenterService is a intermediary service which is responsible to handle
 * request from Rest Controller.
 * 
 * @author Kazim Ulusoy
 *
 */
@Service
@Slf4j
public class DataCenterService {
	private DataCenter dataCenter;

	@PostConstruct
	public void init() {
		this.createRackWithFullofComponents();
	}

	public ResponseEntity<Response> getDataCenter() {
		Response response = new Response();
		response.setData(this.dataCenter);
		response.setStatus(ResponseMessage.SUCCESS.toString());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	public ResponseEntity<Response> getRacks() {
		Response response = new Response();
		response.setData(this.dataCenter.getRacks());
		response.setStatus(ResponseMessage.SUCCESS.toString());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	public ResponseEntity<Response> getRack(String rackId) {
		log.info("Requested rackId: " + rackId);
		Response response = new Response();
		List<Rack> rackList = this.dataCenter.getRacks();

		if (rackList != null) {
			for (Rack rack : rackList) {
				if (rack.getId().equals(rackId)) {
					response.setData(rack);
					response.setStatus(ResponseMessage.SUCCESS.toString());

					return ResponseEntity.status(HttpStatus.OK).body(response);
				}
			}
		}

		response.setData("There is no such a Rack");
		response.setStatus(ResponseMessage.ERROR.toString());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	

	public ResponseEntity<Response> addNode(String rackId, String unitId, DeviceDto deviceDto) {
		log.info("Requested rackId: " + rackId);
		log.info("Requested unitId: " + unitId);
		log.info("Requested DeviceDto: " + deviceDto.toString());

		Response response = new Response();

		Rack rack = findRack(dataCenter, rackId);
		if(rack == null) {
			response.setData("There is no such a Rack");
			response.setStatus(ResponseMessage.ERROR.toString());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		
		Unit unit = findUnit(rack, unitId);
		if(unit == null) {
			response.setData("There is no such a Unit");
			response.setStatus(ResponseMessage.ERROR.toString());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		
		Device device = createDevice(deviceDto);
		unit.addDevice(device);

		response.setData(rack);
		response.setStatus(ResponseMessage.SUCCESS.toString());

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	public ResponseEntity<Response> updateNode(String rackId, String unitId, String nodeId, DeviceDto deviceDto) {
		log.info("Requested rackId: " + rackId);
		log.info("Requested unitId: " + unitId);
		log.info("Requested deviceId: " + nodeId);
		log.info("Requested DeviceDto: " + deviceDto.toString());

		Response response = new Response();

		Rack rack = findRack(dataCenter, rackId);
		if(rack == null) {
			response.setData("There is no such a Rack");
			response.setStatus(ResponseMessage.ERROR.toString());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		
		Unit unit = findUnit(rack, unitId);
		if(unit == null) {
			response.setData("There is no such a Unit");
			response.setStatus(ResponseMessage.ERROR.toString());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		
		Device device = findDevice(unit, nodeId);
		if(device == null) {
			response.setData("There is no such a Device");
			response.setStatus(ResponseMessage.ERROR.toString());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		
		device = updateDevice(device, deviceDto);

		response.setData(rack);
		response.setStatus(ResponseMessage.SUCCESS.toString());

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	public ResponseEntity<Response> addCard(String rackId, String unitId, String nodeId, CardDto cardDto) {
		log.info("Requested rackId: " + rackId);
		log.info("Requested unitId: " + unitId);
		log.info("Requested nodeId: " + nodeId);
		log.info("Requested DeviceDto: " + cardDto.toString());

		Response response = new Response();

		Rack rack = findRack(dataCenter, rackId);
		if(rack == null) {
			response.setData("There is no such a Rack");
			response.setStatus(ResponseMessage.ERROR.toString());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		
		Unit unit = findUnit(rack, unitId);
		if(unit == null) {
			response.setData("There is no such a Unit");
			response.setStatus(ResponseMessage.ERROR.toString());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		
		Device device = findDevice(unit, nodeId);
		if(device == null) {
			response.setData("There is no such a Device");
			response.setStatus(ResponseMessage.ERROR.toString());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		
		device = addCardToDevice(device, cardDto);

		response.setData(rack);
		response.setStatus(ResponseMessage.SUCCESS.toString());

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	public ResponseEntity<Response> deleteCard(String rackId, String unitId, String nodeId, String cardId) {
		log.info("Requested rackId: " + rackId);
		log.info("Requested unitId: " + unitId);
		log.info("Requested nodeId: " + nodeId);
		log.info("Requested cardId: " + cardId);

		Response response = new Response();
		
		Rack rack = findRack(dataCenter, rackId);
		if(rack == null) {
			response.setData("There is no such a Rack");
			response.setStatus(ResponseMessage.ERROR.toString());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		
		Unit unit = findUnit(rack, unitId);
		if(unit == null) {
			response.setData("There is no such a Unit");
			response.setStatus(ResponseMessage.ERROR.toString());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		
		Device device = findDevice(unit, nodeId);
		if(device == null) {
			response.setData("There is no such a Device");
			response.setStatus(ResponseMessage.ERROR.toString());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		
		deleteCard(device, cardId);
		
		response.setData(rack);
		response.setStatus(ResponseMessage.SUCCESS.toString());

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	public ResponseEntity<Response> fetchAllCabeling(String rackId) {
		log.info("Requested rackId: " + rackId);
		
		Response response = new Response();
		
		Rack rack = findRack(dataCenter, rackId);
		if(rack == null) {
			response.setData("There is no such a Rack");
			response.setStatus(ResponseMessage.ERROR.toString());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		
		List<Device> allCabeling = rack.getUnits().stream()
				.flatMap(x -> x.getDevices().stream())
				.filter(y -> y.getCabeling().size() > 0)
				.collect(Collectors.toList());
		
		response.setData(allCabeling);
		response.setStatus(ResponseMessage.SUCCESS.toString());

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	private Rack findRack(DataCenter dataCenter, String rackId) {
		return dataCenter.getRacks().stream().filter(x -> x.getId().equals(rackId)).findFirst().orElse(null);
	}

	private Unit findUnit(Rack rack, String unitId) {
		return rack.getUnits().stream().filter(x -> x.getId().equals(unitId)).findFirst().orElse(null);
	}
	
	private Device findDevice(Unit unit, String nodeId) {
		return unit.getDevices().stream().filter(x -> x.getId().equals(nodeId)).findFirst().orElse(null);
	}
	
	private void deleteCard(Device device, String cardId) {
		if (device instanceof ComputeNode) {
			ComputeNode computeNode = (ComputeNode) device;
			for (int index = 0; index < computeNode.getSlots().length; index++) {
				Card card = computeNode.getSlots()[index];
				if (card != null && card.getId().equals(cardId)) {
					computeNode.getSlots()[index] = null;
					return;
				}
			}
		}else if (device instanceof Switch) {
			Switch sw = (Switch) device;
			for (int index = 0; index < sw.getSlots().length; index++) {
				Card card = sw.getSlots()[index];
				if (card != null && card.getId().equals(cardId)) {
					sw.getSlots()[index] = null;
					return;
				}
			}
		}
	}
	
	private Device updateDevice(Device device, DeviceDto deviceDto) {
		device.setSystemName(deviceDto.getSystemName());
		device.setManagementIP(deviceDto.getManagementIP());
		
		return device;
	}

	private Device createDevice(DeviceDto deviceDto) {
		Device device;
		switch (deviceDto.getType()) {
		case COMPUTE_NODE:
			device = new ComputeNode();
			break;
		case LEAF_SWITCH:
			device = new LeafSwitch();
			break;
		case SPINE_SWITCH:
			device = new SpineSwitch();
			break;
		default:
			log.info(deviceDto.getType().toString());
			return null;
		}

		device.setSystemName(deviceDto.getSystemName());
		device.setManagementIP(deviceDto.getManagementIP());

		return device;
	}
	
	private Device addCardToDevice(Device device, CardDto cardDto) {
		if (device.getType() == DeviceType.COMPUTE_NODE) {
			NIC nic = new NIC();
			ComputeNode computeNode = (ComputeNode) device;
			computeNode.addNIC(nic);
		} else if (device.getType() == DeviceType.LEAF_SWITCH) {
			Card card = new Card();
			card.setNumberOfPorts(cardDto.getNumberOfPorts());
			LeafSwitch leafSwitch = (LeafSwitch) device;
			leafSwitch.addCard(card);
		} else if (device.getType() == DeviceType.SPINE_SWITCH) {
			Card card = new Card();
			card.setNumberOfPorts(cardDto.getNumberOfPorts());
			SpineSwitch spineSwitch = (SpineSwitch) device;
			spineSwitch.addCard(card);
		}
		
		return device;
	}

	private DataCenter createRackWithFullofComponents() {
		dataCenter = new DataCenter();
		Rack rack1 = new Rack();
		Rack rack2 = new Rack();
		Unit unit1 = new Unit();
		Card card1 = new Card();
		SpineSwitch spineSwitch1 = new SpineSwitch();
		spineSwitch1.addCard(card1);
		spineSwitch1.setSystemName("SpineSwitch1");
		spineSwitch1.setManagementIP("192.168.0.10");
		LeafSwitch leafSwitch1 = new LeafSwitch();
		leafSwitch1.setSystemName("leafSwitch1");
		leafSwitch1.setManagementIP("192.168.0.11");
		ComputeNode computeNode1 = new ComputeNode();
		computeNode1.setSystemName("computeNode1");
		computeNode1.setManagementIP("192.168.0.12");
		computeNode1.addCabeling(leafSwitch1);
		computeNode1.addCabeling(spineSwitch1); // SpineSwitch cannot be added to ComputeNode.
		unit1.addDevice(spineSwitch1);
		unit1.addDevice(leafSwitch1);
		unit1.addDevice(computeNode1);
		rack1.addUnit(unit1);

		dataCenter.addRack(rack1);
		dataCenter.addRack(rack2);
		return dataCenter;
	}
}
