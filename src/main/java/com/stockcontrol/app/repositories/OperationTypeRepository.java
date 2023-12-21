package com.stockcontrol.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.stockcontrol.app.domain.operation.OperationType;

/**
 * OperationTypeRepository
 */
public interface OperationTypeRepository extends JpaRepository<OperationType, String>{

   Optional<OperationType> findByDescription(String description);
}
