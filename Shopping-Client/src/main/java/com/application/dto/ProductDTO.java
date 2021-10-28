package com.application.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductDTO {

    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    @NotBlank
    private String productIdentifier;
    @NotNull
    private Float preco;
    @NotNull
    private CategoryDTO category;

    public ProductDTO() {

    }

    public ProductDTO(String nome, String descricao, String productIdentifier, Float preco, CategoryDTO category) {
        this.nome = nome;
        this.descricao = descricao;
        this.productIdentifier = productIdentifier;
        this.preco = preco;
        this.category = category;
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

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }
}
