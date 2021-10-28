package com.application.model;

import com.application.dto.ProductDTO;

import javax.persistence.*;

@Entity(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String descricao;
    private String productIdentifier;
    private Float preco;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category_id;

    public Category getCategory() {
        return category_id;
    }

    public void setCategory(Category category) {
        this.category_id = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getProductIdentifier() {
        return productIdentifier;
    }

    public void setProductIdentifier(String productIdentifier) {
        this.productIdentifier = productIdentifier;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public static Product convert(ProductDTO productDTO) {
        Product product = new Product();
        product.setDescricao(productDTO.getDescricao());
        product.setProductIdentifier(productDTO.getProductIdentifier());
        product.setPreco(productDTO.getPreco());
        product.setNome(productDTO.getNome());

        if (productDTO.getCategory() != null) {
            product.setCategory(Category.convert(productDTO.getCategory()));
        }

        return product;
    }
}
