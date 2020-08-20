package com.datacenter.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.datacenter.WebApiConstant;
import com.datacenter.dto.CardDto;
import com.datacenter.dto.DeviceDto;
import com.datacenter.dto.Response;
import com.datacenter.service.DataCenterService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @author Kazim Ulusoy
 *
 */
@RestController
@RequestMapping(WebApiConstant.DATA_CENTER_URL + "/racks")
@Tag(name = "racks", description = "The Data Center - Rack API")
@RequiredArgsConstructor
public class RackController {

	private final DataCenterService dataCenterService;

	@Operation(summary = "Get the list of existing racks in a data center")
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> getRacks() {
		return dataCenterService.getRacks();
	}

	@Operation(summary = "Get a rack")
	@RequestMapping(value = "/{rackId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> getRack(@Parameter(description = "id of a rack") @PathVariable String rackId) {
		return dataCenterService.getRack(rackId);
	}

	@Operation(summary = "Add a new node (of any type) into one of the racks")
	@RequestMapping(value = "/{rackId}/units/{unitId}/nodes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> addNode(@Valid @RequestBody DeviceDto deviceDto,
			@Parameter(description = "id of a rack") @PathVariable String rackId,
			@Parameter(description = "id of a unit") @PathVariable String unitId) {
		return dataCenterService.addNode(rackId, unitId, deviceDto);
	}

	@Operation(summary = "Update information on a node (system name / management IP etc.)")
	@RequestMapping(value = "/{rackId}/units/{unitId}/nodes/{nodeId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> updateNode(@RequestBody DeviceDto deviceDto,
			@Parameter(description = "id of a rack") @PathVariable String rackId,
			@Parameter(description = "id of a unit") @PathVariable String unitId,
			@Parameter(description = "id of a node") @PathVariable String nodeId) {
		return dataCenterService.updateNode(rackId, unitId, nodeId, deviceDto);
	}

	@Operation(summary = "Add a card to a node")
	@RequestMapping(value = "/{rackId}/units/{unitId}/nodes/{nodeId}/cards", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> addCard(@RequestBody CardDto cardDto,
			@Parameter(description = "id of a rack") @PathVariable String rackId,
			@Parameter(description = "id of a unit") @PathVariable String unitId,
			@Parameter(description = "id of a node") @PathVariable String nodeId) {
		return dataCenterService.addCard(rackId, unitId, nodeId, cardDto);
	}

	@Operation(summary = "Remove a card from a node")
	@RequestMapping(value = "/{rackId}/units/{unitId}/nodes/{nodeId}/cards/{cardId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> deleteCard(@Parameter(description = "id of a rack") @PathVariable String rackId,
			@Parameter(description = "id of a unit") @PathVariable String unitId,
			@Parameter(description = "id of a node") @PathVariable String nodeId,
			@Parameter(description = "id of a card") @PathVariable String cardId) {

		return dataCenterService.deleteCard(rackId, unitId, nodeId, cardId);
	}

	@Operation(summary = "Fetch all the cabeling scheme (nodes & links) of a rack")
	@RequestMapping(value = "/{rackId}/cabelings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> fetchAllCabeling(
			@Parameter(description = "id of a rack") @PathVariable String rackId) {

		return dataCenterService.fetchAllCabeling(rackId);
	}
}
