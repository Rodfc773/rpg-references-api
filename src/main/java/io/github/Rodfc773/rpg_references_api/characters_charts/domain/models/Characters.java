package io.github.Rodfc773.rpg_references_api.characters_charts.domain.models;


import io.github.Rodfc773.rpg_references_api.users.domain.models.UserModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.ManyToAny;


import java.util.UUID;

@Entity
@Table(name = "characters")
public class Characters {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, name = "photo_url")
    @NotBlank
    private String name;

    @Column(name = "photo_url")
    private String profilePhotoUrl;

    @Column(name = "level", nullable = false)
    private int level;

    @Column(name = "alignment", nullable = false)
    private String alignment;

    @Column(name = "blood_type", nullable = false)
    private String bloodType;

    @ManyToAny(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private UserModel user;

    public Characters(){}

    // GETTERS
    public String getID() { return this.id.toString(); }
    public String getName() { return this.name; }
    public String getProfilePhotoUrl() {return this.profilePhotoUrl; }
    public int getLevel() { return level;}
    public String getAlignment() {return  this.alignment; }
    public String getBloodType() { return this.bloodType; }

    // SETTERS
    public void setName(String name) { this.name = name; }
    public void setProfilePhotoUrl(String profilePhotoUrl) { this.profilePhotoUrl = profilePhotoUrl; }
    public void setLevel(int level) { this.level = level; }
    public void setAlignment(String alignment) { this.alignment = alignment; }
    public void setBloodType(String bloodType) { this.bloodType = bloodType; }
}
