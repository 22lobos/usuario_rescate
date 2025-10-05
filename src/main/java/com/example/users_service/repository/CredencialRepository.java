package com.example.users_service.repository;

import com.example.users_service.model.Credencial;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CredencialRepository extends JpaRepository<Credencial, Long> {
  Optional<Credencial> findByEmail(String email);
}
