package com.stockcontrol.app.domain.stock;

import com.stockcontrol.app.dto.RequestTaxGroupDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Generated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class TaxGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tax_group_seq")
    @SequenceGenerator(name = "tax_group_seq", initialValue = 1, allocationSize = 1, sequenceName = "tax_group_seq")
    private Short id;

    @NotBlank
    @Column
    private String description;

    @NotNull
    @Column
    @Positive
    @Max(1)
    private BigDecimal rate;

    @NotNull
    @Column
    @PositiveOrZero
    private Long exemptionCents;

    @NotNull
    @Positive
    private Short darfCode;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public TaxGroup(RequestTaxGroupDTO data) {
        this.description = data.description();
        this.rate = data.rate();
        this.exemptionCents = data.exemptionCents();
        this.darfCode = data.darfCode();
    }
}
