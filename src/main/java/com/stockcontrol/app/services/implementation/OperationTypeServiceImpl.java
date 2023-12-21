package com.stockcontrol.app.services.implementation;

import java.util.Collection;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stockcontrol.app.domain.operation.OperationType;
import com.stockcontrol.app.repositories.OperationTypeRepository;
import com.stockcontrol.app.services.OperationTypeService;

import lombok.RequiredArgsConstructor;

/**
 * OperationTypeImpl
 */
@Service
@RequiredArgsConstructor
@Transactional
public class OperationTypeServiceImpl implements OperationTypeService{

    @Autowired
    private final OperationTypeRepository operationTypeRepository;

	@Override
	public OperationType create(OperationType operationType) {
		if (operationTypeRepository.findByDescription(operationType.getDescription()).isPresent()) {
			throw new DataIntegrityViolationException("Operation Type already registered.");
		}
		return operationTypeRepository.save(operationType);
	}

	@Override
	public Collection<OperationType> findAll() {
		return operationTypeRepository.findAll();
	}

	@Override
	public OperationType findByDescription(String description) {
		return operationTypeRepository.findById(description).orElseThrow(() -> {
			throw new EntityNotFoundException("Operation Type with description " + description + " not found.");
		});
	}

	@Override
	public void delete(String id) {
		operationTypeRepository.deleteById(id);
	}
}
