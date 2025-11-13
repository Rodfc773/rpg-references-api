package io.github.rodfc773.rpgreferencesapi.users.infrastructure.web.v1.controller;

import io.github.rodfc773.rpgreferencesapi.common.domain.exceptions.ResourceNotFound;
import io.github.rodfc773.rpgreferencesapi.users.application.services.UserService;
import io.github.rodfc773.rpgreferencesapi.users.domain.exceptions.InvalidDataException;
import io.github.rodfc773.rpgreferencesapi.users.infrastructure.web.v1.dto.UserCreationRequestDTO;
import io.github.rodfc773.rpgreferencesapi.users.infrastructure.web.v1.dto.UserPatchDTO;
import io.github.rodfc773.rpgreferencesapi.users.infrastructure.web.v1.dto.UserPutDTO;
import io.github.rodfc773.rpgreferencesapi.users.infrastructure.web.v1.dto.UserResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api/v1/users")
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
            @ApiResponse(responseCode = "400", description = "User already exist"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<Object> createUser(@RequestBody @Valid UserCreationRequestDTO newUser){

        var createdUser = this.userService.createUser(newUser);

        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(createdUser);

    }
    @GetMapping("/all")
    @Operation(summary = "Listagem dos usuários do sistema", description = "Rota responsável pela listagem dos usuários do sistema")
    @ApiResponses({
            @ApiResponse( responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = UserResponseDTO[].class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public  ResponseEntity<Object> listUsers(){

        var users = this.userService.listUsers();

        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(users);

    }
    @GetMapping("/user/{id}")
    @Operation(summary = "Listagem de um usuário", description = "Rota responsável pela listagem de um usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = UserResponseDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid ID"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server Error")
    })
    public ResponseEntity<Object> listOneUser(@PathVariable String id) {

        var user = this.userService.listOneUser(id);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(user);

    }
    @DeleteMapping("/user/{id}")
    @Operation(summary = "Deleta de um usuário", description = "Rota responsável por deletar a rota de um usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User deleted"),
            @ApiResponse(responseCode = "400", description = "Invalid ID"),
            @ApiResponse(responseCode = "500", description = "Internal server Error")
    })
    public ResponseEntity<Object> deleteUser(@PathVariable String id){

        this.userService.deleteUser(id);

        return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();

    }

    @PatchMapping("/user/{id}")
    @Operation(summary = "Rota resposável por atualizar o usuário", description = "Rota responsável por realizar update dos usuários")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User updated successfuly",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation= UserPutDTO.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Houve algum problema de validação com os dados enviados",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = InvalidDataException.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuário não encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResourceNotFound.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RuntimeException.class))
            )

    })
    public ResponseEntity<Object> updateUser(@PathVariable String id, @RequestBody UserPatchDTO updateUser){

        var response = this.userService.updateUser(id, updateUser);

        return ResponseEntity.ok().body(response);
    }
}
