package com.stackroute.service;

import com.stackroute.domain.Buyer;
import com.stackroute.domain.Products;
import com.stackroute.exceptions.BuyerNotFoundException;
import com.stackroute.kafka.BuyerRecomDto;
import com.stackroute.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuyerService {


    @Autowired
    BuyerRepository buyerRepository;

    @Autowired
    ProductService productService;

    public List<Products> getAllBuyedProducts(Long id) {
        return buyerRepository.getAllBuyedProducts(id);
    }
    public List<Products> getAllCartProducts(Long id) {
        return  buyerRepository.getAllCartProducts(id);
    }
    public List<Buyer> findAll() {
        return (List<Buyer>) buyerRepository.findAll();
    }

    public Buyer saveBuyer(Buyer buyer){
        Buyer savedBuyer=buyerRepository.save(buyer);
        return savedBuyer;
    }

    public void buyProduct(Long buyerId,Long productId){
        buyerRepository.buysProduct(buyerId,productId);
    }
    public void ProductAddToCart(Long buyerId,Long productId){
        buyerRepository.AddToCartProduct(buyerId,productId);
    }
    public Buyer deleteBuyerById(Long id) throws BuyerNotFoundException {
        /**Throw productNotFoundException if product we want to delete is not found*/
        if (buyerRepository.existsById(id)) {
            Buyer deletedBuyer = buyerRepository.findById(id).get();
            buyerRepository.deleteById(id);
            return deletedBuyer;
        } else {
            throw new BuyerNotFoundException("product you want to delete is not found");
        }
    }

    public Buyer updateBuyer(Buyer buyer) throws BuyerNotFoundException {
        /**Throw buyerNotFoundException if product we want to update is not found*/
        if (buyerRepository.existsById(buyer.getId())) {
            Buyer getBuyer = buyerRepository.findById(buyer.getId()).get();
            getBuyer.setBuyerName(buyer.getBuyerName());
            getBuyer.setBuyerDob(buyer.getBuyerDob());
            getBuyer.setBuyerEmail(buyer.getBuyerEmail());
            return buyerRepository.save(getBuyer);
        } else {
            throw new BuyerNotFoundException("buyer you want to update is not found");
        }
    }
    public void ProductDeleteFromCart(Long buyerId,Long productId){
        buyerRepository.DeleteFromCartProduct(buyerId,productId);
    }
    public List<Products> getAllRecommendedProducts(Long buyerId){
        List<Products> relatedProducts =buyerRepository.getAllBuyedProducts(buyerId);
        relatedProducts.addAll(buyerRepository.getAllCartProducts(buyerId));
        List<Products> recommendedProducts =new ArrayList<>();

        for(int i = 0; i< relatedProducts.size(); i++){
            recommendedProducts.addAll(productService.productHavingSamePriceRange(relatedProducts.get(i).getId()));
        }
        return recommendedProducts;
    }

    public Buyer getIdByEmailId(String emailId){
        return buyerRepository.findByEmailId(emailId);
    }

    @KafkaListener(topics = "buyerrecom", groupId = "buyer-id",containerFactory = "kafkaListenerContainerFactory")
    public void consumeBuyer(@Payload BuyerRecomDto buyerRecomDto){
        System.out.println(buyerRecomDto.toString());
        Buyer newBuyer = new Buyer();
        newBuyer.setBuyerName(buyerRecomDto.getBuyerName());
        newBuyer.setBuyerEmail(buyerRecomDto.getBuyerEmail());
        newBuyer.setBuyerDob(buyerRecomDto.getBuyerDob());
        newBuyer.setBuyerGender(buyerRecomDto.getBuyerGender());
        newBuyer.setBuyerHomeAddress(buyerRecomDto.getBuyerHomeAddress());
        newBuyer.setBuyerOfficeAddress(buyerRecomDto.getBuyerOfficeAddress());
        newBuyer.setBuyerPhone(buyerRecomDto.getBuyerPhone());
        buyerRepository.save(newBuyer);
    }
}
