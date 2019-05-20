package com.learning.app1.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.app1.model.Employee;

@RestController
@RequestMapping("/api/app/employee")
public class App1EmployeeController {

	@PostMapping("/employee")
	public ResponseEntity<String> saveEmployee(@RequestBody Employee employee) {
		if (!ObjectUtils.isEmpty(employee))
			return new ResponseEntity<>( HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);

	}

	@PutMapping("/employee")
	public ResponseEntity<String> updateEmployee(@RequestBody Employee employee) {
		if (!ObjectUtils.isEmpty(employee))
			return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);

	}

	@GetMapping("/employee/{empId}")
	public ResponseEntity<Employee> emplyoyee(@PathVariable("empId") String empId) {
		Employee employee = new Employee();
		employee.setEmployeeId(empId);
		employee.setEmployeeName("Gannamaneni VenkateshwarRao");
		employee.setEmployeeEmail("venkateshwarrao.gannamaneni@capgeminic.om");
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}

	@DeleteMapping("/employee/{empId}")
	public ResponseEntity<String> updateEmployee(@PathVariable("empId") String empId) {
		if (empId != null)
			return new ResponseEntity<>("Employee Deleted Success", HttpStatus.OK);
		return new ResponseEntity<>("Employee Delete Faild", HttpStatus.BAD_REQUEST);
	}

}
