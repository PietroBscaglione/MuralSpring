package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.repositories.Mural;
import br.ufscar.dc.dsw.repositories.MuralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/murais")
public class MuralController {

    @Autowired
    private MuralRepository muralRepository;

    @PostMapping
    public ResponseEntity<Mural> criar(@RequestBody Mural mural) {
        mural.setId(null);

        if (mural.getDataCriacao() == null) {
            mural.setDataCriacao(LocalDateTime.now());
        }

        Mural novoMural = muralRepository.save(mural);
        return new ResponseEntity<>(novoMural, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Mural>> listar() {
        List<Mural> murais = muralRepository.findAll();
        return new ResponseEntity<>(murais, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mural> buscarPorId(@PathVariable Long id) {
        return muralRepository.findById(id)
                .map(mural -> new ResponseEntity<>(mural, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mural> atualizar(@PathVariable Long id, @RequestBody Mural mural) {
        return muralRepository.findById(id)
                .map(muralExistente -> {
                    muralExistente.setAutor(mural.getAutor());
                    muralExistente.setTitulo(mural.getTitulo());
                    muralExistente.setConteudo(mural.getConteudo());
                    muralExistente.setDataCriacao(mural.getDataCriacao());

                    Mural muralAtualizado = muralRepository.save(muralExistente);
                    return new ResponseEntity<>(muralAtualizado, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return muralRepository.findById(id)
                .map(mural -> {
                    muralRepository.delete(mural);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
