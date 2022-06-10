package com.qa.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.demo.domain.dao.Driver;

@Repository
public interface DriverRepo extends JpaRepository<Driver, Long> {

}
