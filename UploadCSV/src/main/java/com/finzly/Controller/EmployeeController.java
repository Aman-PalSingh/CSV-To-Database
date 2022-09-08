package com.finzly.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.finzly.Helper.CSVHelper;
import com.finzly.Model.Employee;
import com.finzly.Service.EmployeeService;

@RestController
@RequestMapping("api/csv")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";
		if (CSVHelper.isCSV(file)) {
			try {
				employeeService.save(file);
				message = "Uploaded the file successfully: " + file.getOriginalFilename();
				return new ResponseEntity<>(message, HttpStatus.OK);
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				System.out.println(e.getMessage());
				return new ResponseEntity<>(message, HttpStatus.EXPECTATION_FAILED);
			}
		}
		message = "Please upload a csv file!";
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllTutorials() {
		try {
			List<Employee> employees = employeeService.getAllEmployees();
			if (employees.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(employees, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
