package com.stockcontrol.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestExchangeDTO(@NotBlank @Size(max = 4, min = 4) String mic,
                                 @NotBlank String name,
                                 @NotBlank @Size(max = 60) String country,
                                 @NotBlank @Size(min = 3, max =3) String currency)
{
}
