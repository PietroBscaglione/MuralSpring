package br.ufscar.dc.dsw.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record MuralUpdateDTO(
        @NotBlank @Size(max = 255) String autor,
        @NotBlank @Size(max = 255) String titulo,
        @NotBlank @Size(max = 4000) String conteudo,
        LocalDateTime dataCriacao
) {
}
