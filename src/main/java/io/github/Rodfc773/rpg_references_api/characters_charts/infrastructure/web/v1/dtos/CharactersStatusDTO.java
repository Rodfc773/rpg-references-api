package io.github.Rodfc773.rpg_references_api.characters_charts.infrastructure.web.v1.dtos;

import io.github.Rodfc773.rpg_references_api.characters_charts.domain.models.Ranks;

import java.math.BigDecimal;

public class CharactersStatusDTO {

    private final long lifePoints;
    private final long manaPoints;
    private final long auraPoints;
    private final long chakraPoints;
    private final long bodyPoints;

    private final BigDecimal currentMoney;
    private final Ranks rank;
    private final long fightPower;
    private final long evasiveAC;
    private final long physicalAC;
    private final int diceType;
    private final int diceCount;

    private final long magicPower;
    private final long strength;
    private final long dexterity;
    private final long constitution;
    private final long wisdom;
    private final long stamina;
    private final long espiritualEnergy;
    private final long presence;

    public static class Builder{
        private long lifePoints;
        private long manaPoints;
        private long auraPoints;
        private long chakraPoints;
        private long bodyPoints;
        private BigDecimal currentMoney;
        private Ranks rank;
        private long fightPower;
        private long evasiveAC;
        private long physicalAC;
        private int diceType;
        private int diceCount;
        private long magicPower;
        private long strength;
        private long dexterity;
        private long constitution;
        private long wisdom;
        private long stamina;
        private long espiritualEnergy;
        private long presence;

        public Builder lifePoints(long lifePoints) { this.lifePoints = lifePoints; return this;}

        public Builder manaPoints(long manaPoints) { this.manaPoints = manaPoints; return this;}

        public Builder auraPoints(long auraPoints) {this.auraPoints = auraPoints; return this; }

        public Builder chakraPoints(long chakraPoints) { this.chakraPoints = chakraPoints; return this;}

        public Builder bodyPoints(long bodyPoints) { this.bodyPoints = bodyPoints; return this; }

        public Builder currentMoney(BigDecimal currentMoney) { this.currentMoney = currentMoney; return this; }

        public Builder rank(Ranks rank) {this.rank = rank; return this; }

        public Builder fightPower(long fightPower) { this.fightPower = fightPower; return this;}

        public Builder evasiveAC(long evasiveAC) { this.evasiveAC = evasiveAC; return this;}

        public Builder physicalAC(long physicalAC) {this.physicalAC = physicalAC; return this;}

        public Builder diceType(int diceType) {this.diceType = diceType; return this;}

        public Builder diceCount(int diceCount) {this.diceCount = diceCount; return this;}

        public Builder magicPower(long magicPower) {this.magicPower = magicPower; return this;}

        public Builder strength(long strength) {this.strength = strength; return this;}

        public Builder dexterity(long dexterity) {this.dexterity = dexterity; return this;}

        public Builder constitution(long constitution) {this.constitution = constitution; return this;}

        public Builder wisdom(long wisdom) {this.wisdom = wisdom; return this;}

        public Builder stamina(long stamina) {this.stamina = stamina; return this;}

        public Builder espiritualEnergy(long espiritualEnergy) {this.espiritualEnergy = espiritualEnergy; return this;}

        public Builder presence(long presence) {this.presence = presence; return this;}

        public CharactersStatusDTO build(){
            return new CharactersStatusDTO(this);
        }
    }
    private CharactersStatusDTO(Builder builder){
        this.lifePoints = builder.lifePoints;
        this.manaPoints = builder.manaPoints;
        this.auraPoints = builder.auraPoints;
        this.chakraPoints = builder.chakraPoints;
        this.bodyPoints = builder.bodyPoints;
        this.currentMoney = builder.currentMoney;
        this.rank = builder.rank;
        this.fightPower = builder.fightPower;
        this.evasiveAC = builder.evasiveAC;
        this.physicalAC = builder.physicalAC;
        this.diceType = builder.diceType;
        this.diceCount = builder.diceCount;
        this.magicPower = builder.magicPower;
        this.strength = builder.strength;
        this.dexterity = builder.dexterity;
        this.constitution = builder.constitution;
        this.wisdom = builder.wisdom;
        this.stamina = builder.stamina;
        this.espiritualEnergy = builder.espiritualEnergy;
        this.presence = builder.presence;
    }

    public long getLifePoints() {return lifePoints;}

    public long getManaPoints() {return manaPoints;}

    public long getAuraPoints() {return auraPoints;}

    public long getChakraPoints() {return chakraPoints;}

    public long getBodyPoints() {return bodyPoints;}

    public BigDecimal getCurrentMoney() {return currentMoney;}

    public Ranks getRank() {return rank;}

    public long getFightPower() {return fightPower;}

    public long getEvasiveAC() {return evasiveAC;}

    public long getPhysicalAC() {return physicalAC;}

    public int getDiceType() {return diceType;}

    public int getDiceCount() {return diceCount;}

    public long getMagicPower() {return magicPower;}

    public long getStrength() {return strength;}

    public long getDexterity() {return dexterity;}

    public long getConstitution() {return constitution;}

    public long getWisdom() {return wisdom;}

    public long getStamina() {return stamina;}

    public long getEspiritualEnergy() {return espiritualEnergy;}

    public long getPresence() {return presence;}

}
