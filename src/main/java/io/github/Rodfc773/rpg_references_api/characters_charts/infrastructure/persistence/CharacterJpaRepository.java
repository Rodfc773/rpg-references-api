package io.github.Rodfc773.rpg_references_api.characters_charts.infrastructure.persistence;

import io.github.Rodfc773.rpg_references_api.characters_charts.application.port.repository.CharacterRepositoryPort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CharacterJpaRepository extends JpaRepository<UUID, CharacterRepositoryPort> {
}
