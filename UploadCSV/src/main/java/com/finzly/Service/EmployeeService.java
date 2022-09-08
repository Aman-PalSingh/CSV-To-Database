package com.finzly.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.finzly.Model.Employee;

public interface EmployeeService {
	public void save(MultipartFile file);
	public List<Employee> getAllEmployees();

}
