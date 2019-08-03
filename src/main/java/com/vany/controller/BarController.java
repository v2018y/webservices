package com.vany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vany.exception.ResourceNotFoundException;
import com.vany.model.Bar;
import com.vany.repositeroy.BarRepo;
import com.vany.services.UserService;

@RestController
@RequestMapping("/bar")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600,allowCredentials= "false")
public class BarController {

	@Autowired
	BarRepo barRepo;
	
	
	@Autowired
	UserService userService;

	// Get All Item
	@GetMapping(value = "/")
	public List<Bar> getAllItem() {
		return barRepo.findByuserId(userService.getUserId());
	}

	// Get Item By Id
	@GetMapping(value = "/{id}")
	public Bar itemFindById(@PathVariable Integer id) {
		return barRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bar Controller", "id", id));
	}

	// Save Item
	@PostMapping(value = "/save")
	public Bar saveItem(@RequestBody Bar bar) {		
//		bar.setDaoUser(userService.getUserId());
		return barRepo.saveAndFlush(bar);
	}

	// Update a Employee
	@PutMapping("/{id}")
	public Bar updateItem(@PathVariable Integer id, @RequestBody Bar bar) {

		Bar findBar = barRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bar Controller ", "id", id));

		findBar.setItemName(bar.getItemName());
		findBar.setItemPrice(bar.getItemPrice());
		findBar.setItemQty(bar.getItemQty());
		findBar.setItemSize(bar.getItemSize());
		findBar.setItemType(bar.getItemType());
		return barRepo.saveAndFlush(findBar);
	}

	// Delete a Employee
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteItem(@PathVariable Integer id) {
		Bar findBar = barRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bar Controller", "id", id));
		barRepo.delete(findBar);
		return ResponseEntity.ok().build();
	}

	
	@RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
	public ResponseEntity<?> handle() {
		return new ResponseEntity(HttpStatus.OK);
	}

}
