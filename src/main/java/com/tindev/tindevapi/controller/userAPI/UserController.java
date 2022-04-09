package com.tindev.tindevapi.controller.userAPI;

import com.tindev.tindevapi.dto.user.*;
import com.tindev.tindevapi.enums.Roles;
import com.tindev.tindevapi.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
@RequiredArgsConstructor
@Api(value = "3 - User API", produces = MediaType.APPLICATION_JSON_VALUE, tags = {"3 - User API"})
public class UserController implements UserAPI{

    private final UserService userService;

    @GetMapping ("/list")
    public ResponseEntity<List<UserDTO>> listUser(@RequestParam(required = false) Integer id) throws Exception {
        return ResponseEntity.ok(userService.listUsers(id));
    }

    @PostMapping
    public ResponseEntity<UserDTOWithoutPassword> postUser(@Valid @RequestBody UserCreateDTO userCreateDTO, @RequestParam Roles role) throws Exception{
        return ResponseEntity.ok(userService.createUser(userCreateDTO, role));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updatedUser(@PathVariable("userId") Integer id,
                                               @Valid @RequestBody UserUpdateDTO userUpdateDTO) throws Exception {
        userService.updateUser(id, userUpdateDTO);
        return ResponseEntity.ok("Usuário Atualizado");
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Integer id) throws Exception {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted!");
    }

    @GetMapping("/getComplete")
    public ResponseEntity<List<UserDTOCompleto>> listUserComplete(@RequestParam(value = "id", required = false) Integer id) throws Exception {
        return ResponseEntity.ok(userService.listUserDTOComplete(id));
    }

    @GetMapping("/loged-user/getMyUser")
    public ResponseEntity<UserDTOCompleto> getLogedUser() throws Exception {
        return ResponseEntity.ok(userService.getUserLoged());
    }

    @PutMapping("/loged-user/update")
    public ResponseEntity<String> updatedLogedUser(@Valid @RequestBody UserUpdateDTO userUpdateDTO) throws Exception {
        userService.updateLogedUser(userUpdateDTO);
        return ResponseEntity.ok("Usuário Atualizado!");
    }

    @DeleteMapping("/loged-user/delete")
    public ResponseEntity<String> deleteLogedUser() throws Exception {
        userService.deleteUserLoged();
        return ResponseEntity.ok("User deleted");
    }

    @GetMapping("/loged-user/list-likes")
    public ResponseEntity<List<UserDTOWithoutPassword>> listLikesLogedUser() throws Exception {
        return ResponseEntity.ok(userService.listLikesOfTheLogedUser());
    }

    @GetMapping("/loged-user/list-received-likes")
    public ResponseEntity<List<UserDTOWithoutPassword>> listReceivedLikesLogedUser() throws Exception {
        return ResponseEntity.ok(userService.listReceivedLikesOfTheLogedUser());
    }

    @GetMapping("/loged-user/get-matches")
    public ResponseEntity<List<UserDTOWithoutPassword>> listMatchesLogedUser() throws Exception {
        return ResponseEntity.ok(userService.listMatchesOfTheLogedUser());
    }

    @GetMapping("/loged-user/get-available-users")
    public ResponseEntity<List<UserDTOWithoutPassword>> listAvailableUsersByLogedUser() throws Exception {
        return ResponseEntity.ok(userService.listAvailableLogedUser());
    }

    @PutMapping("/loged-user/change-role")
    public ResponseEntity<String> changeRoleLogedUser(@RequestParam Roles role) throws Exception {
        userService.changeRoleUserLoged(role);
        return ResponseEntity.ok("Seu plano foi trocado com sucesso!");
    }

}
