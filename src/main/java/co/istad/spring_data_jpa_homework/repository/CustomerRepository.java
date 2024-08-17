package co.istad.spring_data_jpa_homework.repository;

import co.istad.spring_data_jpa_homework.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
}
