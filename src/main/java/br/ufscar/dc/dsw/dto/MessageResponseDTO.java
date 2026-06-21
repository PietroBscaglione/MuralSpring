package br.ufscar.dc.dsw.dto;

public record MessageResponseDTO(
        Long id,
        String from,
        String to,
        String message,
        String timestamp
) {
}
