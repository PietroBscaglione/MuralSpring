package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dto.MuralCreateDTO;
import br.ufscar.dc.dsw.dto.MuralResponseDTO;
import br.ufscar.dc.dsw.dto.MuralUpdateDTO;
import br.ufscar.dc.dsw.service.MuralService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/murais")
public class MuralController {

    private final MuralService muralService;

    public MuralController(MuralService muralService) {
        this.muralService = muralService;
    }

    @GetMapping
    public ResponseEntity<List<MuralResponseDTO>> findAll() {
        return ResponseEntity.ok(muralService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MuralResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(muralService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MuralResponseDTO> create(@Valid @RequestBody MuralCreateDTO dto) {
        var created = muralService.create(dto);
        return ResponseEntity
                .created(URI.create("/api/murais/" + created.id()))
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MuralResponseDTO> update(@PathVariable Long id, @Valid @RequestBody MuralUpdateDTO dto) {
        return ResponseEntity.ok(muralService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        muralService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
