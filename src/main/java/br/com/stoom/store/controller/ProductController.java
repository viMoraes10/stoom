package br.com.stoom.store.controller;

import br.com.stoom.store.dto.ProductDTO;
import br.com.stoom.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ProductController is a REST controller that provides endpoints for managing products.
 */
@RestController
@RequestMapping(value = "/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Endpoint to get all products.
     * @return ResponseEntity with the list of all products or an error message.
     */
    @GetMapping()
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(productService.findAll());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Request failed: " + e.getMessage());
        }
    }

    /**
     * Endpoint to get all active products.
     * @return ResponseEntity with the list of all active products or an error message.
     */
    @GetMapping("/active")
    public ResponseEntity<?> findAllActives() {
        try {
            return ResponseEntity.ok(productService.findAllByActive());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Request failed: " + e.getMessage());
        }
    }

    /**
     * Endpoint to add a new product.
     * @param productDTO Data Transfer Object containing product details.
     * @return ResponseEntity with the added product or an error message.
     */
    @PostMapping()
    public ResponseEntity<?> add(@RequestBody ProductDTO productDTO){
        try {
            return ResponseEntity.ok(productService.add(productDTO) );
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Request failed: " + e.getMessage());
        }
    }

    /**
     * Endpoint to delete a product.
     * @param id ID of the product to be deleted.
     * @return ResponseEntity with the deleted product or an error message.
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id ){
        try {
            return ResponseEntity.ok(productService.delete(id));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Request failed: " + e.getMessage());
        }
    }

    /**
     * Endpoint to update a product.
     * @param id ID of the product to be updated.
     * @param productDTO Data Transfer Object containing updated product details.
     * @return ResponseEntity with the updated product or an error message.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        try {
            return ResponseEntity.ok(productService.update(id, productDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Request failed: " + e.getMessage());
        }
    }

    /**
     * Endpoint to search for products by name.
     * @param productName Name of the product to be searched.
     * @return ResponseEntity with the list of matching products or an error message.
     */
    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String productName) {
        try {
            return ResponseEntity.ok(productService.findByProductName(productName));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Request failed: " + e.getMessage());
        }
    }

    /**
     * Endpoint to get products by category.
     * @param category Category of the products to be retrieved.
     * @return ResponseEntity with the list of products in the category or an error message.
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<?> findByCategory(@PathVariable String category) {
        try {
            return ResponseEntity.ok(productService.findByCategory(category));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Request failed: " + e.getMessage());
        }
    }

    /**
     * Endpoint to get products by brand.
     * @param brand Brand of the products to be retrieved.
     * @return ResponseEntity with the list of products of the brand or an error message.
     */
    @GetMapping("/brand/{brand}")
    public ResponseEntity<?> findByBrand(@PathVariable String brand) {
        try {
            return ResponseEntity.ok(productService.findByBrand(brand));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Request failed: " + e.getMessage());
        }
    }

    /**
     * Endpoint to activate a product.
     * @param id ID of the product to be activated.
     * @return ResponseEntity indicating success or an error message.
     */
    @PutMapping("/activate/{id}")
    public ResponseEntity<?> activate(@PathVariable Long id) {
        try {
            productService.activate(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Request failed: " + e.getMessage());
        }
    }

    /**
     * Endpoint to deactivate a product.
     * @param id ID of the product to be deactivated.
     * @return ResponseEntity indicating success or an error message.
     */
    @PutMapping("/deactivate/{id}")
    public ResponseEntity<?> deactivate(@PathVariable Long id) {
        try {
            productService.deactivate(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Request failed: " + e.getMessage());
        }
    }
}