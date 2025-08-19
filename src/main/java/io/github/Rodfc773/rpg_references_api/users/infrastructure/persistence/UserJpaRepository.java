package io.github.Rodfc773.rpg_references_api.users.infrastructure.persistence;

import io.github.Rodfc773.rpg_references_api.users.application.port.repository.UserRepositoryPort;
import io.github.Rodfc773.rpg_references_api.users.domain.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserJpaRepository extends JpaRepository<UserModel, UUID>, UserRepositoryPort {
}
