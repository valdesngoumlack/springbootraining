package org.isnov.training.app.repositories;

import org.isnov.training.app.entities.UserGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
    @Query("select u from UserGroup u where upper(u.name) like upper(concat(?1, '%')) order by u.name")
    Page<UserGroup> findByNameStartsWithIgnoreCaseOrderByNameAsc(String name, Pageable pageable);
}