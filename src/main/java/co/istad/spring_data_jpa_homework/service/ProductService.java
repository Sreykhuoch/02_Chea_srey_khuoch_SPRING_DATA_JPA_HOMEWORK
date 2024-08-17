package co.istad.spring_data_jpa_homework.service;

import co.istad.spring_data_jpa_homework.model.Product;
import co.istad.spring_data_jpa_homework.model.request.ProductRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductRequest productRequest);

    List<Product> getAllProduct(int page, int size, String sortBy, Sort.Direction orderby);

    Product getProductById(Integer id);

    void deleteProductById(Integer id);

    Product updateProductById(ProductRequest productRequest, Integer id);
}
