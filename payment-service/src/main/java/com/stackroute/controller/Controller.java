package com.stackroute.controller;

import com.stackroute.domain.CustomerDetail;
import com.stackroute.service.CustomerService;
import com.stackroute.service.StripeClient;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1/payment")
@CrossOrigin(value = "*",allowedHeaders = "*")
public class Controller {

    private StripeClient stripeClient;
    private CustomerService customerService;

    private static ResponseEntity<?> responseEntity;

    @Autowired
    public Controller(StripeClient stripeClient, CustomerService customerService) {
        this.stripeClient = stripeClient;
        this.customerService = customerService;
    }

    @PostMapping("/charge")
    public Charge chargeCard(HttpServletRequest request) throws StripeException {
        Double amount = Double.parseDouble(request.getHeader("amount"));
        String customer = request.getHeader("customer");
        return this.stripeClient.chargeCreditCard(customer,amount);
    }

    @PostMapping("/customer")
    public Customer addCustomer(HttpServletRequest request) throws StripeException, Exception {
        String name = request.getHeader("name");
        String email = request.getHeader("email");
        return this.stripeClient.addCustomer(name,email);
    }

    @PostMapping("/card")
    public Customer addCard(HttpServletRequest request) throws StripeException {
        String customerId = request.getHeader("customerId");
        String number=request.getHeader("number");
        int exp_month=Integer.parseInt(request.getHeader("exp_month"));
        int exp_year=Integer.parseInt(request.getHeader("exp_year"));

        return this.stripeClient.addCard(customerId,number,exp_month,exp_year);
    }

    @DeleteMapping("/card")
    public Card deleteCard(HttpServletRequest request) throws StripeException {
        String custId = request.getHeader("customerId");
        String cardId = request.getHeader("cardId");
        return this.stripeClient.deleteCard(custId,cardId);
    }

    @PostMapping("/addCustomer")
    public ResponseEntity<?> addCustomer(@RequestBody CustomerDetail customer){
        CustomerDetail savedCustomer = customerService.saveCustomer(customer);
        responseEntity = new ResponseEntity<CustomerDetail>(savedCustomer, HttpStatus.CREATED);

        return responseEntity;
    }

    @GetMapping("/getcus/{customerEmail}")
    public ResponseEntity<?> getCustomerByEmail(@PathVariable String customerEmail){
        CustomerDetail getCustomer = customerService.getCustomerByEmail(customerEmail);
        responseEntity = new ResponseEntity<CustomerDetail>(getCustomer,HttpStatus.OK);
        return responseEntity;
    }



}
