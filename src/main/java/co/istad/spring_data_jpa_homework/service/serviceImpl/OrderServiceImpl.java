package co.istad.spring_data_jpa_homework.service.serviceImpl;

import co.istad.spring_data_jpa_homework.exception.NotFoundException;
import co.istad.spring_data_jpa_homework.model.*;
import co.istad.spring_data_jpa_homework.model.request.OrderRequest;
import co.istad.spring_data_jpa_homework.model.response.OrderProductResponse;
import co.istad.spring_data_jpa_homework.model.response.OrderResponse;
import co.istad.spring_data_jpa_homework.repository.CustomerRepository;
import co.istad.spring_data_jpa_homework.repository.OrderRepository;
import co.istad.spring_data_jpa_homework.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CustomerRepository customerRepository;
    private final ProductServiceImpl productServiceImpl;
    private final OrderRepository orderRepository;

    @Override
    public OrderResponse createOrder(Integer customerId, List<OrderRequest> orderRequest) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new NotFoundException("Customer not found"));
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDate.now());
        List<ProductOrder> productOrders = new ArrayList<>();
        orderRequest.forEach(orderRequest1 -> {
            Product product = productServiceImpl.getProductById(orderRequest1.getProductId());
            ProductOrder productOrder = new ProductOrder();
            productOrder.setProduct(product);
            productOrder.setQuantity(orderRequest1.getQauantity());
            productOrder.setOrder(order);
            productOrders.add(productOrder);
        });
        order.setProductOrders(productOrders);
        order.setTotalAmount(productOrders.stream().map(productOrder ->
                productOrder.getProduct().getUnitPrice() * productOrder.getQuantity()).reduce(0f, Float::sum));
        orderRepository.save(order);
       return new OrderProductResponse().toOrderResponse(order);
    }

    @Override
    public OrderResponse getOrderById(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Order not found"));

        return new OrderProductResponse().toOrderResponse(order);
    }

    @Override
    public List<OrderResponse> getOrderByCustomerId(Integer customerId) {
        List<Order> orders  = orderRepository.findByCustomerCustomerId(customerId);
       return orders.stream().map(order -> new OrderProductResponse().toOrderResponse(order)).collect(Collectors.toList());
    }

    @Override
    public OrderResponse updateOrderStatus(Integer id, StausEnum status) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Order not found"));
        order.setStatusEnum(status);
        return new OrderProductResponse().toOrderResponse(orderRepository.save(order));
    }

}
