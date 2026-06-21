package br.ufscar.dc.dsw.dto;

public record UserResponseDTO(
        Long id,
        String username,
        String role
) {
}
