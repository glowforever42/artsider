package com.ssafy.myapp.db.repository;

import com.ssafy.myapp.db.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long>  {
}
