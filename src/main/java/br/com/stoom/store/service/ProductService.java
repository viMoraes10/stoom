package br.com.stoom.store.service;

import br.com.stoom.store.Enum.Status;
import br.com.stoom.store.dto.ProductDTO;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findAllByActive() {
        return productRepository.findAllByActive();
    }

    public ResponseEntity<?> add(ProductDTO productDTO) {

        try {
            Status.valueOf(productDTO.getStatus());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid status value");
        }

        Product product = new Product(productDTO);
        productRepository.saveAndFlush(product);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> delete(Long id) {

        productRepository.delete(productRepository.findById(id).get());

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> update(Long id, ProductDTO productDTO) {
        try {
            Status.valueOf(productDTO.getStatus());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid status value");
        }

        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setProductName(productDTO.getProductName());
        product.setDescription(productDTO.getDescription());
        product.setCategory(productDTO.getCategory());
        product.setBrand(productDTO.getBrand());
        product.setPrice(productDTO.getPrice());
        product.setStatus(Status.valueOf(productDTO.getStatus()));

        return ResponseEntity.ok(productRepository.save(product));
    }

    public List<Product> findByProductName(String productName) {
        return productRepository.findByProductName(productName);
    }

    public List<Product> findByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> findByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    public void activate(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setStatus(Status.DISPONIVEL);
        product.setActive(true);
        productRepository.save(product);
    }

    public void deactivate(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setStatus(Status.INDISPONIVEL);
        product.setActive(false);
        productRepository.save(product);
    }
}
