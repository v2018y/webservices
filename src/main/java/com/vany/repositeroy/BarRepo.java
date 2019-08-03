package com.vany.repositeroy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vany.model.Bar;


public interface BarRepo extends JpaRepository<Bar, Integer> {

	List<Bar> findByuserId(long userId);
}
