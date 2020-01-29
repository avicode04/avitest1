package com.stackroute.controller;

import com.stackroute.exceptions.DatabaseConnectivityFailedException;
import com.stackroute.exceptions.SellerNotFoundException;
import com.stackroute.kafka.SellerDto;
import com.stackroute.kafka.SellerRecomDto;
import com.stackroute.service.SellerService;
import com.stackroute.domain.Seller;
import com.stackroute.exceptions.SellerAlreadyExistsException;
import com.stackroute.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/")
@CrossOrigin(value = "*", allowedHeaders = "*")
public class SellerController {

    private SellerService sellerService;
    private Producer producer;

    private static ResponseEntity responseEntity;


    @Autowired
    public SellerController(SellerService sellerService, Producer producer) {
        this.sellerService = sellerService;
        this.producer = producer;
    }

    @PostMapping("seller")
    public ResponseEntity<?> saveSeller(@RequestBody SellerDto sellerDto) throws DatabaseConnectivityFailedException, SellerAlreadyExistsException, Exception{

        Seller seller=new Seller();
        seller.setSellerEmail(sellerDto.getSellerEmail());
        seller.setSellerName(sellerDto.getSellerName());
        seller.setSellerPhone(sellerDto.getSellerPhone());

        Seller savedSeller = sellerService.saveSeller(seller);

        SellerRecomDto sellerRecomDto=new SellerRecomDto();
        sellerRecomDto.setSellerEmail(seller.getSellerEmail());
        sellerRecomDto.setSellerName(seller.getSellerName());
        sellerRecomDto.setSellerPhone(seller.getSellerPhone());

        responseEntity = new ResponseEntity<Seller>(savedSeller, HttpStatus.CREATED);
        this.producer.sendMessage(sellerDto);
        this.producer.sendMessageSellerRecomDto(sellerRecomDto);
        return responseEntity;
    }

    @GetMapping("sellers")
    public ResponseEntity<?> getAllSeller() throws DatabaseConnectivityFailedException, Exception{
        List<Seller> allSeller = sellerService.getAllSeller();
        responseEntity = new ResponseEntity<List<Seller>>(sellerService.getAllSeller(),HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("seller/{sellerEmail}")
    public ResponseEntity<?> getSellerById(@PathVariable String sellerEmail) throws DatabaseConnectivityFailedException, SellerNotFoundException, Exception{
        responseEntity = new ResponseEntity<Seller>(sellerService.getSellerById(sellerEmail),HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("seller/{sellerEmail}")
    public ResponseEntity<?> deleteSellerById(@PathVariable String sellerEmail) throws DatabaseConnectivityFailedException, SellerNotFoundException, Exception{
        responseEntity = new ResponseEntity<Seller>(sellerService.deleteSellerById(sellerEmail),HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("sellers")
    public ResponseEntity<?> deleteAllSeller() throws DatabaseConnectivityFailedException, Exception{
        responseEntity = new ResponseEntity<>(sellerService.deleteAllSeller(),HttpStatus.OK);
        return responseEntity;
    }

    @PutMapping("seller")
    public ResponseEntity<?> updateSeller(@RequestBody Seller seller) throws DatabaseConnectivityFailedException, SellerNotFoundException, Exception{
        responseEntity = new ResponseEntity<Seller>(sellerService.updateSeller(seller),HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("sellers/{sellerName}")
    public ResponseEntity<?> getSellerByName(@PathVariable String sellerName) throws DatabaseConnectivityFailedException, SellerNotFoundException, Exception{

        responseEntity = new ResponseEntity<List<Seller>>(sellerService.getSellerByName(sellerName),HttpStatus.OK);
        return  responseEntity;
    }
}
