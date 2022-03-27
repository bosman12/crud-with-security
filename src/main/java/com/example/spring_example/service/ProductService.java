package com.example.spring_example.service;

import com.example.spring_example.model.Product;
import com.example.spring_example.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findProductById(long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void deleteById(long id) {
        productRepository.deleteById(id);
    }

    public void saveProduct(Product product, MultipartFile file) throws IOException {
        if (file.getSize() != 0) {
            saveImage(product, file);
        }
        log.info("Saving new Product {}", product);
        productRepository.save(product);
    }

    public String toBase64(Product product){
        byte[] encodeBase64 = Base64.encode(product.getBytesImage());
        String base64Encoded = new String(encodeBase64, StandardCharsets.UTF_8);
        return base64Encoded;

    }

    private void saveImage(Product product, MultipartFile file) {
        try {
            byte[] bArray = new byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()) {
                bArray[i++] = b;
            }
            product.setBytesImage(bArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
