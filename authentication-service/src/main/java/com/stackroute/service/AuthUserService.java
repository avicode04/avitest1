package com.stackroute.service;

import com.stackroute.config.JwtUserFactory;
import com.stackroute.domain.AuthUser;
import com.stackroute.domain.UserDTO;
import com.stackroute.kafka.BuyerDto;
import com.stackroute.kafka.SellerDto;
import com.stackroute.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService implements UserDetailsService {

	@Autowired
	private AuthUserRepository authUserRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;



	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AuthUser user = authUserRepository.findByEmailId(username);
			return JwtUserFactory.create(user);
	}

	public AuthUser save(UserDTO user) {
		AuthUser newUser = new AuthUser();
		newUser.setEmailId(user.getEmailId());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setRole(user.getRole());
		return authUserRepository.save(newUser);
	}

	@KafkaListener(topics = "sellerinfo", groupId = "seller-id",containerFactory = "kafkaListenerContainerFactory")
	public void consumeSeller(@Payload SellerDto sellerDto){
		System.out.println(sellerDto.toString());
		AuthUser newUser = new AuthUser();
		newUser.setEmailId(sellerDto.getSellerEmail());
		newUser.setPassword(bcryptEncoder.encode(sellerDto.getPassword()));
		newUser.setRole("seller");
		authUserRepository.save(newUser);
	}

	@KafkaListener(topics = "buyerinfo", groupId = "buyer-id",containerFactory = "kafkaListenerContainerFactory1")
	public void consumeBuyer(@Payload BuyerDto buyerDto){
		System.out.println(buyerDto.toString());
		AuthUser newUser = new AuthUser();
		newUser.setEmailId(buyerDto.getBuyerEmail());
		newUser.setPassword(bcryptEncoder.encode(buyerDto.getPassword()));
		newUser.setRole("buyer");
		authUserRepository.save(newUser);
	}

}