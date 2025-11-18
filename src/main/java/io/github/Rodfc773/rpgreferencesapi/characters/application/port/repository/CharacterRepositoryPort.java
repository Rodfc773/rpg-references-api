package io.github.rodfc773.rpgreferencesapi.characters.application.port.repository;

import io.github.rodfc773.rpgreferencesapi.characters.domain.models.Characters;

import java.util.Optional;
import java.util.UUID;

public interface CharacterRepositoryPort {

    Characters save(Characters characters);
    Optional<Characters> findById(UUID uuid);
    Optional<Characters> findByName(String name);

}
