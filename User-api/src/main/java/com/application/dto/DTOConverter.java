package com.application.dto;

import com.application.model.User;

public class DTOConverter {

    public static UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setNome(user.getNome());
        userDTO.setTelefone(user.getTelefone());
        userDTO.setEmail(user.getEmail());
        userDTO.setCpf(user.getCpf());
        userDTO.setEndereco(user.getEndereco());
        userDTO.setDataCadastro(user.getDataCadastro());
        userDTO.setKey(user.getKey());
        return userDTO;
    }
}
