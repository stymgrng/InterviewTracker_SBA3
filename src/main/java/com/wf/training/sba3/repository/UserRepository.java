package com.wf.training.sba3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wf.training.sba3.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
