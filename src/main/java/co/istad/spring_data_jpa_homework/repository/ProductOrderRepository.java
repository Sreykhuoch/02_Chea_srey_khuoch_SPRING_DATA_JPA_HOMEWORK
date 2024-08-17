package co.istad.spring_data_jpa_homework.repository;

import co.istad.spring_data_jpa_homework.model.Product;
import co.istad.spring_data_jpa_homework.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer> {
}
