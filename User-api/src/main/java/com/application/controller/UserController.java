package com.application.controller;

import com.application.service.UserService;
import com.application.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/")
    public List<UserDTO> getUsers() {
        return userService.getAll();
    }

    @GetMapping("/user/{id}")
    public UserDTO findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/user/newuser")
    public UserDTO newUser(@RequestBody UserDTO requestBody) {
        return userService.save(requestBody);
    }

    @GetMapping("/user/cpf/{cpf}")
    public UserDTO findByCpf(@RequestParam(name = "key", required = true) String key,
                             @PathVariable String cpf) {
        UserDTO userDTO =  userService.findByCpf(cpf, key);
        return userDTO;
    }

    @DeleteMapping("/user/{id}")
    public UserDTO delete(@PathVariable Long id) {
        return userService.delete(id);
    }

    @GetMapping("/user/search")
    public List<UserDTO> queryByName(@RequestParam(name = "nome", required = true) String nome) {
        return userService.queryByName(nome);
    }
}
