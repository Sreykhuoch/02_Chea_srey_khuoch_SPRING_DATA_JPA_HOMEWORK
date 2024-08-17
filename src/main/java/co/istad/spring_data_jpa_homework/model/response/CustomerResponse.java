package co.istad.spring_data_jpa_homework.model.response;


import co.istad.spring_data_jpa_homework.model.Customer;
import co.istad.spring_data_jpa_homework.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private Integer id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private List<OrderResponse> orderList;


    public CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(
                customer.getCustomerId(),
                customer.getCustomerName(),
                customer.getAddress(),
                customer.getPhoneNumber(),
                customer.getEmail().getEmail(),
                new OrderResponse().toOrderResponse(customer.getOrders())
        );
    }

    public List<CustomerResponse> toListResponse(List<Customer> content) {
        List<CustomerResponse> customerResponses = new ArrayList<>();
        for (Customer customer : content) {
            CustomerResponse customerResponse = new CustomerResponse(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getAddress(),
                    customer.getPhoneNumber(),
                    customer.getEmail().getEmail(),
                    new OrderResponse().toOrderResponse(customer.getOrders())
            );
            customerResponses.add(customerResponse);
        }
        return customerResponses;
    }
}
