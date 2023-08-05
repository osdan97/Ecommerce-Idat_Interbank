package com.idat.ecommerce.controller;

import com.idat.ecommerce.dto.Mensaje;
import com.idat.ecommerce.dto.ProductDto;
import com.idat.ecommerce.model.Product;
import com.idat.ecommerce.repository.ProductRepository;
import com.idat.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/add")
    public ResponseEntity<?> saveProduct(@RequestBody ProductDto productDto){
        try {
            if(productDto.getName() == null || productDto.getName().isEmpty()){
                return new ResponseEntity<>(new Mensaje("Product name can't be empty"), HttpStatus.BAD_REQUEST);
            }
            if (productDto.getDescription() == null || productDto.getDescription().isEmpty()){
                return new ResponseEntity<>(new Mensaje("Description can't be empty"), HttpStatus.BAD_REQUEST);
            }
            if(productDto.getImage() == null || productDto.getImage().isEmpty()){
                return new ResponseEntity<>(new Mensaje("Image can't be empty"), HttpStatus.BAD_REQUEST);
            }
            if(productDto.getPrice() == null || productDto.getPrice().isNaN()){
                return new ResponseEntity<>(new Mensaje("Price can't be empty"), HttpStatus.BAD_REQUEST);
            }
            if(productDto.getWeight() == null || productDto.getWeight().isNaN()){
                return new ResponseEntity<>(new Mensaje("Weight can't be empty"), HttpStatus.BAD_REQUEST);
            }
            if(productDto.getCountry() == null || productDto.getCountry().isEmpty()){
                return new ResponseEntity<>(new Mensaje("Country can't be empty"), HttpStatus.BAD_REQUEST);
            }

            if(productDto.getCategory() == null || productDto.getCategory().isEmpty()){
                return new ResponseEntity<>(new Mensaje("Country can't be empty"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/update/{name}")
    public ResponseEntity<?> updateProduct(@PathVariable String id, @RequestBody ProductDto productDto){
        try{
            if(id == null){
                return new ResponseEntity<>(new Mensaje("Id can't be null"), HttpStatus.INTERNAL_SERVER_ERROR);
            }else{
                if(productService.getProductByUuid(id).isPresent()){
                    return new ResponseEntity<>(productService.updateProduct(id, productDto), HttpStatus.OK);
                }
                return new ResponseEntity<>(new Mensaje("Product not found"), HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id){
        try{
            if(id == null){
                return new ResponseEntity<>(new Mensaje("Id can't be null"), HttpStatus.INTERNAL_SERVER_ERROR);
            }else{
                if(productService.getProductByUuid(id).isPresent()) {
                    productService.deleteProduct(id);
                    return new ResponseEntity<>(new Mensaje("Product delete successfully"), HttpStatus.OK);
                }
                return new ResponseEntity<>(new Mensaje("Product not found"), HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> getProducts(@RequestParam("page") Integer page,
                                         @RequestParam(value = "country", required = false) String country,
                                         @RequestParam(value = "category",required = false) String category,
                                         @RequestParam(value = "sort", required = false) String sort){
        try{
            if(page == null){
                return new ResponseEntity<>(new Mensaje("Page can't be null"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if(page < 1){
                return new ResponseEntity<>(new Mensaje("Page can't be less than 1"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(productService.getProducts(page, country, category, sort), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable String id){
        return new ResponseEntity<>(productService.getProductByUuid(id), HttpStatus.OK);
    }
    @GetMapping("/name/{producto}")
    public ResponseEntity<?> getProductByName(@PathVariable String producto){
        return new ResponseEntity<>(productService.findProductByName(producto), HttpStatus.OK);
    }
    @GetMapping("/list_products")
    public ResponseEntity<?>listCustomer(){
        List<Product> list=productRepository.findAll();
        return new ResponseEntity(list,HttpStatus.OK);
    }
}
