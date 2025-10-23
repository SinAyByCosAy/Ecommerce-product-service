package dev.tanay.productservice.repositories;

import dev.tanay.productservice.models.TestModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestModel, Integer> {
}
