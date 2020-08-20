package com.datacenter.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.datacenter.WebApiConstant;
import com.datacenter.dto.Response;
import com.datacenter.service.DataCenterService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @author Kazim Ulusoy
 *
 */
@RestController
@RequestMapping(WebApiConstant.DATA_CENTER_URL)
@Tag(name = "datacenter", description = "The Data Center API")
@RequiredArgsConstructor
public class DataCenterController {
	private final DataCenterService dataCenterService;

	@Operation(summary = "Get the data center")
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> getDataCenter() {
		return dataCenterService.getDataCenter();
	}
}
