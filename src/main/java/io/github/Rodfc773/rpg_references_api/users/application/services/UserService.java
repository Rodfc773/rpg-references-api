package io.github.Rodfc773.rpg_references_api.users.application.services;

import io.github.Rodfc773.rpg_references_api.users.application.port.repository.UserRepositoryPort;
import io.github.Rodfc773.rpg_references_api.users.domain.models.RoleEnum;
import io.github.Rodfc773.rpg_references_api.users.domain.models.UserModel;
import io.github.Rodfc773.rpg_references_api.users.infrastructure.web.v1.dto.UserCreationRequestDTO;
import io.github.Rodfc773.rpg_references_api.users.infrastructure.web.v1.dto.UserResponseDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepositoryPort userRepositoryPort, PasswordEncoder passwordEncoder) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO createUser(UserCreationRequestDTO newUser){

        this.userRepositoryPort
                .findByEmail(newUser.email())
                .ifPresent(userModel -> {
                    throw new RuntimeException("Ja existe uma conta registrada com esse email");
                });

        UserModel userToBeCreated = new UserModel();
        userToBeCreated.setName(newUser.name());
        userToBeCreated.setEmail(newUser.email());
        userToBeCreated.setPassword(passwordEncoder.encode(newUser.password()));
        userToBeCreated.setRole(RoleEnum.PLAYER);

        var createdUser = this.userRepositoryPort.save(userToBeCreated);

        return new UserResponseDTO(createdUser.getId().toString(), createdUser.getName(), createdUser.getUsername(), createdUser.getRole());
    }


}
