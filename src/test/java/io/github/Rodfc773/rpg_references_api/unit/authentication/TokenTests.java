package io.github.Rodfc773.rpg_references_api.unit.authentication;

import io.github.Rodfc773.rpg_references_api.users.domain.models.RoleEnum;
import io.github.Rodfc773.rpg_references_api.users.domain.models.UserModel;
import io.github.Rodfc773.rpg_references_api.users.infrastructure.security.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito.*;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TokenTests {

    private String fakeToken;
    private UserModel user;

    @Mock
    TokenService tokenService;

    @BeforeEach
    void setUp() {
        fakeToken = "this.is.a.Fak3.T0k3n";
        UserModel user = new UserModel();

        user.setId(UUID.randomUUID());
        user.setRole(RoleEnum.NATSU);
        user.setName("Gazzeli");
        user.setEmail("gazelli@gmail.com");
        user.setPassword("123456");
    }

    @Test
    @DisplayName("It should return a token if a user was sent")
    void shouldGenerateToken(){

        when(tokenService.generateToken(user)).thenReturn(fakeToken);

        var generatedToken = tokenService.generateToken(user);

        assertNotNull(generatedToken);

    }

    @Test
    @DisplayName("It should not generate a token if a user wasn't sent")
    void shouldNotGenerateToken(){

        when(tokenService.generateToken(null)).thenReturn(null);

        var generatedToken = tokenService.generateToken(null);

        assertNull(generatedToken);
    }
}
