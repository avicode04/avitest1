package com.stackroute.service;

import com.stackroute.domain.Product;
import com.stackroute.exceptions.DatabaseConnectivityFailedException;
import com.stackroute.exceptions.SellerNotFoundException;
import com.stackroute.kafka.ProductDto;
import com.stackroute.kafka.SellerDto;
import com.stackroute.repository.SellerRepository;
import com.stackroute.domain.Seller;
import com.stackroute.exceptions.SellerAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements SellerService{

    private SellerRepository sellerRepository;

    /**
     * Constructor based Dependency injection to inject SellerRepository here
     */
    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    /**
     * Implementation of saveSeller method
     */
    @Override
    public Seller saveSeller(Seller seller) throws SellerAlreadyExistsException {
        /**Throw SellerAlreadyExistsException if track already exists*/
        if(sellerRepository.existsById(seller.getSellerEmail())){
            throw new SellerAlreadyExistsException();
        }

        Seller savedSeller = sellerRepository.save(seller);

        return savedSeller;
    }
    /**
     * Implementation of getSellerById method
     */

    @Override
    public Seller getSellerById(String sellerEmail) throws SellerNotFoundException {
        /**Throw SellerNotFoundException if Seller we want to get is not found*/
        if(!sellerRepository.existsById(sellerEmail)){
            throw new SellerNotFoundException();
        }
        Seller retrievedSellerById = sellerRepository.findById(sellerEmail).get();
        return retrievedSellerById;

    }

    /*Implementation of getAllSeller method*/

    @Override
    public List<Seller> getAllSeller() throws DatabaseConnectivityFailedException {
        /**Throws DatabaseConnectivityFailedException if Database connection issue happens*/

        return sellerRepository.findAll();
    }

    /*implementation of deleteSellerById method*/

    @Override
    public Seller deleteSellerById(String sellerEmail) throws SellerNotFoundException {
        /* Throws SellerNotFoundException if seller to be deleted is not found*/
        if(!sellerRepository.existsById(sellerEmail)){
            throw new SellerNotFoundException();
        }
        Seller deletedSeller = sellerRepository.findById(sellerEmail).get();
        sellerRepository.deleteById(sellerEmail);
        return deletedSeller;
    }

    /*implementation of deleteAllSeller method*/

    @Override
    public boolean deleteAllSeller() throws DatabaseConnectivityFailedException {
        /*throws DatabaseConnectivityFailedException if database is not connected*/
        if(sellerRepository.findAll().isEmpty()){
            return false;
        }
        sellerRepository.deleteAll();
        return true;
    }

    /*implementation of updateSeller method*/

    @Override
    public Seller updateSeller(Seller seller) throws SellerNotFoundException {
        /*throws SellerNotFoundException if seller to be updated is not found*/
        if(!sellerRepository.existsById(seller.getSellerEmail())){
            throw new SellerNotFoundException();
        }
        sellerRepository.save(seller);
        Seller updatedSeller = sellerRepository.findById(seller.getSellerEmail()).get();

        return updatedSeller;
    }

    /*implementation of getSellerByName method*/

    @Override
    public List<Seller> getSellerByName(String sellerName) throws SellerNotFoundException {
        /*throws SellerNotFoundException exception if seller is not found*/
        if(sellerRepository.findBySellerName(sellerName).isEmpty()){
            throw new SellerNotFoundException();
        }
        return sellerRepository.findBySellerName(sellerName);
    }

    @KafkaListener(topics = "product-Info", groupId = "product-id", containerFactory = "kafkaListenerContainerFactory")
    public void productDto(@Payload ProductDto productDto){
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setProductBrandName(productDto.getProductBrandName());
        product.setProductCategory(productDto.getProductCategory());
        product.setProductDescription(productDto.getProductDescription());
        product.setProductImage(productDto.getProductImage());
        product.setProductPrice(productDto.getProductPrice());
        product.setProductStock(productDto.getProductStock());
        product.setProductSubCategory(productDto.getProductSubCategory());
        Seller seller = sellerRepository.findById(productDto.getSellerEmail()).get();
        seller.addInProduct(product);
        sellerRepository.save(seller);

    }
}
