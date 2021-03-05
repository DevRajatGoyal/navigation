package com.iinerds.navigation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iinerds.navigation.dto.ArrayInitDto;
import com.iinerds.navigation.dto.QueryDto;
import com.iinerds.navigation.service.MovementService;

@RestController
public class MovementController {

	@Autowired
	MovementService movementService;

	/**
	 * @param arrayInitDto
	 * @return
	 */
	@PostMapping("/initiliazeArray")
	public ResponseEntity<String> initializeArray(@RequestBody ArrayInitDto arrayInitDto) {
		return movementService.initializeArray(arrayInitDto);
	}
	
	/**
	 * @param queryDto
	 * @return
	 */
	@PostMapping("/getMemoryStatus")
	public ResponseEntity<String> getMemoryStatus(@RequestBody QueryDto queryDto){
		return movementService.getMemoryStatus(queryDto);
	}
}
