package io.github.rodfc773.rpgreferencesapi.characters.infrastructure.persistence;

import io.github.rodfc773.rpgreferencesapi.characters.domain.models.Characters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CharacterJpaRepository extends JpaRepository<Characters, UUID> {
}
