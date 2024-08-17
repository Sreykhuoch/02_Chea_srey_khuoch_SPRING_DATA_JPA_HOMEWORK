package co.istad.spring_data_jpa_homework.model.response;


import co.istad.spring_data_jpa_homework.model.Order;
import co.istad.spring_data_jpa_homework.model.ProductOrder;
import co.istad.spring_data_jpa_homework.model.StausEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Integer id;
    private LocalDate orderDate;
    private Float totalAmount;
    private StausEnum stausEnum;
    private List<OrderProductResponse> productOrders;


    public List<OrderResponse> toOrderResponse(List<Order> orders) {
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order : orders) {
            OrderResponse orderResponse = new OrderResponse(
                    order.getId(),
                    order.getOrderDate(),
                    order.getTotalAmount(),
                    order.getStatusEnum(),
                    new OrderProductResponse().toOrderProductResponseList(new ArrayList<>(order.getProductOrders()))
            );
            orderResponses.add(orderResponse);
        }
        return orderResponses;
    }

}


