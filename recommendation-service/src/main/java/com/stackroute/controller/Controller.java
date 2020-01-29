package com.stackroute.controller;

import com.stackroute.domain.*;
import com.stackroute.exceptions.BookNotFoundException;
import com.stackroute.exceptions.BuyerNotFoundException;
import com.stackroute.exceptions.ProductNotFoundException;
import com.stackroute.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/neo4j")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Controller {

    @Autowired
    BuyerService buyerService;
    @Autowired
    ProductService productService;
    @Autowired
    SellerService sellerService;
    @Autowired
    CategoriesService categoriesService;
    @Autowired
    SubCategoriesService subCategoriesService;
    @Autowired
    BookService bookService;


    @GetMapping("buyers")
    public ResponseEntity getAllBuyer() {
        return new ResponseEntity<>(buyerService.findAll(), HttpStatus.OK);
    }

    @PostMapping("buyer")
    public ResponseEntity saveBuyer(@RequestBody Buyer buyer){
        ResponseEntity responseEntity;
        buyerService.saveBuyer(buyer);
        responseEntity = new ResponseEntity<String>("successfully created", HttpStatus.CREATED);
        return responseEntity;
    }
    @PutMapping("buyer")
    public ResponseEntity<?> UpdateBuyer(@RequestBody Buyer buyer) throws Exception, BuyerNotFoundException {

        Buyer updatedBuyer = buyerService.updateBuyer(buyer);
        return new ResponseEntity<>(updatedBuyer, HttpStatus.OK);
    }

    @DeleteMapping("buyer/{id}")
    public ResponseEntity<?> deleteBuyerById(@PathVariable Long id) throws Exception, BuyerNotFoundException {
        return new ResponseEntity<>(buyerService.deleteBuyerById(id), HttpStatus.OK);
    }

    @GetMapping("buyer/products/buy/{buyerId}")
    public ResponseEntity getBuyerProduct(@PathVariable Long buyerId) {
        return new ResponseEntity<>(buyerService.getAllBuyedProducts(buyerId), HttpStatus.OK);
    }

    @GetMapping("buyer/products/cart/{buyerId}")
    public ResponseEntity getBuyerCartProduct(@PathVariable Long buyerId) {
        return new ResponseEntity<>(buyerService.getAllCartProducts(buyerId), HttpStatus.OK);
    }
    @GetMapping("buyer/products/recommendation/{buyerId}")
    public ResponseEntity getBuyerRecommendedProduct(@PathVariable Long buyerId) {
        return new ResponseEntity<>(buyerService.getAllRecommendedProducts(buyerId), HttpStatus.OK);
    }

    @GetMapping("buyer/{buyerEmail}/")
    public ResponseEntity<?> getBuyerById(@PathVariable String buyerEmail){
        ResponseEntity responseEntity;
        System.out.println(buyerEmail);
        responseEntity = new ResponseEntity<>(buyerService.getIdByEmailId(buyerEmail),HttpStatus.OK);
        return responseEntity;
    }




    @PostMapping("buyer/Buy/{buyerId}/{productId}")
    public ResponseEntity buyProduct(@PathVariable Long buyerId,@PathVariable Long productId){
        ResponseEntity responseEntity;
        buyerService.buyProduct(buyerId,productId);
        responseEntity = new ResponseEntity<String>("successfully created", HttpStatus.CREATED);
        return responseEntity;
    }

    @PostMapping("buyer/Cart/{buyerId}/{productId}")
    public ResponseEntity addProductToCart(@PathVariable Long buyerId,@PathVariable Long productId){
        ResponseEntity responseEntity;
        buyerService.ProductAddToCart(buyerId,productId);
        responseEntity = new ResponseEntity<String>("successfully created", HttpStatus.CREATED);
        return responseEntity;
    }

    @PostMapping("buyer/Cart/delete/{buyerId}/{productId}")
    public ResponseEntity deleteProductFromCart(@PathVariable Long buyerId,@PathVariable Long productId){
        ResponseEntity responseEntity;
        buyerService.ProductDeleteFromCart(buyerId,productId);
        responseEntity = new ResponseEntity<String>("successfully deleted", HttpStatus.OK);
        return responseEntity;
    }


    @GetMapping("products")
    public ResponseEntity getAllProducts() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @PostMapping("product")
    public ResponseEntity saveProduct(@RequestBody Products products){
        ResponseEntity responseEntity;
        productService.saveProduct(products);
        responseEntity = new ResponseEntity<String>("successfully created", HttpStatus.CREATED);
        return responseEntity;
    }

    @PutMapping("product")
    public ResponseEntity<?> UpdateProduct(@RequestBody Products products) throws Exception, ProductNotFoundException {

        Products updatedProducts = productService.updateProduct(products);
        return new ResponseEntity<>(updatedProducts, HttpStatus.OK);
    }

    @DeleteMapping("product/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable Long id) throws Exception, ProductNotFoundException {
        return new ResponseEntity<>(productService.deleteProductById(id), HttpStatus.OK);
    }

    @GetMapping("products/{productId}")
    public ResponseEntity productHavingSamePrice(@PathVariable Long productId){
        ResponseEntity responseEntity;
        responseEntity = new ResponseEntity<>(productService.productHavingSamePriceRange(productId), HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("sellers")
    public ResponseEntity getAllSellers() {
        return new ResponseEntity<>(sellerService.findAll(), HttpStatus.OK);
    }

    @PostMapping("seller")
    public ResponseEntity saveSeller(@RequestBody Sellers sellers){
        ResponseEntity responseEntity;
        sellerService.saveSeller(sellers);
        responseEntity = new ResponseEntity<String>("successfully created", HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping("seller/products/sold/{sellerId}")
    public ResponseEntity getSoldProduct(@PathVariable Long sellerId) {
        return new ResponseEntity<>(sellerService.getAllSoldProducts(sellerId), HttpStatus.OK);
    }

    @GetMapping("seller/products/inventory/{sellerId}")
    public ResponseEntity getSellerInventoryProduct(@PathVariable Long sellerId) {
        return new ResponseEntity<>(sellerService.getAllInventoryProducts(sellerId), HttpStatus.OK);
    }


    @GetMapping("seller/{sellerEmail}/")
    public ResponseEntity<?> getSellerById(@PathVariable String sellerEmail){
        ResponseEntity responseEntity;
        System.out.println(sellerEmail);
        responseEntity = new ResponseEntity<>(sellerService.getIdByEmailId(sellerEmail),HttpStatus.OK);
        return responseEntity;
    }




    @PostMapping("seller/Sell/{sellerId}/{productId}")
    public ResponseEntity sellProduct(@PathVariable Long sellerId,@PathVariable Long productId){
        ResponseEntity responseEntity;
        sellerService.sellProduct(sellerId,productId);
        responseEntity = new ResponseEntity<>("successfully created", HttpStatus.CREATED);
        return responseEntity;
    }

    @PostMapping("seller/Inventory/{sellerId}/{productId}")
    public ResponseEntity addProductToInventory(@PathVariable Long sellerId,@PathVariable Long productId){
        ResponseEntity responseEntity;
        sellerService.ProductAddToInventory(sellerId,productId);
        responseEntity = new ResponseEntity<String>("successfully created", HttpStatus.CREATED);
        return responseEntity;
    }

    @PostMapping("seller/Inventory/delete/{sellerId}/{productId}")
    public ResponseEntity deleteProductFromInventory(@PathVariable Long sellerId,@PathVariable Long productId){
        ResponseEntity responseEntity;
        sellerService.ProductDeleteFromInventory(sellerId,productId);
        responseEntity = new ResponseEntity<String>("successfully deleted", HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("categories")
    public ResponseEntity getAllCategories() {
        return new ResponseEntity<>(categoriesService.findAll(), HttpStatus.OK);
    }

    @PostMapping("category")
    public ResponseEntity saveCategory(@RequestBody Category category){
        ResponseEntity responseEntity;
        categoriesService.saveCategory(category);
        responseEntity = new ResponseEntity<String>("successfully created", HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping("subcategories")
    public ResponseEntity getAllSubCategories() {
        return new ResponseEntity<>(subCategoriesService.findAll(), HttpStatus.OK);
    }

    @GetMapping("subcategory/{categoryName}")
    public ResponseEntity<?> getCategorySubCategories(@PathVariable String categoryName) {
        return new ResponseEntity<>(subCategoriesService.getAll(categoryName), HttpStatus.OK);
    }
    @PostMapping("subcategory")
    public ResponseEntity saveSubCategory(@RequestBody SubCategory subCategory){
        ResponseEntity responseEntity;
        subCategoriesService.saveSubCategory(subCategory);
        responseEntity = new ResponseEntity<String>("successfully created", HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping("books")
    public ResponseEntity getAllBooks() {
        return new ResponseEntity<>(bookService.getAll(), HttpStatus.OK);
    }

    @PostMapping("book")
    public ResponseEntity saveBook(@RequestBody Book book){
        ResponseEntity responseEntity;
        bookService.saveBook(book);
        responseEntity = new ResponseEntity<String>("successfully created", HttpStatus.CREATED);
        return responseEntity;
    }

    @PutMapping("book")
    public ResponseEntity<?> UpdateBook(@RequestBody Book book) throws Exception, BookNotFoundException {

        Book updatedBook = bookService.updateBook(book);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("book/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable Long id) throws Exception, BookNotFoundException {
        return new ResponseEntity<>(bookService.deleteBookById(id), HttpStatus.OK);
    }

}
