package com.application.service;

import com.application.dto.ProductDTO;
import com.application.exception.CategoryNotFoundException;
import com.application.exception.ProductNotFoundException;
import com.application.dto.DTOConverter;
import com.application.model.Product;
import com.application.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getAll() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    public List<ProductDTO> getProductByCategoryId(Long categoryId) {
        List<Product> products = productRepository.getProductByCategory(categoryId);
        return products.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    public List<ProductDTO> findByProductIdentifier( String productIdentifier) {
        List<Product> product = productRepository.findByProductIdentifier(productIdentifier);

        if (!product.isEmpty()) {
            return product.stream().map(DTOConverter::convert).collect(Collectors.toList());
        }

        throw new ProductNotFoundException();
    }

    public ProductDTO save(ProductDTO productDTO) {
        Long existsCategory = productRepository.existsCategoryId(productDTO.getCategory().getId());

        if (existsCategory != null) {
            Product product = productRepository.save(Product.convert(productDTO));
            return DTOConverter.convert(product);
        }
        throw new CategoryNotFoundException();
    }

    public String delete(long productId) {
        Optional<Product> product = productRepository.findById(productId);

        if (product.isPresent()) {
            productRepository.delete(product.get());
            return "Sucesso";
        }
        throw new ProductNotFoundException();
    }
}
