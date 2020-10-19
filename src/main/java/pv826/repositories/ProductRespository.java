package pv826.repositories;


import pv826.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRespository extends JpaRepository<Product, Long> {
}
