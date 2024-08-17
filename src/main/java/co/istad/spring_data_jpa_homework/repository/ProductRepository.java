package co.istad.spring_data_jpa_homework.repository;

import co.istad.spring_data_jpa_homework.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
