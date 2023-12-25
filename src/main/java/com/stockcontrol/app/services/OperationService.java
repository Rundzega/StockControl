package com.stockcontrol.app.services;

import com.stockcontrol.app.domain.operation.Operation;

import java.util.Collection;

public interface OperationService {
    Operation create(Operation operation);

    Operation findById(String id);

    Collection<Operation> findAll();

    void delete(String id);
}
