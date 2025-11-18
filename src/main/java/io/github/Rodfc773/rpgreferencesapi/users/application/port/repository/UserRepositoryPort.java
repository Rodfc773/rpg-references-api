package io.github.rodfc773.rpgreferencesapi.users.application.port.repository;

import io.github.rodfc773.rpgreferencesapi.users.domain.models.UserModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {

    UserModel save(UserModel user);
    Optional<UserModel> findById(UUID id);
    Optional<UserModel> findByEmail(String email);
    List<UserModel> findAll();
    void deleteById(UUID id);
    boolean existsById(UUID id);
    Optional<UserModel> findByRefreshToken(String refreshToken);

}
