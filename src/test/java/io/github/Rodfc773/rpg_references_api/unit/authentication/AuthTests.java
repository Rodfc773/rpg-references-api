package io.github.Rodfc773.rpg_references_api.unit.authentication;

import io.github.Rodfc773.rpg_references_api.users.application.port.repository.UserRepositoryPort;
import io.github.Rodfc773.rpg_references_api.users.application.services.AuthService;
import io.github.Rodfc773.rpg_references_api.users.domain.exceptions.InvalidDataException;
import io.github.Rodfc773.rpg_references_api.users.domain.models.RoleEnum;
import io.github.Rodfc773.rpg_references_api.users.domain.models.UserModel;
import io.github.Rodfc773.rpg_references_api.users.infrastructure.security.TokenService;
import io.github.Rodfc773.rpg_references_api.users.infrastructure.web.v1.dto.LoginRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthTests {


    @Mock
    private UserRepositoryPort userRepositoryPort;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private TokenService tokenService;


    @InjectMocks
    private AuthService authService;


    private UserModel userFound;
    private String fakeToken;

    @BeforeEach
    void setUp(){

        userFound = new UserModel();

        userFound.setEmail("gazzeli@gmail.com");
        userFound.setPassword("123456");
        userFound.setName("Gazzeli");
        userFound.setId(UUID.randomUUID());
        userFound.setRole(RoleEnum.NATSU);

        fakeToken = "this.is.a.Fak3.T0k3n";

    }


    @Test
    @DisplayName("It should login with success")
    void loginSuccess(){

        var loginDto = new LoginRequestDTO("gazzeli@gmail.com", "123456");

        when(userRepositoryPort.findByEmail(loginDto.email())).thenReturn(Optional.of(userFound));
        when(passwordEncoder.matches(loginDto.password(), userFound.getPassword())).thenReturn(true);
        when(tokenService.generateToken(userFound)).thenReturn(fakeToken);

        var authenticatedUser = authService.authenticate(loginDto);

        assertNotNull(authenticatedUser.token());

        verify(userRepositoryPort, times(1)).findByEmail(loginDto.email());
        verify(passwordEncoder, times(1)).matches(loginDto.password(), userFound.getPassword());
    }

    @Test
    @DisplayName("It should fail a attempt login if the email is wrong")
    void shouldNotAuthenticateIfEmailIsWrong(){

        var loginDto = new LoginRequestDTO("gazzeli2@gmail.com", "123456");

        when(userRepositoryPort.findByEmail(loginDto.email())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> authService.authenticate(loginDto));

        verify(userRepositoryPort, times(1)).findByEmail(loginDto.email());
        verify(passwordEncoder, never()).matches(loginDto.password(), userFound.getPassword());


    }

    @Test
    @DisplayName("It should fail a attempt login if the password is wrong")
    void shouldNotAuthenticateIfPasswordIsWrong(){

        var loginDto = new LoginRequestDTO("gazzeli@gmail.com", "123567");

        when(userRepositoryPort.findByEmail(loginDto.email())).thenReturn(Optional.of(userFound));
        when(passwordEncoder.matches(loginDto.password(), userFound.getPassword())).thenReturn(false);

        assertThrows(InvalidDataException.class, () -> authService.authenticate(loginDto));

        verify(userRepositoryPort, times(1)).findByEmail(loginDto.email());
        verify(passwordEncoder, times(1)).matches(loginDto.password(), userFound.getPassword());


    }
}
