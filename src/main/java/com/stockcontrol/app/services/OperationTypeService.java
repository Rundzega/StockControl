package com.stockcontrol.app.services;

import com.stockcontrol.app.domain.operation.OperationType;

import java.util.Collection;

/**
 * OperationTypeService
 */
public interface OperationTypeService {
    
    OperationType create(OperationType operationType);

    Collection<OperationType> findAll();

    OperationType findByDescription(String description);

    void delete(String id);
}
