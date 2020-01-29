package com.stackroute.service;

import com.stackroute.repository.PaymentRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Profile("stripe")
public class StripeClient {

    private PaymentRepository paymentRepository;

    @Autowired
    StripeClient(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
        Stripe.apiKey ="sk_test_qj7NOEUYZpx3QaaBOifcjHmV00V7sa1ohc";
    }

    public Charge chargeCreditCard(String customer, Double amount) throws StripeException {
        Map<String,Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int)(amount * 100));
        chargeParams.put("currency","INR");
        chargeParams.put("customer", customer);
        //chargeParams.put("source",token);
        Charge charge = Charge.create(chargeParams);
        return charge;
    }

    public Customer addCustomer(String name, String email) throws StripeException, Exception {
        if(!paymentRepository.existsById(email)) {
            Map<String, Object> addCustomer = new HashMap<String, Object>();
            addCustomer.put("name", name);
            addCustomer.put("email", email);
            Customer customer = Customer.create(addCustomer);
            return customer;
        }else{
            throw new Exception("user already added");
        }
    }

    public Customer addCard(String customerId,String number, int exp_month, int exp_year) throws StripeException {

        Customer customer = Customer.retrieve(customerId);

        Map<String,Object> cardParam = new HashMap<String, Object>();
        cardParam.put("number",number);
        cardParam.put("exp_month", exp_month);
        cardParam.put("exp_year", exp_year);
//        cardParam.put("cvc", cvc);

        Map<String, Object> tokenParam = new HashMap<String, Object>();
        tokenParam.put("card", cardParam);

        Token token = Token.create(tokenParam);

        Map<String, Object> source = new HashMap<String, Object>();
        source.put("source", token.getId());

        customer.getSources().create(source);

        return customer;
    }

    public Card deleteCard(String customerId,String cardId) throws StripeException {
        Customer customer = Customer.retrieve(customerId);
        Card card = (Card) customer.getSources().retrieve(cardId);
        card.delete();
        return card;
    }

}
