package io.github.Rodfc773.rpg_references_api.characters_charts.application.port.repository;

import io.github.Rodfc773.rpg_references_api.characters_charts.domain.models.CharacterStatus;

import java.util.Optional;
import java.util.UUID;

public interface CharacterStatusRepositoryPort {

    CharacterStatus save(CharacterStatus status);
    Optional<CharacterStatus> findById(UUID uuid);
    Optional<CharacterStatus> findByCharacterId(UUID characterId);

}
