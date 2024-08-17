package co.istad.spring_data_jpa_homework.controller;


import co.istad.spring_data_jpa_homework.model.Customer;
import co.istad.spring_data_jpa_homework.model.request.CustomerRequest;
import co.istad.spring_data_jpa_homework.model.response.ApiResponse;
import co.istad.spring_data_jpa_homework.model.response.CustomerResponse;
import co.istad.spring_data_jpa_homework.repository.CustomerRepository;
import co.istad.spring_data_jpa_homework.service.CustomerSevice;
import lombok.Getter;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerSevice customerService;


    public CustomerController(CustomerSevice customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCustomer(@RequestBody CustomerRequest customerRequest){
        Customer customer =  customerService.createCustomer(customerRequest);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .status(HttpStatus.CREATED)
                .message("Customer created successfully")
                .payload(customer)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping
    public ResponseEntity<?> getCustomers(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam Sort.Direction orderBy
            ){
        List<CustomerResponse> customerResponses = customerService.getAllCustomer(page, size, sortBy, orderBy);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("Customer fetched successfully")
                .payload(customerResponses)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Integer id){
        CustomerResponse customer = customerService.getCustomerById(id);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("Customer fetched successfully")
                .payload(customer)
                .build();

        return ResponseEntity.ok(apiResponse);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerRequest customerRequest, @PathVariable Integer id){
        CustomerResponse customerResponse = customerService.updateCustomer(customerRequest, id);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("Customer updated successfully")
                .payload(customerResponse)
                .build();

        return ResponseEntity.ok(apiResponse);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer id){
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Customer deleted successfully");
    }


}
