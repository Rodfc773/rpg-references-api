package io.github.Rodfc773.rpg_references_api.characters_charts.application.port.repository;

import io.github.Rodfc773.rpg_references_api.characters_charts.domain.models.Characters;

import java.util.Optional;
import java.util.UUID;

public interface CharacterRepositoryPort {

    Characters save(Characters characters);
    Optional<Characters> findById(UUID uuid);
    Optional<Characters> findByName(String name);

}
