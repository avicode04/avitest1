//package com.stackroute.service;
//
//import com.stackroute.domain.Product;
//import com.stackroute.exception.ProductAlreadyExistsException;
//import com.stackroute.repository.ProductRepository;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//public class ProductServiceTest {
//
//    @Mock
//    ProductRepository productRepository;
//
//    @InjectMocks
//    ProductServiceImpl productService;
//    Product product;
//
//    @Before
//    public void setUp(){
//        MockitoAnnotations.initMocks(this);
//        product = new Product();
//        product.setProductName("Mi");
//        productRepository.save(product);
//    }
//
//    @After
//    public void tearDown(){
//        productRepository.deleteAll();
//    }
//
//    @Test
//    public void saveProductTestSuccess() throws ProductAlreadyExistsException {
//        when(productRepository.save((Product) any())).thenReturn(product);
//        Product savedProduct = productService.saveProduct(product);
//        Assert.assertEquals(product,savedProduct);
//    }
////
////    @Test(expected = ProductAlreadyExistsException.class)
////    public void saveProductTestFailure() throws ProductAlreadyExistsException{
//////        when(productRepository.save((Product)any())).thenReturn(null);
////        Product savedProduct = productService.saveProduct(product);
////        System.out.println(product.getProductId());
////    }
//}
