package com.vany.controller;

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
import com.vany.model.OpenSateBar;
import com.vany.repositeroy.BarRepo;
import com.vany.repositeroy.OpenStateRepo;

@RestController
@RequestMapping("/bar")
@CrossOrigin(origins = "*")
public class OpenStateController {

	@Autowired
	OpenStateRepo openStateRepo;

	@Autowired
	BarRepo barRepo;

	// Get All OpenSate Records
	@GetMapping(value = "/openState")
	public List<OpenSateBar> getAllOpenState() {
		return openStateRepo.findAll();
	}

	// Get All OpenSate Records by Date
	@GetMapping(value = "/openState/date/{Date}")
	public List<OpenSateBar> getAllOpenStateByDate(@PathVariable(value = "Date") String userDate) {
		return openStateRepo.findAll();
	}

	// Get OpenSate Record By Id
	@GetMapping(value = "/openState/id/{openStateId}")
	public Optional<OpenSateBar> getOpenStateFindByItemId(@PathVariable(value = "openStateId") Integer opid,
			Pageable pageable) {
		return openStateRepo.findById(opid);
	}

	// Save OpenSate Record
	@PostMapping(value = "/{itemId}/openState/save")
	public OpenSateBar saveOpenState(@PathVariable(value = "itemId") Integer bid, @RequestBody OpenSateBar openBar) {
		Bar findBar = barRepo.findById(bid)
				.orElseThrow(() -> new ResourceNotFoundException("Open State Item ", "id", bid));
		openBar.setBar(findBar);
		return openStateRepo.saveAndFlush(openBar);

	}

	// Save All OpenSate Record
	@PostMapping(value = "/{itemId}/openState/saveAll")
	public List<OpenSateBar> saveOpenStateBatch(@PathVariable(value = "itemId") Integer bid,
			@RequestBody List<OpenSateBar> openBar) {
		Bar findBar = barRepo.findById(bid)
				.orElseThrow(() -> new ResourceNotFoundException("Open State Item ", "id", bid));
		for (OpenSateBar data : openBar) {
			data.setBar(findBar);
		}
		return openStateRepo.saveAll(openBar);
	}

	// Update a OpenSate Record
	@PutMapping("/{itemId}/openState/{openStateId}")
	public OpenSateBar updateOpenState(@PathVariable(value = "itemId") Integer bid,
			@PathVariable(value = "openStateId") Integer opid, @RequestBody OpenSateBar openBar) {
		if (!barRepo.existsById(bid)) {
			throw new ResourceNotFoundException("Open State Item ", "id", bid);
		}
		OpenSateBar findOpenState = openStateRepo.findById(opid)
				.orElseThrow(() -> new ResourceNotFoundException("Open State Not Found ", "id", opid));
		findOpenState.setOpenQty(openBar.getOpenQty());
		return openStateRepo.saveAndFlush(findOpenState);
	}

	// Delete a OpenSate Record
	@DeleteMapping("/{itemId}/openState/{openStateId}")
	public ResponseEntity<?> deleteOpenState(@PathVariable(value = "itemId") Integer bid,
			@PathVariable(value = "openStateId") Integer opid) {
		OpenSateBar findOpenState = openStateRepo.findById(opid).orElseThrow(() -> new ResourceNotFoundException(
				"Open State not found with id " + bid + " and postId " + opid, null, bid));
//		openStateRepo.delete(findOpenState);
		openStateRepo.delete(findOpenState);
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
	}

}
