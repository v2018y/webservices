package com.vany.repositeroy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vany.model.Bar;
import com.vany.model.DAOUser;


public interface BarRepo extends JpaRepository<Bar, Integer> {

	List<Bar> findByDaoUser(DAOUser userDao);
}
