package com.stackroute.service;

import com.stackroute.domain.CustomerDetail;

public interface CustomerService {

    public CustomerDetail saveCustomer(CustomerDetail customer);

    public CustomerDetail getCustomerByEmail(String email);


}
