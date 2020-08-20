package com.datacenter.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * There will be some standard JSON response formats such as JSON API, JSend, HAL, OData JSON Protocol
 * In the class the response format is accepted as JSend, it is simple
 * There are three type of status messages success, fail, error
 *
 * @author Kazim Ulusoy
 *
 */
@Getter
@Setter
public class Response {
	private String status;
	private Object data;
}
