package com.application.service;

import com.application.dto.ProductDTO;
import com.application.exception.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class ProductService {

    public ProductDTO getProductByIdentifier(String productIdentifier) {

        try {
            RestTemplate template = new RestTemplate();
            String url = "http://localhost:8081/product/" + productIdentifier;
            ResponseEntity<ProductDTO[]> response = template.getForEntity(url, ProductDTO[].class);
            return Objects.requireNonNull(response.getBody())[0];
        } catch (HttpClientErrorException.NotFound e) {
            throw new ProductNotFoundException();
        }
    }
}
