package com.finzly.Service.Implementation;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.finzly.Helper.CSVHelper;
import com.finzly.Model.Employee;
import com.finzly.Repository.EmployeeRepository;
import com.finzly.Service.EmployeeService;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void save(MultipartFile file) {
		// TODO Auto-generated method stub

		List<Employee> employees;
		try {
			employees = CSVHelper.csvToEmployees(file.getInputStream());
			employeeRepository.saveAll(employees);
		} catch (IOException e) {
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}

	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

}
