package io.github.rodfc773.rpgreferencesapi.characters.domain.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "status")
public class CharacterStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // ENERGIES
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

    // BASIC DEFENSE AND INFORMATIONAL ATTRIBUTES
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

    // BASIC ATTRIBUTES
    @Column(name = "magic_power", nullable = false)
    private long magicPower;

    @Column(name = "strength", nullable = false)
    private long strength;

    @Column(name = "dexterity", nullable = false)
    private long dexterity;

    @Column(name = "constitution", nullable = false)
    private long constitution;

    @Column(name="wisdom", nullable = false)
    private long wisdom;

    @Column(name = "stamina", nullable = false)
    private long stamina;

    @Column(name = "espiritual_energy", nullable = false)
    private  long  espiritualEnergy;

    @Column(name = "presence", nullable = false)
    private long presence;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id", nullable = false)
    private Characters character;

    public CharacterStatus(){}

    // GETTERS
    public UUID getID() { return this.id; }

    public long getLifePoints() { return this.lifePoints; }

    public BigDecimal getMoney() { return this.money; }

    public long getManaPoints() { return this.manaPoints; }

    public long getAuraPoints() { return this.auraPoints; }

    public long getChakraPoints() { return this.chakraPoints; }

    public long getBodyPoints() { return this.bodyPoints; }

    public Ranks getRank() { return this.rank; }

    public long getFightPower() { return this.fightPower; }

    public long getEvasiveAC() { return this.evasiveAC; }

    public long getPhysicalAC() { return this.physicalAC; }

    public int getDiceType() { return this.diceType; }

    public int getDiceCount() { return this.diceCount; }

    public long getMagicPower() { return this.magicPower; }

    public long getStrength() { return this.strength; }

    public long getDexterity() { return this.dexterity; }

    public long getConstitution() { return this.constitution; }

    public long getWisdom() { return this.wisdom; }

    public long getStamina() { return this.stamina; }

    public long getEspiritualEnergy() { return this.espiritualEnergy; }

    public long getPresence() { return this.presence; }

    // SETTERS

    public void setLifePoints(long lifePoints) { this.lifePoints = lifePoints; }

    public void setMoney(BigDecimal money) { this.money = money; }

    public void setManaPoints(long manaPoints) { this.manaPoints = manaPoints; }

    public void setAuraPoints(long auraPoints) { this.auraPoints = auraPoints; }

    public void setChakraPoints(long chakraPoints) { this.chakraPoints = chakraPoints; }

    public void setBodyPoints(long bodyPoints) { this.bodyPoints = bodyPoints; }

    public void setRank(Ranks rank) { this.rank = rank; }

    public void setFightPower(long fightPower) { this.fightPower = fightPower; }

    public void setEvasiveAC(long evasiveAC) { this.evasiveAC = evasiveAC; }

    public void setPhysicalAC(long physicalAC) { this.physicalAC = physicalAC; }

    public void setDiceType(int diceType) { this.diceType = diceType; }

    public void setDiceCount(int diceCount) { this.diceCount = diceCount; }

    public void setMagicPower(long magicPower) { this.magicPower = magicPower; }

    public void setStrength(long strength) { this.strength = strength; }

    public void setDexterity(long dexterity) { this.dexterity = dexterity; }

    public void setConstitution(long constitution) { this.constitution = constitution; }

    public void setWisdom(long wisdom) { this.wisdom = wisdom; }

    public void setStamina(long stamina) { this.stamina = stamina; }

    public void setEspiritualEnergy(long espiritualEnergy) { this.espiritualEnergy = espiritualEnergy; }

    public void setPresence(long presence) { this.presence = presence; }

    public void setCharacter(Characters character) { this.character = character; }

}
