package io.github.Rodfc773.rpg_references_api.characters_charts.domain.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "status")
public class CharacterStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "life_points", nullable = false)
    private long lifePoints;

    @Column(name = "money")
    private BigDecimal money;

    @Column(name = "mana_points", nullable = false)
    private long manaPoints;

    @Column(name = "aura_points", nullable = false)
    private long auraPoints;

    @Column(name = "chakra_points", nullable = false)
    private long chakraPoints;

    @Column(name = "body_points", nullable = false)
    private  long bodyPoints;

    @Column(name = "rank", nullable = true)
    private Ranks rank;

    @Column(name = "fight_power")
    private long fightPower;

    @Column(name = "evasive_ac", nullable = false)
    private long evasiveAC;

    @Column(name = "physical_ac", nullable = false)
    private long physicalAC;

    @Column(name = "base_damage_dice_type", nullable = false)
    private int diceType;

    @Column(name = "base_damage_dice_count", nullable = false)
    private int diceCount;
}
