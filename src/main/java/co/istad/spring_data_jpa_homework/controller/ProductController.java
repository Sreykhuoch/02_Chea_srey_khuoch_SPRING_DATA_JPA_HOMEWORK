package co.istad.spring_data_jpa_homework.controller;


import co.istad.spring_data_jpa_homework.model.Product;
import co.istad.spring_data_jpa_homework.model.request.ProductRequest;
import co.istad.spring_data_jpa_homework.model.response.ApiResponse;
import co.istad.spring_data_jpa_homework.repository.ProductRepository;
import co.istad.spring_data_jpa_homework.service.ProductService;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    private final ProductService productService;


    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest) {
        Product product = productService.createProduct(productRequest);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .status(HttpStatus.CREATED)
                .message("Product created successfully")
                .payload(product)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAlllProduct(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam Sort.Direction orderby
    ){
        List<Product> product = productService.getAllProduct(page, size,  sortBy, orderby);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .status(HttpStatus.CREATED)
                .message("Get All Product successfully")
                .payload(product)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Integer id){
        Product  product = productService.getProductById(id);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .status(HttpStatus.CREATED)
                .message("Get product by id successfully")
                .payload(product)
                .build();

        return ResponseEntity.ok(apiResponse);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id){
        productService.deleteProductById(id);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("product delete successfully!!")
                .build();

        return ResponseEntity.ok(apiResponse);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateProductById(@RequestBody ProductRequest productRequest, @PathVariable Integer id){
       Product product=  productService.updateProductById(productRequest,id);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .status(HttpStatus.CREATED)
                .message("Product updated successfully")
                .payload(product)
                .build();

        return ResponseEntity.ok(apiResponse);
    }


}
