package co.istad.spring_data_jpa_homework.model.request;


import co.istad.spring_data_jpa_homework.model.Product;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRequest {
    private String productName;
    private Float unitPrice;
    private String description;

    public Product toProduct() {
        return new Product(null, this.productName, this.unitPrice, this.description,null);
    }
}
