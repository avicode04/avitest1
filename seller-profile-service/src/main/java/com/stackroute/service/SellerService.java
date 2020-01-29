package com.stackroute.service;

import com.stackroute.exceptions.DatabaseConnectivityFailedException;
import com.stackroute.exceptions.SellerNotFoundException;
import com.stackroute.domain.Seller;
import com.stackroute.exceptions.SellerAlreadyExistsException;
import com.stackroute.kafka.SellerDto;

import java.util.List;

public interface SellerService {
    /**
     * AbstractMethod to save a seller
     */
    public Seller saveSeller(Seller seller) throws SellerAlreadyExistsException;

    /**
     * AbstractMethod to get a seller by Id
     */
    public Seller getSellerById(String sellerEmail) throws SellerNotFoundException;

    /**
     * AbstractMethod to get all sellers
     */
    public List<Seller> getAllSeller() throws DatabaseConnectivityFailedException;

    /**
     * AbstractMethod to delete seller by Id
     *
     * @return
     */
    public Seller deleteSellerById(String sellerEmail) throws SellerNotFoundException;

    /**
     * AbstractMethod to delete all sellers
     */
    public boolean deleteAllSeller() throws DatabaseConnectivityFailedException;

    /**
     * AbstractMethod to update comments of a track by its id
     */
    public Seller updateSeller(Seller seller) throws SellerNotFoundException;

    /**
     * AbstractMethod to get track by Name
     */
    public List<Seller> getSellerByName(String sellerName) throws SellerNotFoundException;

}
