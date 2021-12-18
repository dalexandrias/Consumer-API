package com.application.service;

import com.application.dto.ProductDTO;
import com.application.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class ProductService {

    @Value(value = "${POSTGRES_API_URL:http://localhost:8081/product/}")
    private String productApiURL;

    public ProductDTO getProductByIdentifier(String productIdentifier) {

        try {
            RestTemplate template = new RestTemplate();
            String url = productApiURL + productIdentifier;
            ResponseEntity<ProductDTO[]> response = template.getForEntity(url, ProductDTO[].class);
            return Objects.requireNonNull(response.getBody())[0];
        } catch (HttpClientErrorException.NotFound e) {
            throw new ProductNotFoundException();
        }
    }
}
