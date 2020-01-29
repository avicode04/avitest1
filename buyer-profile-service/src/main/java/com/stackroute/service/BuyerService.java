package com.stackroute.service;
import com.stackroute.domain.Buyer;
import com.stackroute.exceptions.BuyerAlreadyExistException;
import com.stackroute.exceptions.BuyerNotFoundException;
import com.stackroute.exceptions.DatabaseConnectivityFailedException;
import com.stackroute.kafka.BuyerDto;
import rx.Producer;

import java.util.List;


public interface BuyerService {

    /**
     * AbstractMethod to save a buyer
     */
    public Buyer saveBuyer(Buyer buyer) throws BuyerAlreadyExistException;



    /**
     * AbstractMethod to get a buyer by Id
     */
    public Buyer getBuyerById(String buyerEmail) throws BuyerNotFoundException;


    /**
     * AbstractMethod to get all buyers
     */
    public List<Buyer> getAllBuyer() throws DatabaseConnectivityFailedException;

    /**
     * AbstractMethod to delete buyer by Id
     *
     * @return
     */
    public Buyer deleteBuyerById(String BuyerEmail) throws BuyerNotFoundException;

    /**
     * AbstractMethod to delete all buyers
     */
    public boolean deleteAllBuyer() throws DatabaseConnectivityFailedException;

    /**
     * AbstractMethod to update comments of a track by its id
     */
    public Buyer updateBuyer(Buyer buyer) throws BuyerNotFoundException;

    /**
     * AbstractMethod to get track by Name
     */
    public List<Buyer> getBuyerByName(String buyerName) throws BuyerNotFoundException;

   // public boolean addToCart(Product product)

}
