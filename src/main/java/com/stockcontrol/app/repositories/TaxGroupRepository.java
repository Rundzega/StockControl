package com.stockcontrol.app.repositories;

import com.stockcontrol.app.domain.stock.TaxGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaxGroupRepository extends JpaRepository<TaxGroup, Short> {

}
