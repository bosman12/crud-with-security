package com.example.spring_example.сontroller;

import com.example.spring_example.model.Product;
import com.example.spring_example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model) {
        Product product = productService.findProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("image", productService.toBase64(product) );
        return "productPage";
    }



    @GetMapping("/product-create")
    public String createProductForm(Product product){
        return "productCreate";
    }

    @PostMapping("/product-create")
    public String createProduct(Product product, @RequestParam("file1")MultipartFile file1) throws IOException {
        productService.saveProduct(product,file1);
        return "redirect:/product-all";

    }

    @GetMapping ("/product-delete/{id}")
    public String deleteProduct(@PathVariable("id")long id){
        productService.deleteById(id);
        return "redirect:/product-all";
    }

    @GetMapping("/product-update/{id}")
    public String updateProductForm(@PathVariable("id") Long id, Model model){
        Product product = productService.findProductById(id);
        model.addAttribute("product", product);
        return "productUpdate";
    }

    @PostMapping("/product-update")
    public String updateProduct(Product product,@RequestParam("file1")MultipartFile file1) throws IOException {
        productService.saveProduct(product,file1);
        return "redirect:/product-all";
    }

}
