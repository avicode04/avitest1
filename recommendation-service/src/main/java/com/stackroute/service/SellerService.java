package com.stackroute.service;


import com.stackroute.domain.Product;
import com.stackroute.domain.Products;
import com.stackroute.domain.Seller;
import com.stackroute.domain.Sellers;
import com.stackroute.kafka.SellerRecomDto;
import com.stackroute.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {
    @Autowired
    SellerRepository sellerRepository;

    public List<Products> getAllSoldProducts(Long id) {
        return sellerRepository.getAllSoldProducts(id);
    }
    public List<Products> getAllInventoryProducts(Long id) {
        return  sellerRepository.getAllInventoryProducts(id);
    }
    public List<Sellers> findAll() {
        return (List<Sellers>) sellerRepository.findAll();
    }

    public Sellers saveSeller(Sellers sellers){
        Sellers savedSellers =sellerRepository.save(sellers);
        return savedSellers;
    }

    public void sellProduct(Long sellerId,Long productId){
        sellerRepository.soldProduct(sellerId,productId);
    }
    public void ProductAddToInventory(Long sellerId,Long productId){
        sellerRepository.AddToInventoryProduct(sellerId,productId);
    }
    public void ProductDeleteFromInventory(Long sellerId,Long productId){
        sellerRepository.DeleteFromInventoryProduct(sellerId,productId);
    }
    public Sellers getIdByEmailId(String emailId){
        return sellerRepository.findByEmailId(emailId);
    }

    @KafkaListener(topics = "sellerrecom", groupId = "seller-id",containerFactory = "kafkaListenerContainerFactory1")
    public void consumeSeller(@Payload SellerRecomDto sellerRecomDto){
        System.out.println(sellerRecomDto.toString());
        List<Product> productList=sellerRecomDto.getSellerProducts();

        Sellers newSeller = new Sellers();
        newSeller.setSellerAddress(sellerRecomDto.getSellerAddress());
        newSeller.setSellerEmail(sellerRecomDto.getSellerEmail());
        newSeller.setSellerGstIn(sellerRecomDto.getSellerGstIn());
        newSeller.setSellerName(sellerRecomDto.getSellerName());
        newSeller.setSellerPhone(sellerRecomDto.getSellerPhone());
        //newSeller.setSellerProducts(sellerRecomDto.getSellerProducts());
        newSeller.setSellerRating(sellerRecomDto.getSellerRating());
        Sellers savedSeller=this.saveSeller(newSeller);
        for(int i=0;i<productList.size();i++){
            sellerRepository.AddProduct(savedSeller.getId(),productList.get(i).getProductName(),productList.get(i).getProductQuantity(),productList.get(i).getProductPrice().doubleValue());
        }
    }
}
