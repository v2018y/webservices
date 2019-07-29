package com.vany.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RestController;

import com.vany.exception.ResourceNotFoundException;
import com.vany.model.Bar;
import com.vany.model.DAOUser;
import com.vany.model.OpenSateBar;
import com.vany.model.SalesStateBar;
import com.vany.repositeroy.BarRepo;
import com.vany.repositeroy.SalesStateRepo;
import com.vany.repositeroy.UserDao;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SalesStateController {

	@Autowired
	SalesStateRepo salesStateRepo;

	@Autowired
	BarRepo barRepo;

	@Autowired
	UserDao userDao;

	// Get All SalesState Records
	@GetMapping(value = "/bar/salesState")
	public List<SalesStateBar> getAllSalesState() {
		return salesStateRepo.findAll();
	}

	// Get All SalesState Records By Date
	@GetMapping(value = "/bar/salesState/date/{Date}")
	public List<SalesStateBar> getAllSalesStateByDate(@PathVariable(value = "Date") String userDate) {
		return salesStateRepo.findAll();
	}

	// Get SalesState Record By Id
	@GetMapping(value = "/bar/salesState/id/{salesStateId}")
	public Optional<SalesStateBar> getSalesStateFindByItemId(@PathVariable(value = "salesStateId") Integer spid,
			Pageable pageable) {
		return salesStateRepo.findById(spid);
	}

	// Save SalesState Record
	@PostMapping(value = "/bar/{itemId}/salesState/save")
	public SalesStateBar saveSalesState(@PathVariable(value = "itemId") Integer bid, @RequestBody SalesStateBar salesBar) {
		Bar findBar = barRepo.findById(bid) .orElseThrow(() -> new ResourceNotFoundException("Open State Item ", "id", bid));
		
		//	This Line Show the Whatever Sales Qty Come as input
		System.out.println("Sales Qty : "+salesBar.getSalesQty());
		
		//	This Line What That Exact Qty In Database
		System.out.println("Bar Item Qty: "+findBar.getItemQty());
		
		//  This Line Shows Doing Opertions And Deduct That Qty.
		long updatedQty=findBar.getItemQty()-salesBar.getSalesQty();
		
		//  This line Shows the What Exact Value Geting
		System.out.println("Updated Item Qty: "+updatedQty);
		
		//  This We Can Update The Value Of Qty
		findBar.setItemQty(updatedQty);
		
		//  This Line We Set The Updated The Bar Item
		salesBar.setBar(findBar);
		
		//	This We Save and Return The Result. 
		return salesStateRepo.saveAndFlush(salesBar);

	}

	// Save All SalesState Record
	@PostMapping(value = "/bar/{itemId}/salesState/saveAll")
	public List<SalesStateBar> saveSalesBatchState(@PathVariable(value = "itemId") Integer bid,
			@RequestBody List<SalesStateBar> salesBar) {
		Bar findBar = barRepo.findById(bid).orElseThrow(() -> new ResourceNotFoundException("Open State Item ", "id", bid));
		
		for (SalesStateBar data : salesBar) {
			data.setBar(findBar);
		}
		
		return salesStateRepo.saveAll(salesBar);

	}

	// Update a SalesState Record
	@PutMapping("/bar/{itemId}/salesState/{salesStateId}")
	public SalesStateBar updateSalesState(@PathVariable(value = "itemId") Integer bid,
			@PathVariable(value = "salesStateId") Integer spid, @RequestBody SalesStateBar salesBar) {
		if (!barRepo.existsById(bid)) {
			throw new ResourceNotFoundException("Open State Item ", "id", bid);
		}
		SalesStateBar findSalesState = salesStateRepo.findById(spid)
				.orElseThrow(() -> new ResourceNotFoundException("Open State Not Found ", "id", spid));
		findSalesState.setSalesQty(salesBar.getSalesQty());
		return salesStateRepo.saveAndFlush(findSalesState);
	}

	// Delete a SalesState Record
	@DeleteMapping("/bar/{itemId}/salesState/{salesStateId}")
	public ResponseEntity<?> deleteSalesState(@PathVariable(value = "itemId") Integer bid,
			@PathVariable(value = "salesStateId") Integer spid) {
		SalesStateBar findSalesState = salesStateRepo.findById(spid).orElseThrow(() -> new ResourceNotFoundException(
				"Sales State not found with id " + bid + " and postId " + spid, null, bid));
		salesStateRepo.delete(findSalesState);
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
	}

}
