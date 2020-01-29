package com.stackroute.service;

import com.stackroute.domain.Book;
import com.stackroute.domain.Product;
import com.stackroute.domain.Seller;
import com.stackroute.exception.ProductAlreadyExistsException;
import com.stackroute.exception.ProductNotExistsException;

import java.util.List;

public interface BookService {

    public Book getProductDetails(String productId) throws ProductNotExistsException;

    public Book saveProduct(Book product) throws ProductAlreadyExistsException;

    public boolean deleteProduct(String productId) throws ProductNotExistsException;

    public Book updateProduct(Book product) throws ProductNotExistsException;

    public List<Book> getProductListOfSeller(String sellerId);

    public List<Seller> getSellerListOfProduct(String productId) throws ProductNotExistsException;

    public Seller deleteAllProductOfSeller(Seller seller);

    public boolean addSeller(String productId, Seller seller) throws ProductNotExistsException;

    public boolean updateSellerDetails(String productId, Seller seller) throws ProductNotExistsException;

    public boolean deleteSeller(String productId,String sellerId) throws ProductNotExistsException;

//    public Book findByProductName(String productName) throws ProductNotExistsException;

}
