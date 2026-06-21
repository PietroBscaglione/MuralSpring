package br.ufscar.dc.dsw.service;

import br.ufscar.dc.dsw.dto.MessageCreateDTO;
import br.ufscar.dc.dsw.dto.MessageResponseDTO;
import br.ufscar.dc.dsw.exception.BusinessException;
import br.ufscar.dc.dsw.model.Message;
import br.ufscar.dc.dsw.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public MessageResponseDTO create(MessageCreateDTO dto) {
        if (dto.from().trim().equals(dto.to().trim())) {
            throw new BusinessException("Remetente e destinatario nao podem ser iguais.");
        }

        var message = new Message();
        message.setFrom(dto.from());
        message.setTo(dto.to());
        message.setMessage(dto.message());
        message.setTimestamp(new Date().toString());

        return toDTO(messageRepository.save(message));
    }

    public List<MessageResponseDTO> findAll() {
        return messageRepository.findAllOrderedByIdDesc().stream()
                .map(this::toDTO)
                .toList();
    }

    private MessageResponseDTO toDTO(Message message) {
        return new MessageResponseDTO(
                message.getId(),
                message.getFrom(),
                message.getTo(),
                message.getMessage(),
                message.getTimestamp()
        );
    }
}
