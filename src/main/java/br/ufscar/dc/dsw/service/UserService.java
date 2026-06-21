package br.ufscar.dc.dsw.service;

import br.ufscar.dc.dsw.dto.UserCreateDTO;
import br.ufscar.dc.dsw.dto.UserResponseDTO;
import br.ufscar.dc.dsw.dto.UserUpdateDTO;
import br.ufscar.dc.dsw.exception.BusinessException;
import br.ufscar.dc.dsw.exception.ResourceNotFoundException;
import br.ufscar.dc.dsw.model.User;
import br.ufscar.dc.dsw.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO create(UserCreateDTO dto) {
        validateUniqueUsername(dto.username(), null);

        var user = new User();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRole(dto.role());

        return toDTO(userRepository.save(user));
    }

    public List<UserResponseDTO> findAll() {
        return userRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public UserResponseDTO findById(Long id) {
        return toDTO(findEntityById(id));
    }

    public UserResponseDTO update(Long id, UserUpdateDTO dto) {
        var user = findEntityById(id);
        validateUniqueUsername(dto.username(), id);

        user.setUsername(dto.username());
        if (dto.password() != null && !dto.password().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.password()));
        }
        user.setRole(dto.role());

        return toDTO(userRepository.save(user));
    }

    public void delete(Long id) {
        userRepository.delete(findEntityById(id));
    }

    private void validateUniqueUsername(String username, Long currentUserId) {
        userRepository.findByUsername(username)
                .filter(user -> currentUserId == null || !user.getId().equals(currentUserId))
                .ifPresent(user -> {
                    throw new BusinessException("Username ja cadastrado.");
                });
    }

    private User findEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario nao encontrado: " + id));
    }

    private UserResponseDTO toDTO(User user) {
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getRole());
    }
}
