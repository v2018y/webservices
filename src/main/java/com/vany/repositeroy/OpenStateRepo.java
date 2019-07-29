package com.vany.repositeroy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vany.model.OpenSateBar;

public interface OpenStateRepo extends JpaRepository<OpenSateBar, Integer> {

	List<OpenSateBar> findBycreatedAt(String createdAt);
	
}
