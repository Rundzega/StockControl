package com.stockcontrol.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestAccountDTO(@NotBlank @Size(max = 60) String firstName,
                                @NotBlank @Size(max = 60) String lastName,
                                @NotBlank @Email String email) {
}
