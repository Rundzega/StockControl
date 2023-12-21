package com.stockcontrol.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestOperationTypeDTO(@NotBlank(message = "Description can't be empty") @Size(max = 20) String description) {}
