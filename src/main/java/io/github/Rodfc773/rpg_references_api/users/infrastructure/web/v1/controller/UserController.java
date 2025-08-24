package io.github.Rodfc773.rpg_references_api.users.infrastructure.web.v1.controller;

import io.github.Rodfc773.rpg_references_api.users.application.services.UserService;
import io.github.Rodfc773.rpg_references_api.users.infrastructure.web.v1.dto.UserCreationRequestDTO;
import io.github.Rodfc773.rpg_references_api.users.infrastructure.web.v1.dto.UserResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/users")
@Tag(name = "users", description = "Rotas referentes a lógica de usuário")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/create")
    @Operation(summary = "Cadastro do candidato", description = "Rota responsável pelo cadastro do candidato na base de dados")
    @ApiResponses({
            @ApiResponse(
                    responseCode  = "200",
                    content = {
                            @Content(schema = @Schema(implementation = UserResponseDTO.class))
                    }
            ),
            @ApiResponse(responseCode = "400", description = "User already exist")
    })
    public ResponseEntity<Object> createUser(@RequestBody UserCreationRequestDTO newUser){

        try{
            var createdUser = this.userService.createUser(newUser);
            return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(createdUser);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    public  ResponseEntity<Object> listUsers(){
        try{
            var users = this.userService.listUsers();
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(users);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
