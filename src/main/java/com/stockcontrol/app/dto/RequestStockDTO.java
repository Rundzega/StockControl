package com.stockcontrol.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RequestStockDTO(@NotBlank(message = "Ticker can't be empty") @Size(max = 6) String ticker,
                              @NotBlank(message = "Name can't be empty") @Size(max = 100) String name,

                              @NotNull(message = "Tax group id can't be empty") Short taxGroupId
                              ) {}
