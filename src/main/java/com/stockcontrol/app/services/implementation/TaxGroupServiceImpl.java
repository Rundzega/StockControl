package com.stockcontrol.app.services.implementation;

import com.stockcontrol.app.domain.stock.Stock;
import com.stockcontrol.app.domain.stock.TaxGroup;
import com.stockcontrol.app.repositories.TaxGroupRepository;
import com.stockcontrol.app.services.TaxGroupService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class TaxGroupServiceImpl implements TaxGroupService {

    @Autowired
    private final TaxGroupRepository taxGroupRepository;

    @Override
    public TaxGroup create(TaxGroup taxGroup) {
        return taxGroupRepository.save(taxGroup);
    }

    @Override
    public Collection<TaxGroup> findAll() {
        return taxGroupRepository.findAll();
    }

    @Override
    public TaxGroup findById(Short id) {
        System.out.println(id);
        System.out.println(id.getClass());
        return taxGroupRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Tax group not found");
        });
    }
}
