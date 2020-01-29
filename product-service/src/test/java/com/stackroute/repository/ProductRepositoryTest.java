//package com.stackroute.repository;
//
//import com.stackroute.domain.Product;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@DataMongoTest
//public class ProductRepositoryTest {
//
//    @Autowired
//    ProductRepository productRepository;
//    Product product;
//
//    @Before
//    public void setUp(){
////        product.setProductId("1");
//        product = new Product();
//        product.setProductBrandName("Xaomi");
//        product.setProductName("Mi note 4");
//        product.setProductCategory("Electronics");
//        product.setProductDescription("4GB RAM");
//        product.setProductSubCategory("Mobile");
//        product.setProductImage("This is image link");
//    }
//
//    @After
//    public void tearDown(){
//        productRepository.deleteAll();
//    }
//
//    @Test
//    public void testSaveProduct(){
//        productRepository.save(product);
//        Product fetchProduct = productRepository.findById(product.getProductId()).get();
//        Assert.assertEquals("Xaomi",fetchProduct.getProductBrandName());
//    }
//
//    @Test
//    public void testSaveProductFailure(){
//        Product testproduct = new Product();
//        testproduct.setProductImage("This is new link");
//        testproduct.setProductName("Mi note 7");
//        Assert.assertNotSame(product,testproduct);
//    }
//
//
//
//}
