package com.idat.ecommerce.service;

import com.idat.ecommerce.dto.ProductDto;
import com.idat.ecommerce.model.Product;
import jakarta.transaction.Transactional;


import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product createProduct(ProductDto productDto);
    Product updateProduct(String id, ProductDto productDto);
   // void changeStateProduct (String id);
    void deleteProduct(String id);
    List<Product> getProducts(Integer page, String country, String category, String sort);
    //long getTotalProducts(Integer page, String country, String category);
  //  int getTotalPage(Integer page, String country, String category);
    Optional<Product> getProduct(String product);
    Optional<Product> getProductByUuid(String id);

    @Transactional
   // void updateStock(int newStock, String state, String productUuid);

    List<Product> findProductByName(String product);
}
