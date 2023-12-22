package com.stockcontrol.app.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record RequestTaxGroupDTO(@NotBlank String description,
                                 @NotNull @Positive @Max(1) BigDecimal rate,
                                 @NotNull @PositiveOrZero Long exemptionCents,
                                 @NotNull @Positive Short darfCode) {
}
