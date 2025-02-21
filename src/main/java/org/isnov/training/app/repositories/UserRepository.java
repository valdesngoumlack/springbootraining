package org.isnov.training.app.repositories;

import org.isnov.training.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  @Query("select distinct u from User u where upper(u.name) like upper(concat('%', ?1, '%')) order by u.name")
  List<User> findDistinctByNameContainsIgnoreCaseOrderByNameAsc(String name);
}