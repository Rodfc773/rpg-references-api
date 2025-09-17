package io.github.Rodfc773.rpg_references_api.unit.users;

import io.github.Rodfc773.rpg_references_api.users.application.port.repository.UserRepositoryPort;
import io.github.Rodfc773.rpg_references_api.users.application.services.UserService;
import io.github.Rodfc773.rpg_references_api.users.domain.exceptions.InvalidDataException;
import io.github.Rodfc773.rpg_references_api.users.domain.exceptions.UserAlreadyExists;
import io.github.Rodfc773.rpg_references_api.users.domain.models.RoleEnum;
import io.github.Rodfc773.rpg_references_api.users.domain.models.UserModel;
import io.github.Rodfc773.rpg_references_api.users.infrastructure.web.v1.dto.UserCreationRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class UserTests {

    @Mock
    private UserRepositoryPort userRepositoryPort;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("It should create user in the database")
    void createUserWithSuccess(){

        var dto = new UserCreationRequestDTO("Gazzeli", "123456", "gazzeli@gmail.com");

        when(userRepositoryPort.findByEmail(dto.email())).thenReturn(Optional.empty());

        when(passwordEncoder.encode(dto.password())).thenReturn("senha_hasheada_xyz");

        when(userRepositoryPort.save(any(UserModel.class))).thenAnswer(invocation -> {
            UserModel userSaved = invocation.getArgument(0);
            userSaved.setId(UUID.randomUUID());
            return userSaved;
        });

        var userData = userService.createUser(dto);

        assertNotNull(userData);
        assertEquals(dto.name(), userData.name());
        assertEquals(dto.name(), userData.name());
        assertEquals(dto.email(), userData.email());

        verify(userRepositoryPort, times(1)).findByEmail(dto.email());
        verify(userRepositoryPort, times(1)).save(any(UserModel.class));
        verify(passwordEncoder, times(1)).encode(dto.password());

    }

    @Test
    @DisplayName("It should fail if a user already exists")
    void failedUserCreation(){

        var dto = new UserCreationRequestDTO("Gazzeli2", "654321", "gazzeli@gmail.com");

        var userAlreadyExist = new UserModel();

        userAlreadyExist.setId(UUID.randomUUID());
        userAlreadyExist.setName("Gazzeli");
        userAlreadyExist.setRole(RoleEnum.GAME_MASTER);
        userAlreadyExist.setPassword("123456");
        userAlreadyExist.setEmail("gazzeli@gmail.com");

        when(userRepositoryPort.findByEmail(dto.email())).thenReturn(Optional.of(userAlreadyExist));

        UserAlreadyExists thrownException = assertThrows(UserAlreadyExists.class, () -> userService.createUser(dto));

        assertEquals("User already exist", thrownException.getMessage());

        verify(userRepositoryPort, never()).save(any(UserModel.class));
        verify(passwordEncoder, never()).encode(dto.password());
        verify(userRepositoryPort, times(1)).findByEmail(dto.email());

    }

    @Test
    @DisplayName("Should delete users info if the user was found")
    void shouldDeleteUserWhenPassedCorrectUser(){
        var id = UUID.randomUUID();

        when(userRepositoryPort.existsById(id)).thenReturn(true);


        userService.deleteUser(id.toString());

        verify(userRepositoryPort, times(1)).deleteById(id);

        assertDoesNotThrow(()-> userService.deleteUser(id.toString()));
    }
}
