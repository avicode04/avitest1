//package com.stackroute.controller;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.stackroute.domain.Product;
//import com.stackroute.service.ProductService;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest
//class ProductControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    private Product product;
//
//    @MockBean
//    private ProductService productService;
//    @InjectMocks
//    private ProductController productController;
//
//    private List<Product> list =null;
//
//    @Before
//    public void setUp(){
//
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
//        product = new Product();
//        product.setProductName("Mi");
//        list = new ArrayList();
//        list.add(product);
//    }
//
//    @Test
//    public void saveUser() throws Exception {
//        when(productService.saveProduct(any())).thenReturn(product);
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(product)))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    private static String asJsonString(final Object obj)
//    {
//        try{
//            return new ObjectMapper().writeValueAsString(obj);
//
//        }catch(Exception e){
//            throw new RuntimeException(e);
//        }
//    }
//
//
//}
