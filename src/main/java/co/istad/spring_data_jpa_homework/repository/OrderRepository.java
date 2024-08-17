package co.istad.spring_data_jpa_homework.repository;


import co.istad.spring_data_jpa_homework.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

    List<Order> findByCustomerCustomerId(Integer customerId);
}
