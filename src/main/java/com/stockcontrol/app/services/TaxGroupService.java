package com.stockcontrol.app.services;


import com.stockcontrol.app.domain.stock.Stock;
import com.stockcontrol.app.domain.stock.TaxGroup;

import java.util.Collection;
import java.util.List;

public interface TaxGroupService {
    TaxGroup create(TaxGroup taxGroup);

    Collection<TaxGroup> findAll();

    TaxGroup findById(Short id);
}
