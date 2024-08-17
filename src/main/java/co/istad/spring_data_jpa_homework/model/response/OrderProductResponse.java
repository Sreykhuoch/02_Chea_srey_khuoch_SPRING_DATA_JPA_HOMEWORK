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
public class OrderProductResponse {
  private Integer id;
  private String productName;
  private Float unitPrice;
  private String description;

  public List<OrderProductResponse> toOrderProductResponseList(ArrayList<ProductOrder> productOrders) {
    List<OrderProductResponse> orderProductResponses = new ArrayList<>();
    for (ProductOrder productOrder : productOrders) {
      OrderProductResponse orderProductResponse = new OrderProductResponse(productOrder.getProduct().getProductId(),
              productOrder.getProduct().getProductName(), productOrder.getProduct().getUnitPrice(), productOrder.getProduct().getDescription());
      orderProductResponses.add(orderProductResponse);
    }
    return orderProductResponses;
  }

  public OrderResponse toOrderResponse(Order order) {
    Integer orderId = order.getId();
    LocalDate orderDate = order.getOrderDate();
    Float totalAmount = order.getTotalAmount();
    StausEnum status = order.getStatusEnum();
    List<OrderProductResponse> productOrderResponses = new OrderProductResponse()
            .toOrderProductResponseList(new ArrayList<>(order.getProductOrders()));
    return new OrderResponse(orderId, orderDate, totalAmount, status, productOrderResponses);
  }
}
