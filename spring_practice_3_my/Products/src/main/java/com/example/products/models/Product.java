package com.example.products.models;

import com.example.products.enumm.Provider;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity(name = "product_site")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Column(nullable = false, columnDefinition = "text")
    private String name;

    @NotNull(message = "Поле не может быть пустым")
    @Min(value = 1, message = "Цена товара не может быть меньше одного рубля")
    @Column(nullable = false, columnDefinition = "float")
    private float price;

    @NotNull(message = "Поле не может быть пустым")
    @Min(value = 1, message = "Вес товара не может быть меньше одного грамма")
    @Column(nullable = false, columnDefinition = "float")
    private float weight;

    private Provider provider;

    public Product(int id, String name, float price, float weight, Provider provider) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.provider = provider;
    }

    public Product() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
