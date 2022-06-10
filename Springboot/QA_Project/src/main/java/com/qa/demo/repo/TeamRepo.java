package com.qa.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.demo.domain.dao.Team;

@Repository
public interface TeamRepo extends JpaRepository<Team, Long> {

}
