package co.istad.spring_data_jpa_homework.service.serviceImpl;

import co.istad.spring_data_jpa_homework.controller.ProductController;
import co.istad.spring_data_jpa_homework.exception.NotFoundException;
import co.istad.spring_data_jpa_homework.model.Product;
import co.istad.spring_data_jpa_homework.model.request.ProductRequest;
import co.istad.spring_data_jpa_homework.repository.ProductRepository;
import co.istad.spring_data_jpa_homework.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    @Override
    public Product createProduct(ProductRequest productRequest) {
        return productRepository.save(productRequest.toProduct());
    }

    @Override
    public List<Product> getAllProduct(int page, int size, String sortBy, Sort.Direction orderby) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderby, sortBy));
        Page<Product> products = productRepository.findAll(pageable);
        return products.getContent();
    }

    @Override
    public Product getProductById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new NotFoundException("product Id is not found")
        );
        return product;
    }

    @Override
    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product updateProductById(ProductRequest productRequest, Integer id) {
         Product productUpdate = getProductById(id);
         productUpdate.setProductName(productRequest.getProductName());
         productUpdate.setUnitPrice(productRequest.getUnitPrice());
         productUpdate.setDescription(productUpdate.getDescription());

         return productRepository.save(productUpdate);

    }
}
