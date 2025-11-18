package io.github.rodfc773.rpgreferencesapi.users.infrastructure.web.v1.dto;

import io.github.rodfc773.rpgreferencesapi.users.domain.models.RoleEnum;

import java.util.Optional;

public class UserPatchDTO{
    private Optional<String> name = Optional.empty();
    private Optional<String> password = Optional.empty();
    private Optional<String> email = Optional.empty();
    private Optional<RoleEnum> role = Optional.empty();

    UserPatchDTO(){}

    UserPatchDTO(String name, String password, String email, RoleEnum role){
        this.name = Optional.ofNullable(name);
        this.password = Optional.ofNullable(password);
        this.email = Optional.ofNullable(email);
        this.role = Optional.ofNullable(role);
    }


    public boolean isAnythingToUpdate(){
        return name.isPresent() || password.isPresent() || email.isPresent();
    }

    //GETTERS
    public Optional<String> getName() { return this.name; }
    public Optional<String> getPassword() { return this.password; }
    public Optional<String> getEmail() { return this.email; }
    public Optional<RoleEnum> getRoleRole() { return this.role; }

    //SETTERS
    public void setName(String name){ this.name = Optional.of(name); }
    public void setPassword(String password) { this.password = Optional.of(password); }
    public void setEmail(String email) {this.email = Optional.of(email); }
    public void setRole(RoleEnum role) {this.role = Optional.of(role); }
}
