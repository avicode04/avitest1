package com.stackroute.service;

import com.stackroute.domain.Product;
import com.stackroute.domain.Seller;
import com.stackroute.exceptions.ProductAlreadyExistsException;
import com.stackroute.exceptions.ProductNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    /**
     * AbstractMethod to save a product
     */
    public Product saveProduct(Product product) throws ProductAlreadyExistsException;

    /**
     * AbstractMethod to get a product by Id
     */
    public Product getProductById(String id) throws ProductNotFoundException;

    /**
     * AbstractMethod to get all products
     */
    public List<Product> getAllProducts() throws Exception;

    /**
     * AbstractMethod to delete product by Id
     *
     * @return
     */
    public Optional<Product> deleteProductById(String id) throws ProductNotFoundException;

    /**
     * AbstractMethod to delete all products
     */
    public boolean deleteAllProducts() throws Exception;

    /**
     * AbstractMethod to update comments of a product by its id
     */
    public Product updateProduct(Product product) throws ProductNotFoundException;

    /**
     * AbstractMethod to get product by Name
     */
    public List<Seller> getSellerByProductName(String productName) throws ProductNotFoundException;

    /**
     * AbstractMethod to buy product by Name
     */
    public void buyProduct(String productName,String sellerId) throws ProductNotFoundException;

    public Seller productWithSellerId(String productName,String sellerId) throws ProductNotFoundException;

    public Product getProductByName(String name) throws ProductNotFoundException;

}
