package io.github.rodfc773.rpgreferencesapi.users.infrastructure.persistence;

import io.github.rodfc773.rpgreferencesapi.users.application.port.repository.UserRepositoryPort;
import io.github.rodfc773.rpgreferencesapi.users.domain.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserJpaRepository extends JpaRepository<UserModel, UUID>, UserRepositoryPort {
}
