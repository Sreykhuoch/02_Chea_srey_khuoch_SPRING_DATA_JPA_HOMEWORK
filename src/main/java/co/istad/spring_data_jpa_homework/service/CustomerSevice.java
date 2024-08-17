package co.istad.spring_data_jpa_homework.service;


import co.istad.spring_data_jpa_homework.model.Customer;
import co.istad.spring_data_jpa_homework.model.request.CustomerRequest;
import co.istad.spring_data_jpa_homework.model.response.CustomerResponse;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CustomerSevice {
    Customer createCustomer(CustomerRequest customerRequest);

    List<CustomerResponse> getAllCustomer(int page, int size, String sortBy, Sort.Direction orderBy);

    CustomerResponse getCustomerById(Integer id);

    CustomerResponse updateCustomer(CustomerRequest customerRequest, Integer id);

    void deleteCustomer(Integer id);
}
