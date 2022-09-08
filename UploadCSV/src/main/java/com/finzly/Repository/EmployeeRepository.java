package com.finzly.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finzly.Model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
