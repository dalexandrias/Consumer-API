package com.application.service;

import com.application.dto.DTOConverter;
import com.application.dto.UserDTO;
import com.application.exception.CpfAlreadyRegisteredException;
import com.application.exception.UserNotFoundException;
import com.application.model.User;
import com.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAll() {
        List<User> usuarios = userRepository.findAll();
        return usuarios.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    public UserDTO findById(long id) {
        Optional<User> usuario = userRepository.findById(id);
        if (usuario.isPresent()) {
            return DTOConverter.convert(usuario.get());
        }
        return null;
    }

    public UserDTO save(UserDTO userDTO) {

        if (userRepository.findByCpf(userDTO.getCpf()) != null) {
            throw new CpfAlreadyRegisteredException();
        }
        userDTO.setKey(UUID.randomUUID().toString());
        userDTO.setDataCadastro(new Date());
        User user = userRepository.save(User.convert(userDTO));
        return DTOConverter.convert(user);
    }

    public UserDTO delete(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
        }
        return null;
    }

    public UserDTO findByCpf(String cpf, String key) {
        User user = userRepository.findByCpfAndKey(cpf, key);
        if (user != null) {
            return DTOConverter.convert(user);
        }
        throw new UserNotFoundException();
    }

    public List<UserDTO> queryByName(String name) {
        List<User> usuario = userRepository.queryByNomeLike(name);
        return usuario.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }
}