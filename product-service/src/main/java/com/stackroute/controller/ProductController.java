package com.stackroute.controller;


import com.stackroute.domain.Product;
import com.stackroute.domain.ProductInfoResponseDTO;
import com.stackroute.domain.Seller;
import com.stackroute.exception.ProductAlreadyExistsException;
import com.stackroute.exception.ProductNotExistsException;
import com.stackroute.kafka.ProductRecomDto;
import com.stackroute.service.Producer;
import com.stackroute.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestController annotation is used to create Restful web services using Spring MVC
 */
@RestController

/**
 * RequestMapping annotation maps HTTP requests to handler methods
 */
@RequestMapping(value = "api/v1")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class ProductController {

    private ProductService productService;

    private Producer producer;

    @Autowired
    public ProductController(ProductService productService, Producer producer) {
        this.productService = productService;
        this.producer = producer;
    }

    @PostMapping("product")
    public ResponseEntity<?> saveProduct(@RequestBody Product product) throws ProductAlreadyExistsException {
        Product product1 = productService.saveProduct(product);
        System.out.println(product1.getProductId());
        ResponseEntity responseEntity = new ResponseEntity(product1, HttpStatus.CREATED);
        ProductRecomDto productRecomDto = new ProductRecomDto();
        productRecomDto.setProductBrandName(product.getProductBrandName());
        productRecomDto.setProductCategory(product.getProductCategory());
        productRecomDto.setProductDescription(product.getProductDescription());
        productRecomDto.setProductImage(product.getProductImage());
        productRecomDto.setProductName(product.getProductName());
        productRecomDto.setProductSubCategory(product.getProductSubCategory());
        productRecomDto.setSellerEmail(product.getSellers().get(0).getSellerId());
        productRecomDto.setProductPrice(product.getSellers().get(0).getProductPrice());
        productRecomDto.setProductStock(product.getSellers().get(0).getProductStock());
        this.producer.sendProductRecom(productRecomDto);
        this.producer.sendProduct(product1);
        this.producer.sendProductToMap(product1);
        return responseEntity;
    }

    @PostMapping("product/details")
    public ResponseEntity<?> getProductDetails(@RequestParam("productName") String productId) throws ProductNotExistsException {
        System.out.println(productId);
        Product product1 = productService.getProductDetails(productId);
        ProductInfoResponseDTO productInfoResponseDTO= new ProductInfoResponseDTO();
        productInfoResponseDTO.setProductName(product1.getProductName());
        productInfoResponseDTO.setProductDescription(product1.getProductDescription());
        ResponseEntity responseEntity = new ResponseEntity(productInfoResponseDTO, HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("product")
    public ResponseEntity<?> deleteProduct(@RequestParam("productName") String productId) throws ProductNotExistsException {
        boolean b = productService.deleteProduct(productId);
        ResponseEntity responseEntity = new ResponseEntity(b, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("product/sellerlist")
    public ResponseEntity<?> getSellerListOfProduct(@RequestParam("productName") String productId) throws ProductNotExistsException {
        List<Seller> sellerList = productService.getSellerListOfProduct(productId);
        ResponseEntity responseEntity = new ResponseEntity(sellerList,HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("product/seller")
    public ResponseEntity<?> getSellerToProduct(@RequestParam("sellerEmail") String sellerId, @RequestParam("productName") String productName) throws ProductNotExistsException {
        List<Seller> sellerList = productService.getSellerListOfProduct(productName);
        Seller seller=null;
        for(Seller s:sellerList){
            if(s.getSellerId().equals(sellerId)){
                seller=s;
            }
        }
        ResponseEntity responseEntity = new ResponseEntity(seller,HttpStatus.OK);
        return responseEntity;
    }

    @PutMapping("product/seller")
    public ResponseEntity<?> addSellerToProduct(@RequestBody Seller seller,@RequestParam("productName") String productId) throws ProductNotExistsException {
        boolean b = productService.addSeller(productId,seller);

        ResponseEntity responseEntity = new ResponseEntity(b,HttpStatus.OK);
        return responseEntity;
    }

    @PutMapping("product")
    public ResponseEntity<?> updateSellerToProduct(@RequestBody Seller seller,@RequestParam("productName") String productId) throws ProductNotExistsException {
        boolean b = productService.updateSellerDetails(productId,seller);
        ResponseEntity responseEntity = new ResponseEntity(b,HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("product/seller")
    public ResponseEntity<?> deleteSellerToProduct(@RequestParam("sellerEmail") String sellerId,
                                                   @RequestParam("productName") String productId) throws ProductNotExistsException {
        System.out.println(productId+" + "+sellerId);
        boolean b = productService.deleteSeller(productId,sellerId);

        ResponseEntity responseEntity = new ResponseEntity(b,HttpStatus.OK);
        return responseEntity;
    }

}
