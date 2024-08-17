package co.istad.spring_data_jpa_homework.controller;


import co.istad.spring_data_jpa_homework.model.Order;
import co.istad.spring_data_jpa_homework.model.StausEnum;
import co.istad.spring_data_jpa_homework.model.request.OrderRequest;
import co.istad.spring_data_jpa_homework.model.response.ApiResponse;
import co.istad.spring_data_jpa_homework.model.response.OrderResponse;
import co.istad.spring_data_jpa_homework.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("{customerId}")
    public ResponseEntity<?> createOrder(@PathVariable Integer customerId, @RequestBody List<OrderRequest> orderRequest) {
            OrderResponse order = orderService.createOrder(customerId, orderRequest);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .status(HttpStatus.CREATED)
                .message("Order created successfully")
                .payload(order)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Integer id) {
        OrderResponse order = orderService.getOrderById(id);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("get order id successfully")
                .payload(order)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getOrderByCustomerId(@PathVariable Integer customerId) {
        List<OrderResponse> order = orderService.getOrderByCustomerId(customerId);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("get order by customer id successfully")
                .payload(order)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<?>  updateOrderStatus(@PathVariable Integer id, @RequestParam StausEnum status) {
        OrderResponse order = orderService.updateOrderStatus(id, status);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("Order status updated successfully")
                .payload(order)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

}
