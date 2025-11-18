package io.github.rodfc773.rpgreferencesapi.characters.application.port.repository;

import io.github.rodfc773.rpgreferencesapi.characters.domain.models.CharacterStatus;

import java.util.Optional;
import java.util.UUID;

public interface CharacterStatusRepositoryPort {

    CharacterStatus save(CharacterStatus status);
    Optional<CharacterStatus> findById(UUID uuid);
    Optional<CharacterStatus> findByCharacterId(UUID characterId);

}
