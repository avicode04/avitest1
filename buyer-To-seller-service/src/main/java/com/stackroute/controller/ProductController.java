package com.stackroute.controller;

import com.stackroute.domain.Order;
import com.stackroute.domain.Product;
import com.stackroute.domain.Seller;
import com.stackroute.exceptions.ProductAlreadyExistsException;
import com.stackroute.exceptions.ProductNotFoundException;
import com.stackroute.repository.ProductRepository;
import com.stackroute.service.OrderService;
import com.stackroute.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    @Autowired
    public ProductService productService;
    @Autowired
    public OrderService orderService;
    @Autowired
    public ProductRepository productRepository;

    /**
     * Constructor based Dependency injection to inject TrackService into controller
     */
    @Autowired
    public ProductController(ProductService productService,OrderService orderService) {
        this.productService = productService;
        this.orderService=orderService;
    }

    /**
     * PostMapping Annotation for mapping HTTP POST requests onto specific handler methods.
     */
    @PostMapping("product")
    public ResponseEntity<?> saveProduct(@RequestBody Product product) throws Exception, ProductAlreadyExistsException {
        Product savedProduct = productRepository.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    /**
     * GetMapping Annotation for mapping HTTP GET requests onto specific handler methods.
     */
    @GetMapping("product/{id}")
    public ResponseEntity<?> getProductById(@PathVariable String id) throws Exception, ProductNotFoundException {

        Product retrieveProductById = productService.getProductById(id);
        return new ResponseEntity<>(retrieveProductById, HttpStatus.OK);
    }

    /**
     * GetMapping Annotation for mapping HTTP GET requests onto specific product name.
     */
    @GetMapping("product/name/{productName}")
    public ResponseEntity<?> getProductsByName(@PathVariable String productName) throws Exception, ProductNotFoundException {

        Product retrieveProductByName = productService.getProductByName(productName);
        return new ResponseEntity<>(retrieveProductByName, HttpStatus.OK);
    }

    /**
     * GetMapping Annotation for mapping HTTP GET all requests.
     */
    @GetMapping("products")
    public ResponseEntity<?> getAllProducts() throws Exception {

        List<Product> retrieveProducts = productService.getAllProducts();
        return new ResponseEntity<>(retrieveProducts, HttpStatus.OK);
    }

    /**
     * DeleteMapping Annotation for mapping HTTP Delete requests onto specific handler methods.
     */
    @DeleteMapping("product/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable String id) throws Exception, ProductNotFoundException {
        return new ResponseEntity<>(productService.deleteProductById(id), HttpStatus.OK);
    }

    /**
     * GetMapping Annotation for mapping HTTP DELETE all requests onto specific handler methods.
     */
    @DeleteMapping("products")
    public ResponseEntity<?> deleteAllProducts() throws Exception {
        return new ResponseEntity<>(productService.deleteAllProducts(), HttpStatus.OK);
    }

    /**
     * PutMapping Annotation for mapping HTTP PuT requests onto specific handler methods.
     */
    @PutMapping("product")
    public ResponseEntity<?> UpdateProduct(@RequestBody Product product) throws Exception, ProductNotFoundException {

        Product updatedProduct = productService.updateProduct(product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    /**
     * GetMapping Annotation for mapping HTTP GET all sellers from products.
     */
   @GetMapping("products/{productName}")
    public ResponseEntity<?> getProductByName(@PathVariable String productName) throws Exception, ProductNotFoundException {

        List<Seller> retrieveSellerByProduct = productService.getSellerByProductName(productName);
        return new ResponseEntity<>(retrieveSellerByProduct, HttpStatus.OK);
    }

    /**
     * GetMapping Annotation for buying HTTP GET specific seller from product.
     */
    @GetMapping("products/buy/{productName}/{sellerId}/")
    public ResponseEntity<?> buyProduct(@PathVariable String productName,@PathVariable String sellerId) throws Exception, ProductNotFoundException {

        Product buyedProduct=productService.buyProduct(productName,sellerId);
        return new ResponseEntity<>(buyedProduct, HttpStatus.OK);
    }
    /**
     * GetMapping Annotation for find HTTP GET specific sellers from products.
     */
    @GetMapping("products/{productName}/{sellerId}/")
    public ResponseEntity<?> productWithSellerId(@PathVariable String productName,@PathVariable String sellerId) throws Exception, ProductNotFoundException {

        Seller seller=productService.productWithSellerId(productName,sellerId);
        return new ResponseEntity(seller, HttpStatus.OK);
    }

    /**
     * GetMapping Annotation for mapping HTTP GET all orders.
     */
    @GetMapping("orders")
    public ResponseEntity<?> getAllOrders() throws Exception {

        List<Order> retrieveOrders = orderService.getAllOrders();
        return new ResponseEntity<>(retrieveOrders, HttpStatus.OK);
    }
    /**
     * PostMapping Annotation for mapping HTTP Post an order in orders.
     */
    @PostMapping("order")
    public ResponseEntity<?> saveOrder(@RequestBody Order order) throws Exception {

        Order savedOrder = orderService.saveOrder(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }
    /**
     * Scheduling at per 15 mins.. to post order to order-service.
     */
    @Scheduled(fixedRate = 900000)
    //@GetMapping("mappedOrders")
    public ResponseEntity<?> getAllMappedOrders() throws Exception {
        List<Order> retrieveOrders = orderService.mappedSeller();
        return new ResponseEntity<>(retrieveOrders, HttpStatus.OK);
    }

    /**
     * GetMapping Annotation for mapping HTTP GET all mappedOrders.
     */
    @GetMapping("mappedOrders")
    public ResponseEntity<?> getAllOrderMapped() throws Exception {
        List<Order> retrieveOrders = orderService.mappedSeller();
        return new ResponseEntity<>(retrieveOrders, HttpStatus.OK);
    }

    /**
     * DeleteMapping Annotation for mapping HTTP DELETE an order from orders.
     */
    @DeleteMapping("order/{id}")
    public ResponseEntity<?> deleteOrderById(@PathVariable String id) throws Exception, ProductNotFoundException {
        return new ResponseEntity<>(orderService.deleteOrderById(id), HttpStatus.OK);
    }


}
