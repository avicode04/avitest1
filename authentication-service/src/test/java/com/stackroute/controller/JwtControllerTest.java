//package com.stackroute.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.stackroute.domain.AuthUser;
//import com.stackroute.domain.UserDTO;
//import com.stackroute.service.AuthUserService;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest
//public class JwtControllerTest{
//    @Autowired
//    private MockMvc mockMvc;
//    private AuthUser user;
//    private UserDTO userDTO;
//
//    @MockBean
//    private AuthUserService userService;
//
//    @InjectMocks
//    private JwtAuthenticationController userController;
//    private List<AuthUser> list;
//
//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup().build();
//        user=new AuthUser();
//        user.setEmailId("anushanayak00@gmail.com");
//        user.setPassword("Anusha123");
//        user.setRole("buyer");
//        userDTO=new UserDTO();
//        userDTO.setEmailId("anushanayak00@gmail.com");
//        userDTO.setPassword("Anusha123");
//        userDTO.setRole("buyer");
//        list=new ArrayList<>();
//        list.add(user);
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }
//
//    @Test
//    public void registration()throws Exception {
//        when(userService.save((UserDTO) any())).thenReturn(user);
//        mockMvc.perform(MockMvcRequestBuilders.post("/register")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
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
//}
