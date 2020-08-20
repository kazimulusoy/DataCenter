package com.datacenter.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.datacenter.model.components.DeviceType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDto {
	
	@NotBlank(message = "systemName can not be empty/blank")
    private String systemName;
	
	@NotBlank(message = "managementIP can not be empty/blank")
    private String managementIP;
	
	@NotNull(message = "type can not be null")
    private DeviceType type;
}
