package io.github.Rodfc773.rpg_references_api.unit.users;

import io.github.Rodfc773.rpg_references_api.users.application.port.repository.UserRepositoryPort;
import io.github.Rodfc773.rpg_references_api.users.application.services.UserService;
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
public class CreateUserTest {

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

        when(passwordEncoder.encode(dto.password())).thenReturn("sehnah_hasheada_xyz");

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
}
