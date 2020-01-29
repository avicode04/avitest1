package com.stackroute.service;

import com.stackroute.domain.Product;
import com.stackroute.domain.Products;
import com.stackroute.domain.Seller;
import com.stackroute.exceptions.ProductNotFoundException;
import com.stackroute.kafka.NewSellerDto;
import com.stackroute.kafka.ProductRecomDto;
import com.stackroute.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Products> getAll() {
        return (List<Products>) productRepository.findAll();
    }
    public List<Products> productHavingSamePriceRange(Long productId) {
        List<Products> products=productRepository.getProductSamePriceRange(productId);
        products.addAll(productRepository.getProductSameBrand(productId));
        products.addAll(productRepository.getRelatedProduct(productId));
        return products;
    }

    public Products saveProduct(Products products){
        Products savedProducts =productRepository.save(products);
        //productRepository.postProductSamePriceRange(savedProducts.getId());
        productRepository.postProductSameBrand(savedProducts.getId());
        productRepository.postProductCategory(savedProducts.getId());
        return savedProducts;
    }

    public Products deleteProductById(Long id) throws ProductNotFoundException {
        /**Throw productNotFoundException if product we want to delete is not found*/
        if (productRepository.existsById(id)) {
            Products deletedProducts = productRepository.findById(id).get();
            productRepository.deleteById(id);
            return deletedProducts;
        } else {
            throw new ProductNotFoundException("product you want to delete is not found");
        }
    }

    public Products updateProduct(Products products) throws ProductNotFoundException {
        /**Throw productNotFoundException if product we want to update is not found*/
        if (productRepository.existsById(products.getId())) {
            Products getProducts = productRepository.findById(products.getId()).get();
            getProducts.setProductName(products.getProductName());
            //getProducts.setSellers(products.getSellers());
            getProducts.setProductDescription(products.getProductDescription());
            return productRepository.save(getProducts);
        } else {
            throw new ProductNotFoundException("product you want to update is not found");
        }
    }

    @KafkaListener(topics = "product-recomm-Info", groupId = "product-id",containerFactory = "kafkaListenerContainerFactory1")
    public void consumeProduct(@Payload ProductRecomDto productRecomDTO){
        System.out.println(productRecomDTO.toString());
        Products newProducts = new Products();
        newProducts.setProductName(productRecomDTO.getProductName());
        newProducts.setProductCategory(productRecomDTO.getProductCategory());
        newProducts.setProductSubCategory(productRecomDTO.getProductSubCategory());
        newProducts.setProductCategory(productRecomDTO.getProductCategory());
        newProducts.setProductDescription(productRecomDTO.getProductDescription());
        newProducts.setProductBrandName(productRecomDTO.getProductBrandName());
        //newProducts.setSellers(productRecomDTO.getSellers());
        Products savedProduct=this.saveProduct(newProducts);
        System.out.println(savedProduct);
        productRepository.AddSellers(productRecomDTO.getSellerEmail(),productRecomDTO.getProductName(),productRecomDTO.getProductStock(),productRecomDTO.getProductPrice());

    }

    @KafkaListener(topics = "product-new-seller", groupId = "new-seller-id",containerFactory = "kafkaListenerContainerFactory1")
    public void consumeProduct(@Payload NewSellerDto newSellerDto){
        System.out.println(newSellerDto.toString());
        Products newProducts = new Products();
        productRepository.AddNewSellers(newSellerDto.getSellerEmail(),newSellerDto.getProductName(),newSellerDto.getProductStock(),newSellerDto.getProductPrice());

    }

}
