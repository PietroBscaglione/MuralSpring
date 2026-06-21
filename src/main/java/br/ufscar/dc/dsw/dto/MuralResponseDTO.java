package br.ufscar.dc.dsw.dto;

import java.time.LocalDateTime;

public record MuralResponseDTO(
        Long id,
        String autor,
        String titulo,
        String conteudo,
        LocalDateTime dataCriacao
) {
}
