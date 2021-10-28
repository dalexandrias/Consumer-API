package com.application.service;

import com.application.dto.UserDTO;
import com.application.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class UserService {

    private final String USER_URL = "http://localhost:8080";

    public UserDTO getUserByCpf(String cpf, String key) {
        try {
            RestTemplate template = new RestTemplate();

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(USER_URL + "/user/cpf/" + cpf);
            builder.queryParam("key", key);

            ResponseEntity<UserDTO> response = template.getForEntity(builder.toUriString(), UserDTO.class);
            return response.getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new UserNotFoundException();
        }
    }
}
