package io.github.Rodfc773.rpg_references_api.users.infrastructure.web.v1;

import io.github.Rodfc773.rpg_references_api.users.domain.models.UserModel;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@Tag(name = "users", description = "Rotas referentes a lógica de usuário")
public class UserController {

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody UserModel newUser){

        try{
            return ResponseEntity.ok().body("Olá");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
