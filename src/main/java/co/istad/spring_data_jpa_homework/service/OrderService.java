package co.istad.spring_data_jpa_homework.service;


import co.istad.spring_data_jpa_homework.model.Order;
import co.istad.spring_data_jpa_homework.model.StausEnum;
import co.istad.spring_data_jpa_homework.model.request.OrderRequest;
import co.istad.spring_data_jpa_homework.model.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(Integer customerId, List<OrderRequest> orderRequest);

    OrderResponse getOrderById(Integer id);

    List<OrderResponse> getOrderByCustomerId(Integer customerId);

    OrderResponse updateOrderStatus(Integer id, StausEnum status);
}
