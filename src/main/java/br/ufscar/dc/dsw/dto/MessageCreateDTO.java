package br.ufscar.dc.dsw.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MessageCreateDTO(
        @NotBlank @Size(max = 255) String from,
        @NotBlank @Size(max = 255) String to,
        @NotBlank @Size(max = 2000) String message
) {
}
