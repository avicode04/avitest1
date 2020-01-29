package com.stackroute.service;
import com.stackroute.domain.Buyer;
import com.stackroute.exceptions.BuyerAlreadyExistException;
import com.stackroute.exceptions.BuyerNotFoundException;
import com.stackroute.exceptions.DatabaseConnectivityFailedException;
import com.stackroute.kafka.BuyerDto;
import com.stackroute.kafka.OrderedProductsDto;
import com.stackroute.kafka.ReturnedProductsDto;
import com.stackroute.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyerServiceImpl implements BuyerService {

            private BuyerRepository buyerRepository;
            /**
             * Constructor based Dependency injection to inject BuyerRepository here
             */
            @Autowired
            public BuyerServiceImpl(BuyerRepository buyerRepository) {
                this.buyerRepository = buyerRepository;
            }

            /**
             * Implementation of saveBuyer method
             */
            @Override
            public Buyer saveBuyer(Buyer buyer) throws BuyerAlreadyExistException {
                /**Throw SellerAlreadyExistsException if track already exists*/
                if(buyerRepository.existsById(buyer.getBuyerEmail())){
                    throw new BuyerAlreadyExistException();
                }

                Buyer savedBuyer = buyerRepository.save(buyer);
                return savedBuyer;
            }

            /**
             * Implementation of getBuyerById method
             */

            @Override
            public Buyer getBuyerById(String sellerEmail) throws BuyerNotFoundException {
                /**Throw BuyerNotFoundException if desired Buyer is not found*/
                if(!buyerRepository.existsById(sellerEmail)){
                    throw new BuyerNotFoundException();
                }
                Buyer retrievedBuyerById = buyerRepository.findById(sellerEmail).get();
                return retrievedBuyerById;

            }

            /*Implementation of getAllBuyer method*/

            @Override
            public List<Buyer> getAllBuyer() throws DatabaseConnectivityFailedException {
                /**Throws DatabaseConnectivityFailedException if Database connection issue happens*/

                return buyerRepository.findAll();
            }

            /*implementation of deleteBuyerById method*/

            @Override
            public Buyer deleteBuyerById(String buyerEmail) throws BuyerNotFoundException {
                /* Throws BuyerNotFoundException if buyer to be deleted is not found*/
                if(!buyerRepository.existsById(buyerEmail)){
                    throw new BuyerNotFoundException();
                }
                Buyer deletedBuyer = buyerRepository.findById(buyerEmail).get();
                buyerRepository.deleteById(buyerEmail);
                return deletedBuyer;
            }

            /*implementation of deleteAllBuyer method*/

            @Override
            public boolean deleteAllBuyer() throws DatabaseConnectivityFailedException {
                /*throws DatabaseConnectivityFailedException if database is not connected*/
                if(buyerRepository.findAll().isEmpty()){
                    return false;
                }
                buyerRepository.deleteAll();
                return true;
            }

            /*implementation of updateBuyer method*/

            @Override
            public Buyer updateBuyer(Buyer buyer) throws BuyerNotFoundException {
                /*throws SellerNotFoundException if seller to be updated is not found*/
                if(!buyerRepository.existsById(buyer.getBuyerEmail())){
                    throw new BuyerNotFoundException();
                }
                buyerRepository.save(buyer);
                Buyer updatedBuyer = buyerRepository.findById(buyer.getBuyerEmail()).get();

                return updatedBuyer;
            }

            /*implementation of getBuyerByName method*/

            @Override
            public List<Buyer> getBuyerByName(String buyerName) throws BuyerNotFoundException {
                /*throws SellerNotFoundException exception if seller is not found*/
                if(buyerRepository.findByBuyerName(buyerName).isEmpty()){
                    throw new BuyerNotFoundException();
                }
                return buyerRepository.findByBuyerName(buyerName);

            }


            /*implementation of kafka listener of ordered-products*/

//            @KafkaListener(topics = "  ", groupId = "ordered-products-id",containerFactory = "kafkaListenerContainerFactory")
//            public void consumeOrderedProducts(OrderedProductsDto orderedProductsDto){
//
//
//            }


            /*implementation of kafka listener of returned-products*/

            // @KafkaListener(topics = "  ", groupId = "returned-products-id",containerFactory = "kafkaListenerContainerFactory")
            // public void consumeReturned(ReturnedProductsDto returnedProductsDto){


            // }



}
