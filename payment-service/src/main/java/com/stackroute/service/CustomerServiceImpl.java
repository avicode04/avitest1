package com.stackroute.service;

import com.stackroute.domain.CustomerDetail;
import com.stackroute.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("customer")
public class CustomerServiceImpl implements CustomerService {

    private PaymentRepository paymentRepository;

    @Autowired
    public CustomerServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public CustomerDetail saveCustomer(CustomerDetail customer) {

        CustomerDetail savedCus = paymentRepository.save(customer);
        return savedCus;
    }

    @Override
    public CustomerDetail getCustomerByEmail(String email) {

        CustomerDetail getCus = paymentRepository.findById(email).get();
        return getCus;
    }
}
