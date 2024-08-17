package co.istad.spring_data_jpa_homework.service.serviceImpl;


import co.istad.spring_data_jpa_homework.exception.NotFoundException;
import co.istad.spring_data_jpa_homework.model.Customer;
import co.istad.spring_data_jpa_homework.model.request.CustomerRequest;
import co.istad.spring_data_jpa_homework.model.response.CustomerResponse;
import co.istad.spring_data_jpa_homework.repository.CustomerRepository;
import co.istad.spring_data_jpa_homework.service.CustomerSevice;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerSevice {

    private final CustomerRepository customerRepository;
    @Override
    public Customer createCustomer(CustomerRequest customerRequest) {
      return customerRepository.save(customerRequest.toEntity());
    }

    @Override
    public List<CustomerResponse> getAllCustomer(int page, int size, String sortBy, Sort.Direction orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy, sortBy));
        Page<Customer> customers = customerRepository.findAll(pageable);
//        return customers.getContent();
        return new CustomerResponse().toListResponse(customers.getContent());
    }

    @Override
    public CustomerResponse getCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Customer id is not found")
        );
        return new CustomerResponse().toResponse(customer);
    }

    @Override
    public CustomerResponse updateCustomer(CustomerRequest customerRequest, Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Customer id is not found")
        );
      customer.setCustomerName(customerRequest.getName());
      customer.setAddress(customerRequest.getAddress());
      customer.setPhoneNumber(customerRequest.getPhoneNumber());
      customer.setEmail(customerRequest.toEntity().getEmail());
        return new CustomerResponse().toResponse(customerRepository.save(customer));
    }

    @Override
    public void deleteCustomer(Integer id) {
        getCustomerById(id);
        customerRepository.deleteById(id);
    }
}
