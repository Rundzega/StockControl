package com.stockcontrol.app.repositories;

import com.stockcontrol.app.domain.operation.Operation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OperationRepository extends JpaRepository<Operation, String> {
}
