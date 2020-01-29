package com.stackroute.service;

import com.stackroute.domain.Product;
import com.stackroute.domain.Seller;
import com.stackroute.exception.ProductAlreadyExistsException;
import com.stackroute.exception.ProductNotExistsException;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public Product getProductDetails(String productId) throws ProductNotExistsException;

    public Product saveProduct(Product product) throws ProductAlreadyExistsException;

    public boolean deleteProduct(String productId) throws ProductNotExistsException;

    public Product updateProduct(Product product) throws ProductNotExistsException;

    public List<Product> getProductListOfSeller(String sellerId);

    public List<Seller> getSellerListOfProduct(String productId) throws ProductNotExistsException;

    public Seller deleteAllProductOfSeller(Seller seller);

    public boolean addSeller(String productId, Seller seller) throws ProductNotExistsException;

    public boolean updateSellerDetails(String productId,Seller seller) throws ProductNotExistsException;

    public boolean deleteSeller(String productId,String sellerId) throws ProductNotExistsException;

//    public Product findByProductName(String productName) throws ProductNotExistsException;

}
