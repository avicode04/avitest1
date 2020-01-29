package com.stackroute.controller;

import com.stackroute.domain.Book;
import com.stackroute.domain.Seller;
import com.stackroute.exception.ProductAlreadyExistsException;
import com.stackroute.exception.ProductNotExistsException;
import com.stackroute.kafka.BookRecomDto;
import com.stackroute.service.BookService;
import com.stackroute.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestController annotation is used to create Restful web services using Spring MVC
 */
@RestController

/**
 * RequestMapping annotation maps HTTP requests to handler methods
 */
@RequestMapping(value = "api/v1")
public class BookController {

    private BookService productService;

    private Producer producer;

    @Autowired
    public BookController(BookService productService, Producer producer) {
        this.productService = productService;
        this.producer = producer;
    }

    @PostMapping("book")
    public ResponseEntity<?> saveProduct(@RequestBody Book product) throws ProductAlreadyExistsException {
        Book product1 = productService.saveProduct(product);
        ResponseEntity responseEntity = new ResponseEntity(product1, HttpStatus.CREATED);
        BookRecomDto bookRecomDto=new BookRecomDto();
        bookRecomDto.setBookISBN(product.getBookISBN());
        bookRecomDto.setBookAuthor(product.getBookAuthor());
        bookRecomDto.setBookCategory(product.getBookCategory());
        bookRecomDto.setBookSubcategory(product.getBookSubcategory());
        bookRecomDto.setBookTitle(product.getBookTitle());
        bookRecomDto.setBookPublisher(product.getBookPublisher());
        bookRecomDto.setSellerEmail(product.getSellers().get(0).getSellerId());
        bookRecomDto.setBookPrice(product.getSellers().get(0).getProductPrice());
        bookRecomDto.setBookStock(product.getSellers().get(0).getProductStock());
        this.producer.sendBook(product1);
        this.producer.sendBookRecom(bookRecomDto);
        return responseEntity;
    }

    @GetMapping("book")
    public ResponseEntity<?> getProductDetails(@RequestParam("productId") String productId) throws ProductNotExistsException {
        Book product1 = productService.getProductDetails(productId);
        ResponseEntity responseEntity = new ResponseEntity(product1, HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("book")
    public ResponseEntity<?> deleteProduct(@RequestParam("productId") String productId) throws ProductNotExistsException {
        boolean b = productService.deleteProduct(productId);
        ResponseEntity responseEntity = new ResponseEntity(b, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("book/sellerlist")
    public ResponseEntity<?> getSellerListOfProduct(@RequestParam("productId") String productId) throws ProductNotExistsException {
        List<Seller> sellerList = productService.getSellerListOfProduct(productId);
        ResponseEntity responseEntity = new ResponseEntity(sellerList,HttpStatus.OK);
        return responseEntity;
    }

    @PutMapping("book/seller")
    public ResponseEntity<?> addSellerToProduct(@RequestBody Seller seller,@RequestParam("productId") String productId) throws ProductNotExistsException {
        boolean b = productService.addSeller(productId,seller);
        ResponseEntity responseEntity = new ResponseEntity(b,HttpStatus.OK);
        return responseEntity;
    }

    @PutMapping("book")
    public ResponseEntity<?> updateSellerToProduct(@RequestBody Seller seller,@RequestParam("productId") String productId) throws ProductNotExistsException {
        boolean b = productService.updateSellerDetails(productId,seller);
        ResponseEntity responseEntity = new ResponseEntity(b,HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("book/seller")
    public ResponseEntity<?> deleteSellerToProduct(@RequestParam("sellerId") String sellerId,
                                                   @RequestParam("productId") String productId) throws ProductNotExistsException {
        System.out.println(productId+" + "+sellerId);
        boolean b = productService.deleteSeller(productId,sellerId);
        ResponseEntity responseEntity = new ResponseEntity(b,HttpStatus.OK);
        return responseEntity;
    }

}
