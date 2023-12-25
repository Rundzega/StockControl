package com.stockcontrol.app.domain.operation;

import com.stockcontrol.app.domain.account.Account;
import com.stockcontrol.app.domain.stock.Stock;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @JoinColumn
    @ManyToOne
    @NotNull
    private Account account;

    @JoinColumn
    @ManyToOne
    @NotNull
    private Stock stock;

    @JoinColumn
    @ManyToOne
    @NotNull
    private OperationType operationType;

    @Column
    @NotNull
    private LocalDate opDate;

    @Column
    @Positive
    @NotNull
    private Integer quantity;

    @Column
    @Positive
    @NotNull
    private Integer unitValueCents;

    @Column
    @Positive
    @NotNull
    private BigDecimal currencyConversionRate;

    @Column
    @Positive
    @NotNull
    private Integer unitValueCentsBrl;

    @Column
    @PositiveOrZero
    private Integer expensesCents;

    @Column
    @PositiveOrZero
    private Integer withheldTaxCentsBrl;

    @Column
    @CreatedDate
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
