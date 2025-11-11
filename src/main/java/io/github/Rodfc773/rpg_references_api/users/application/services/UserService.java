package io.github.Rodfc773.rpg_references_api.users.application.services;

import io.github.Rodfc773.rpg_references_api.common.domain.exceptions.ResourceNotFound;
import io.github.Rodfc773.rpg_references_api.users.application.port.repository.UserRepositoryPort;
import io.github.Rodfc773.rpg_references_api.users.domain.exceptions.InvalidDataException;
import io.github.Rodfc773.rpg_references_api.users.domain.exceptions.UserAlreadyExists;
import io.github.Rodfc773.rpg_references_api.users.domain.models.RoleEnum;
import io.github.Rodfc773.rpg_references_api.users.domain.models.UserModel;
import io.github.Rodfc773.rpg_references_api.users.infrastructure.web.v1.dto.UserCreationRequestDTO;
import io.github.Rodfc773.rpg_references_api.users.infrastructure.web.v1.dto.UserPatchDTO;
import io.github.Rodfc773.rpg_references_api.users.infrastructure.web.v1.dto.UserResponseDTO;
import jakarta.transaction.Transactional;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepositoryPort userRepositoryPort, PasswordEncoder passwordEncoder) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepositoryPort.findByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + username)
                );
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

        return new UserResponseDTO(
                foundUser.getId().toString(),
                foundUser.getName(),
                foundUser.getUsername(),
                foundUser.getRole()
        );
    }

    @Transactional
    public UserResponseDTO updateUser(String id, UserPatchDTO newUser){
        if(id.isBlank()) throw new InvalidDataException("Id inválido");

        var idConverted = UUID.fromString(id);

        UserModel foundUser = this.userRepositoryPort.findById(idConverted).orElseThrow(() -> new ResourceNotFound("User Not Found"));


        newUser.getEmail().ifPresent(email -> {
            if (!isEmail(email)) throw new InvalidDataException("Email inválido");
            foundUser.setEmail(email);
        });

        newUser.getPassword().ifPresent(password -> {

            if (password.length() < 8 || password.length() > 100) {
                throw new InvalidDataException("The password must be between 8 and 100 characters");
            }
            foundUser.setPassword(passwordEncoder.encode(password));
        });

        newUser.getName().ifPresent(name -> {

            if(name.isBlank()) throw new InvalidDataException("The field name cannot be blank");

            foundUser.setName(name);
        });
        newUser.getRoleRole().ifPresent(foundUser::setRole);

        UserResponseDTO userResponseDTO = new UserResponseDTO(
                foundUser.getId().toString(),
                foundUser.getName(),
                foundUser.getUsername(),
                foundUser.getRole()
        );

        try{
            this.userRepositoryPort.save(foundUser);
            return  userResponseDTO;
        } catch (InvalidDataException e) {
            throw new InvalidDataException(e.getMessage());
        }

    }
    @Transactional
    public void deleteUser(String id){
        if(id.isBlank()) throw new InvalidDataException("Id inválido");

        var idConverted = UUID.fromString(id);

        if(!userRepositoryPort.existsById(idConverted)) throw new ResourceNotFound("User not found");

        this.userRepositoryPort.deleteById(idConverted);
    }

    private boolean isEmail(String email){

        return EmailValidator.getInstance().isValid(email);
    }
}
