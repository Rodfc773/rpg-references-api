package io.github.Rodfc773.rpg_references_api.users.application.services;

import io.github.Rodfc773.rpg_references_api.common.domain.exceptions.ResourceNotFound;
import io.github.Rodfc773.rpg_references_api.users.application.port.repository.UserRepositoryPort;
import io.github.Rodfc773.rpg_references_api.users.domain.exceptions.InvalidDataException;
import io.github.Rodfc773.rpg_references_api.users.domain.exceptions.UserAlreadyExists;
import io.github.Rodfc773.rpg_references_api.users.domain.models.RoleEnum;
import io.github.Rodfc773.rpg_references_api.users.domain.models.UserModel;
import io.github.Rodfc773.rpg_references_api.users.infrastructure.web.v1.dto.UserCreationRequestDTO;
import io.github.Rodfc773.rpg_references_api.users.infrastructure.web.v1.dto.UserResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepositoryPort userRepositoryPort, PasswordEncoder passwordEncoder) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponseDTO createUser(UserCreationRequestDTO newUser){

        this.userRepositoryPort
                .findByEmail(newUser.email())
                .ifPresent(userModel -> {
                    throw new UserAlreadyExists("User already exist");
                });

        UserModel userToBeCreated = new UserModel();
        userToBeCreated.setName(newUser.name());
        userToBeCreated.setEmail(newUser.email());
        userToBeCreated.setPassword(passwordEncoder.encode(newUser.password()));
        userToBeCreated.setRole(RoleEnum.PLAYER);

        var createdUser = this.userRepositoryPort.save(userToBeCreated);

        return new UserResponseDTO(createdUser.getId().toString(), createdUser.getName(), createdUser.getUsername(), createdUser.getRole());
    }

    public List<UserResponseDTO> listUsers(){
        try{
            return this.userRepositoryPort
                    .findAll()
                    .stream()
                    .map(userModel -> new UserResponseDTO(
                            userModel.getId().toString(),
                            userModel.getName(),
                            userModel.getUsername(),
                            userModel.getRole())
                    )
                    .toList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public UserResponseDTO listOneUser(String id){


        if(id.isBlank()) throw new InvalidDataException("Invalid ID");

        var idConverted = UUID.fromString(id);

        UserModel foundUser = this.userRepositoryPort.findById(idConverted).orElseThrow(() -> new ResourceNotFound("User not found"));

        UserResponseDTO userResponseDTO = new UserResponseDTO(
                foundUser.getId().toString(),
                foundUser.getName(),
                foundUser.getUsername(),
                foundUser.getRole());

        return  userResponseDTO;
    }

    @Transactional
    public UserResponseDTO updateUser(String id, UserCreationRequestDTO newUser){
        if(id.isBlank()) throw new InvalidDataException("Id inválido");

        var idConverted = UUID.fromString(id);

        UserModel foundUser = this.userRepositoryPort.findById(idConverted).orElseThrow(() -> new ResourceNotFound("User Not Found"));

        foundUser.setEmail(newUser.email());

        return  null;

    }
    @Transactional
    public void deleteUser(String id){
        if(id.isBlank()) throw new InvalidDataException("Id inválido");

        var idConverted = UUID.fromString(id);

        if(!userRepositoryPort.existsById(idConverted)) throw new ResourceNotFound("User not found");

        this.userRepositoryPort.deleteById(idConverted);
    }
}
