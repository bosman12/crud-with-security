package com.example.spring_example.сontroller;

import com.example.spring_example.model.Product;
import com.example.spring_example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product-all")
    public String findAllProducts(Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "productList";
    }

    @GetMapping("/product-create")
    public String createUserForm(Product product){
        return "productCreate";
    }

    @PostMapping("/product-create")
    public String createUser(Product product){
        productService.saveProduct(product);
        return "redirect:/product-all";
    }

    @GetMapping ("/product-delete/{id}")
    public String deleteUser(@PathVariable("id")long id){
        productService.deleteById(id);
        return "redirect:/product-all";
    }

    @GetMapping("/product-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        Object product = productService.findById(id);
        model.addAttribute("product", product);
        return "userUpdate";
    }

    @PostMapping("/product-update")
    public String updateUser(Product product){
        productService.saveProduct(product);
        return "redirect:/product-all";
    }

}
