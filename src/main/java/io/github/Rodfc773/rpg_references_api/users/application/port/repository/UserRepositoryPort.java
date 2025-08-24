package io.github.Rodfc773.rpg_references_api.users.application.port.repository;

import io.github.Rodfc773.rpg_references_api.users.domain.models.UserModel;

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

}
