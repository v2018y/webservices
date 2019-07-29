package com.vany.repositeroy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vany.model.SalesStateBar;

public interface SalesStateRepo extends JpaRepository<SalesStateBar, Integer> {

	List<SalesStateBar> findBycreatedAt(String createdAt);
}
