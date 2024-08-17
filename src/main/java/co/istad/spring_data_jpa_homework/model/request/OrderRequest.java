package co.istad.spring_data_jpa_homework.model.request;


import co.istad.spring_data_jpa_homework.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Integer qauantity;
    private Integer productId;
}
