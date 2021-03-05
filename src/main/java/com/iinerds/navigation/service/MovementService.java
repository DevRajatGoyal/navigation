package com.iinerds.navigation.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.iinerds.navigation.constants.MovementConstants;
import com.iinerds.navigation.dao.MovementDao;
import com.iinerds.navigation.dto.ArrayInitDto;
import com.iinerds.navigation.dto.QueryDto;

@Service
public class MovementService {

	public ResponseEntity<String> initializeArray(ArrayInitDto arrayInitDto) {
		MovementDao.array = arrayInitDto.getValues();
		MovementDao.size = Integer.parseInt(arrayInitDto.getSize());
		return ResponseEntity.ok(MovementConstants.INITIALIZED);
	}

	public ResponseEntity<String> getMemoryStatus(QueryDto queryDto) {
		int row = (int) (queryDto.getHeaderLocation().toCharArray()[0]) - 65;
		int col = Integer.parseInt(String.valueOf(queryDto.getHeaderLocation().toCharArray()[1])) - 1;
		char[] cmd = queryDto.getHeaderMovement().toCharArray();
		int size = MovementDao.size;
		for (int i = 0; i < cmd.length; i = i + 2) {
			int steps = Integer.parseInt(String.valueOf(cmd[i]));
			if (cmd[i + 1] == 'R') {
				if (col + steps < size) {
					col = col + steps;
				} else 
					return new ResponseEntity<>(MovementConstants.ERROR, HttpStatus.BAD_REQUEST);
			} else if (cmd[i + 1] == 'L') {
				if (col - steps >= 0) {
					col = col - steps;
				} else 
					return new ResponseEntity<>(MovementConstants.ERROR, HttpStatus.BAD_REQUEST);
			} else if (cmd[i + 1] == 'U') {
				if (row - steps >= 0) {
					row = row - steps;
				} else 
					return new ResponseEntity<>(MovementConstants.ERROR, HttpStatus.BAD_REQUEST);
			} else if (cmd[i + 1] == 'D') {
				if (row + steps < size) {
					row = row + steps;
				} else 
					return new ResponseEntity<>(MovementConstants.ERROR, HttpStatus.BAD_REQUEST);
			}
		}
		if (row < size && col < size)
			return ResponseEntity.ok(MovementConstants.SUCCESS + MovementDao.array[row][col]);
		return new ResponseEntity<>(MovementConstants.ERROR, HttpStatus.BAD_REQUEST);
	}

}
