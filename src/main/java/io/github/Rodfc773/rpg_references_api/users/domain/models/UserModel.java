package io.github.Rodfc773.rpg_references_api.users.domain.models;

import io.github.Rodfc773.rpg_references_api.characters_charts.domain.models.Characters;
import io.github.Rodfc773.rpg_references_api.common.domain.model.base.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserModel extends AuditableEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    @Email(message = "O email deve ser válido")
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Size(min=8, max=100, message = "A senha deve ter entre 8-100 caracteres")
    public String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "role")
    private RoleEnum role;

    @Override
    public String getUsername() {
        return this.email;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Characters> characters = new ArrayList<>();



    // GETTERS

    @Override
    public String getPassword() {
        return this.password;
    }
    public String getName(){
        return this.name;
    }
    public UUID getId(){
        return this.id;
    }
    public RoleEnum getRole(){
        return this.role;
    }

    //SETTERS
    public void setId(UUID id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setRole(RoleEnum role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role.name()));
    }
}
