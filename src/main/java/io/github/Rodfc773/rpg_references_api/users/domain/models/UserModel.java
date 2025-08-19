package io.github.Rodfc773.rpg_references_api.users.domain.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    @Column(nullable = false, unique = true)
    public String email;

    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
    public String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "role")
    public RoleEnum role;

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role.name()));
    }


}
