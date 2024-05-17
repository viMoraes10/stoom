package br.com.stoom.store.repository;

import br.com.stoom.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByProductName(String productName);

    List<Product> findByCategory(String category);

    List<Product> findByBrand(String brand);

    @Query(nativeQuery = true, value = "Select * from product where active = true")
    List<Product> findAllByActive();
}