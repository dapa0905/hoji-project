package com.example.hoji_project.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hoji_project.model.UserEntity;

public interface MemberRepository extends JpaRepository<UserEntity, Long>{
  
  Optional<UserEntity> findByEmail(String email);
  
  Optional<UserEntity> findByName(String name);
  
  boolean existsByEmail(String email);
  
}
