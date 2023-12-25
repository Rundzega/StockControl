package com.stockcontrol.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestRoleDTO(@NotBlank @Size(max = 10) String id,
                             @NotBlank @Size(max = 60) String description) {
}
