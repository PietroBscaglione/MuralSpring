package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dto.MessageCreateDTO;
import br.ufscar.dc.dsw.dto.MessageResponseDTO;
import br.ufscar.dc.dsw.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/mensagens")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public ResponseEntity<List<MessageResponseDTO>> findAll() {
        return ResponseEntity.ok(messageService.findAll());
    }

    @PostMapping
    public ResponseEntity<MessageResponseDTO> create(@Valid @RequestBody MessageCreateDTO dto) {
        var created = messageService.create(dto);
        return ResponseEntity
                .created(URI.create("/api/mensagens/" + created.id()))
                .body(created);
    }
}
