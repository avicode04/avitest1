/*
package com.stackroute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.Product;
import com.stackroute.domain.Seller;
import com.stackroute.exceptions.GlobalException;
import com.stackroute.exceptions.ProductAlreadyExistsException;
import com.stackroute.exceptions.ProductNotFoundException;
import com.stackroute.service.ProductService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

*/
/**
 * @RunWith(SpringRunner.class) is used to provide a bridge between Spring Boot test features and JUnit
 *//*

@RunWith(SpringRunner.class)
@WebMvcTest

public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private Product product;
    */
/**
     * Create a mock for productService
     *//*

    @MockBean
    private ProductService productService;
    */
/**
     * Inject the mocks as dependencies into ProductController
     *//*

    @InjectMocks
    private ProductController productController;

    */
/**
     * Run this before each test case
     *//*

    private List<Seller> list1 = null;
    private List<Product> list2=null;

    @Before
    public void setUp() {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).setControllerAdvice(new GlobalException()).build();
        //act
        product = new Product();
        product.setId(100);
        product.setProductName("Music product100");
        list1 = new ArrayList<Seller>();
        Seller s1=new Seller(1,"Seller1",3.5,10,7,10,0,0,80);
        list1.add(s1);
        Seller s2=new Seller(2,"Seller2",4.0,10,7,10,0,0,80);
        list1.add(s2);
        product.setSellers(list1);
        list2=new ArrayList<Product>();
        list2.add(product);
    }

    @After
    public void tearDown() {
        this.product = null;
        this.list1 = null;
        this.list2=null;
    }

    @Test
    public void givenPostMappingUrlShouldReturnTheSavedproduct() throws Exception {
        when(productService.saveProduct(any())).thenReturn(product);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(product)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

        //verify here verifies that productService saveProduct method is only called once
        verify(productService, times(1)).saveProduct(product);

    }

    @Test
    public void givenPostMappingUrlAndProductShouldThrowProductAlreadyException() throws Exception {
        when(productService.saveProduct(any())).thenThrow(ProductAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(product)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());

        //verify here verifies that productService saveProduct method is only called once
        verify(productService, times(1)).saveProduct(product);
    }

    @Test
    public void givenGetMappingUrlShouldReturnListOfAllProducts() throws Exception {
        when(productService.getAllProducts()).thenReturn(list2);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andDo(MockMvcResultHandlers.print());

        //verify here verifies that productService getAllProducts method is only called once
        verify(productService, times(1)).getAllProducts();

    }

    @Test
    public void givenGetMappingUrlWithIdShouldReturnProductWIthThatThatId() throws Exception {
        when(productService.getProductById(anyInt())).thenReturn(product);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product/100")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andDo(MockMvcResultHandlers.print());

        //verify here verifies that productService getProductById method is only called once
        verify(productService, times(1)).getProductById(product.getId());
    }

    @Test
    public void givenGetMappingUrlAndIdShouldThrowProductNotFoundException() throws Exception {
        when(productService.getProductById(anyInt())).thenThrow(ProductNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product/200")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());

        //verify here verifies that productService getProductById method is not called even once
        verify(productService, times(0)).getProductById(product.getId());
    }


    @Test
    public void givenGetMappingUrlWithProductNameShouldReturnProductsWithThatProductName() throws Exception {
        when(productService.getSellerByProductName(any())).thenReturn(list1);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/Products/Music Product100")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andDo(MockMvcResultHandlers.print());

        //verify here verifies that ProductService getProductByName method is only called once
        verify(productService, times(1)).getSellerByProductName(product.getProductName());
    }

    @Test
    public void givenGetMappingUrlWithProductNameShouldReturnProductNotFoundException() throws Exception {
        when(productService.getSellerByProductName(any())).thenThrow(ProductNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/Emo emo")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
        //verify here verifies that productService getProductByName method is not called once
        verify(productService, times(0)).getSellerByProductName(product.getProductName());
    }

    @Test
    public void givenPutMappingUrlAndProductShouldReturnUpdatedProduct() throws Exception {
        when(productService.updateProduct(any())).thenReturn(product);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/product")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(product)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        //verify here verifies that productService updateProduct method is only called once
        verify(productService, times(1)).updateProduct(product);
    }


    @Test
    public void givenPutMappingUrlAndProductShouldReturnProductNotFoundException() throws Exception {
        when(productService.updateProduct(any())).thenThrow(ProductNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/product")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(product)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());


        //verify here verifies that productService updateProduct method is only called once
        verify(productService, times(1)).updateProduct(product);
    }

    @Test
    public void givenDeleteMappingUrlAndProductIdShouldReturnDeletedProduct() throws Exception {
        when(productService.deleteProductById(anyInt())).thenReturn(Optional.of(product));
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/product/100")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());


        //verify here verifies that productService deleteProductById method is only called once
        verify(productService, times(1)).deleteProductById(product.getId());
    }

    @Test
    public void givenDeleteMappingUrlAndProductIdShouldThrowProductNotFoundException() throws Exception {
        when(productService.deleteProductById(anyInt())).thenThrow(ProductNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/product/200")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());

        //verify here verifies that productService deleteProductById method is only called once
        verify(productService, times(0)).deleteProductById(product.getId());
    }

    @Test
    public void givenDeleteMappingUrlShouldDeleteAllProductsAndReturnTrue() throws Exception {
        when(productService.deleteAllProducts()).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        //verify here verifies that productService deleteAllProducts method is only called once
        verify(productService, times(1)).deleteAllProducts();
    }

    @Test
    public void givenDeleteMappingUrlShouldReturnDeleteAllProductsAndReturnFalse() throws Exception {
        when(productService.deleteAllProducts()).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());


        //verify here verifies that productService deleteAllProducts method is only called once
        verify(productService, times(1)).deleteAllProducts();
    }

    private static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);

        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

}
*/
