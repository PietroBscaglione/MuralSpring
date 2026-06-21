package br.ufscar.dc.dsw.service;

import br.ufscar.dc.dsw.dto.MuralCreateDTO;
import br.ufscar.dc.dsw.dto.MuralResponseDTO;
import br.ufscar.dc.dsw.dto.MuralUpdateDTO;
import br.ufscar.dc.dsw.exception.ResourceNotFoundException;
import br.ufscar.dc.dsw.model.Mural;
import br.ufscar.dc.dsw.repository.MuralRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MuralService {

    private final MuralRepository muralRepository;

    public MuralService(MuralRepository muralRepository) {
        this.muralRepository = muralRepository;
    }

    public MuralResponseDTO create(MuralCreateDTO dto) {
        var mural = new Mural();
        mural.setAutor(dto.autor());
        mural.setTitulo(dto.titulo());
        mural.setConteudo(dto.conteudo());
        mural.setDataCriacao(LocalDateTime.now());

        return toDTO(muralRepository.save(mural));
    }

    public List<MuralResponseDTO> findAll() {
        return muralRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public MuralResponseDTO findById(Long id) {
        return toDTO(findEntityById(id));
    }

    public MuralResponseDTO update(Long id, MuralUpdateDTO dto) {
        var mural = findEntityById(id);
        mural.setAutor(dto.autor());
        mural.setTitulo(dto.titulo());
        mural.setConteudo(dto.conteudo());
        mural.setDataCriacao(dto.dataCriacao() == null ? mural.getDataCriacao() : dto.dataCriacao());

        return toDTO(muralRepository.save(mural));
    }

    public void delete(Long id) {
        muralRepository.delete(findEntityById(id));
    }

    private Mural findEntityById(Long id) {
        return muralRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mural nao encontrado: " + id));
    }

    private MuralResponseDTO toDTO(Mural mural) {
        return new MuralResponseDTO(
                mural.getId(),
                mural.getAutor(),
                mural.getTitulo(),
                mural.getConteudo(),
                mural.getDataCriacao()
        );
    }
}
