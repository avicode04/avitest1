//package com.stackroute.service;
//
//import com.stackroute.domain.AuthUser;
//import com.stackroute.domain.UserDTO;
//import com.stackroute.repository.AuthUserRepository;
//import com.stackroute.service.AuthUserService;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.mockito.internal.verification.VerificationModeFactory.times;
//
//public class AuthUserServiceTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//
//    private UserDTO user;
//    private AuthUser authUser;
//
//    //Create a mock for UserRepository
//    @Mock
//    private AuthUserRepository authUserRepository;
//
//    //Inject the mocks as dependencies into UserServiceImpl
//    @InjectMocks
//    private AuthUserService userService;
//    List<AuthUser> list = null;
//
//    @Before
//    public void setUp() throws Exception {
//        //Initialising the mock object
//        MockitoAnnotations.initMocks(this);
//        user=new UserDTO();
////            user.setId(1);
//        user.setEmailId("anushanayak00@gmail.com");
//        user.setPassword("Anusha123");
//        user.setRole("buyer");
////            user.setEnable(true);
////            user.setPhonenumber("9972171647");
//        authUser=new AuthUser();
//        authUser.setEmailId("anushanayak00@gmail.com");
//        authUser.setPassword("Anusha123");
//        authUser.setRole("buyer");
//        list=new ArrayList<>();
//        list.add(authUser);
//    }
//
//
//    @After
//    public void tearDown() throws Exception {
//    }
//
//    @Test
//    public void saveUser() {
//        System.out.println("hello");
//        when(authUserRepository.save((AuthUser) any())).thenReturn(authUser);
//        AuthUser savedUser = userService.save(user);
//        System.out.println("mc");
//        Assert.assertEquals(user, savedUser);
//        //verify here verifies that userRepository save method is only called once
//        Mockito.verify(authUserRepository, times(1)).save(authUser);
//    }
//
//
//    @Test
//    public void saveUserFailure() {
//        when(authUserRepository.save((AuthUser) any())).thenReturn(null);
//        AuthUser savedUser = userService.save(user);
//        System.out.println("savedUser" + savedUser);
//        Assert.assertNotEquals(user, savedUser);
//    }
//
////    @Test
////    public void findall() {
////        authUserRepository.save(authUser);
////        when(authUserRepository.findAll()).thenReturn(list);
////        List<AuthUser> userList=userService.findall();
////        Assert.assertEquals(list,userList);
////
////    }
//
//}
//
