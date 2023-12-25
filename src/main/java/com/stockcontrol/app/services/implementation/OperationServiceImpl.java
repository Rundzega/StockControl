package com.stockcontrol.app.services.implementation;

import com.stockcontrol.app.domain.operation.Operation;
import com.stockcontrol.app.repositories.OperationRepository;
import com.stockcontrol.app.services.OperationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService {

    @Autowired
    private final OperationRepository operationRepository;


    @Override
    public Operation create(Operation operation) {
        return operationRepository.save(operation);
    }

    @Override
    public Operation findById(String id) {
        return operationRepository.findById(id).orElseThrow(() ->{
            throw new EntityNotFoundException("Operation not found");
        });
    }

    @Override
    public Collection<Operation> findAll() {
        return operationRepository.findAll();
    }

    @Override
    public void delete(String id) {
        operationRepository.deleteById(id);
    }
}
