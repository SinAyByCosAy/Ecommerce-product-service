package dev.tanay.productservice.repositories;

import dev.tanay.productservice.models.JwtKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JwtKeyRepository extends JpaRepository<JwtKeyEntity, Long> {
    Optional<JwtKeyEntity> findByKid(String kid);
}