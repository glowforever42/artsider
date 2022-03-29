package com.ssafy.myapp.db.repository;

import com.ssafy.myapp.db.entity.Expectation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpectRepository extends JpaRepository<Expectation, Long> {
}
