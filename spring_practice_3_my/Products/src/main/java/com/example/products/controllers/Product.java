package com.example.products.controllers;

import com.example.products.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class Product {

    //внедрение сервисного слоя
    private final ProductService productService;

    //конструктор сервисного слоя
    @Autowired
    public Product(ProductService productService){
        this.productService = productService;
    }

    //метод для получения списка всех товаров - главная страница
    @GetMapping("/product")
    public String index(Model model){
        model.addAttribute("product", productService.getAllProduct());
        return "product";
    }


    //метод для поиска товара по id и возврата шаблона с подробной информацией о конкретном продукте
    @GetMapping("/product/{id}")
    public String productInfo(Model model, @PathVariable("id") int id){
        model.addAttribute("product", productService.getProductId(id));
        return "product_info";
    }

    //метод позволяет отобразить представление с формой по добавлению товара
    @GetMapping("/product/add")
    public String newProduct(Model model){
        model.addAttribute("product", new com.example.products.models.Product());
        return "add_product";
    }

    //метод позволяет принять данные с формы и сохранить товар в лист
    @PostMapping("/product/add")
    public String newProduct(@ModelAttribute("product") @Valid com.example.products.models.Product product, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "add_product";
        }
        productService.newProduct(product);
        return "redirect:/product";
    }

    //данный метод позволяет получить редактируемый товар по id и вернуть форму редактирования товара
    @GetMapping("/product/edit/{id}")
    public String editProduct(Model model, @PathVariable("id") int id){
        model.addAttribute("edit_product", productService.getProductId(id));
        return "edit_product";
    }

    //данный метод позволяет принять редактируемый объект с формы и обновить информацию о редактируемом товаре
    @PostMapping("/product/edit/{id}")
    public String editProduct(@ModelAttribute("edit_product") @Valid com.example.products.models.Product product, BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            return "edit_product";
        }
        productService.editProduct(id,product);
        return "redirect:/product";
    }

    //данный метод позволяет удалить товар по id
    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        productService.delete(id);
        return "redirect:/product";
    }
}
