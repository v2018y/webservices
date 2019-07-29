package com.vany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vany.exception.ResourceNotFoundException;
import com.vany.model.Empolyee;
import com.vany.repositeroy.EmpolyeRepo;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")   
public class EmployeeController {

	@Autowired
	EmpolyeRepo empolyeRepo;
	
	//Get All Employee
	@GetMapping(value="/emp")
	public java.util.List<Empolyee> getAllEmployee()
	{
		return  empolyeRepo.findAll();
	}
	
	//Get Employee By Id
	@GetMapping(value="/emp/{id}")
	public  Empolyee employeeFindById(@PathVariable Integer id)
	{
		return  empolyeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
	}
	
	//Save Employee
	@PostMapping(value = "/emp/save")
	public Empolyee saveEmployee(@RequestBody Empolyee emp){
		return empolyeRepo.saveAndFlush(emp);
	}
	
	// Update a Employee
	@PutMapping("/emp/{id}")
	public Empolyee updateEmpolyee(@PathVariable Integer id,@RequestBody Empolyee emp) {
		
		Empolyee findEmp=empolyeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
		
		findEmp.setName(emp.getName());
		findEmp.setSalary(emp.getSalary());
		findEmp.setDoj(emp.getDoj());
		
		return  empolyeRepo.saveAndFlush(findEmp);
		
	}
	
	// Delete a Employee
	@DeleteMapping("/emp/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Integer id){
		Empolyee findEmp=empolyeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
		empolyeRepo.delete(findEmp);
		return ResponseEntity.ok().build();
	}
	
}
