package com.vany.repositeroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.vany.model.Empolyee;

@Component
public interface EmpolyeRepo extends JpaRepository<Empolyee, Integer> {
	Empolyee findByName(String name);

	
}
