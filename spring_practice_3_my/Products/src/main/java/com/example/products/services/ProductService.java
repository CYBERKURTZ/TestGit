package com.example.products.services;

import com.example.products.models.Product;
import com.example.products.repositories.ProductRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductService {

    //внедрение репозитория
    private ProductRepository productRepository;

    //конструктор репозитория
    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    //метод для получения списка всех товаров. по сути это SELECT * FROM PRODUCT_SITE;
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    //метод для получения товара по id. по сути это SELECT * FROM PRODUCT_SITE WHERE ID = {id};
    public Product getProductId(int id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    //метод позволяет добавить товар в таблицу product_site. по сути это:
    //INSERT INTO PRODUCT_SITE (name, price, weight, provider)
    //VALUES ('Картофель', 70, 3000, 'LadyLux')
    @Transactional //Spring автоматически определяет транзанкцию; происходит переопределение транзакции.
    public void newProduct(Product product){
        productRepository.save(product); //save и для добавления и для редактирования
    }

    //метод позволяет обновить данные по товару
    @Transactional //Spring автоматически определяет транзанкцию; происходит переопределение транзакции.
    public void editProduct(int id, Product product){
        product.setId(id);
        productRepository.save(product); //save и для добавления и для редактирования
    }

    //метод позволяет удалить товар по id. по сути это DELETE FROM PRODUCT_SITE WHERE ID = {id};
    @Transactional
    //Spring автоматически определяет транзанкцию; происходит переопределение транзакции.
    public void delete(int id){
        productRepository.deleteById(id);
    }
}
