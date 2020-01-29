package com.stackroute.controller;

import com.stackroute.domain.Buyer;
import com.stackroute.exceptions.BuyerAlreadyExistException;
import com.stackroute.exceptions.BuyerNotFoundException;
import com.stackroute.exceptions.DatabaseConnectivityFailedException;
import com.stackroute.kafka.BuyerDto;
import com.stackroute.kafka.BuyerRecomDto;
import com.stackroute.service.BuyerService;
import com.stackroute.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value = "api/v1/")
@CrossOrigin(value = "*", allowedHeaders = "*")
public class BuyerController {

        private BuyerService buyerService;
        private Producer producer;

        private static ResponseEntity responseEntity;


        @Autowired
        public BuyerController(BuyerService buyerService, Producer producer) {
            this.buyerService = buyerService;
            this.producer = producer;
        }

    /**
     * Routing of save buyer method and setting field of BuyerDto via Buyer class
     */
        @PostMapping("buyer")
        public ResponseEntity<?> saveBuyer(@RequestBody BuyerDto buyerDto) throws DatabaseConnectivityFailedException, BuyerAlreadyExistException, Exception{

            Buyer buyer=new Buyer();
            buyer.setBuyerEmail(buyerDto.getBuyerEmail());
            buyer.setBuyerName(buyerDto.getBuyerName());
            buyer.setBuyerPhone(buyerDto.getBuyerPhone());
            Buyer savedBuyer = buyerService.saveBuyer(buyer);

            BuyerRecomDto buyerRecomDto=new BuyerRecomDto();
            buyerRecomDto.setBuyerEmail(buyer.getBuyerEmail());
            buyerRecomDto.setBuyerName(buyer.getBuyerName());
            buyerRecomDto.setBuyerPhone(buyer.getBuyerPhone());

            responseEntity = new ResponseEntity<Buyer>(savedBuyer, HttpStatus.CREATED);
            this.producer.sendMessageBuyerDto(buyerDto);
            this.producer.sendMessageBuyerRecomDto(buyerRecomDto);
            return responseEntity;
        }

    /**
     * Routing of get request to get all buyer method
     */

        @GetMapping("buyers")
        public ResponseEntity<?> getAllBuyer() throws DatabaseConnectivityFailedException, Exception{
            List<Buyer> allBuyer =buyerService.getAllBuyer();
            responseEntity = new ResponseEntity<List<Buyer>>(buyerService.getAllBuyer(),HttpStatus.OK);
            return responseEntity;
        }

    /**
     * Routing of get request to get buyer via id
     */

        @GetMapping("buyer/{buyerEmail}")
        public ResponseEntity<?> getBuyerById(@PathVariable String buyerEmail) throws DatabaseConnectivityFailedException, BuyerNotFoundException, Exception{
            responseEntity = new ResponseEntity<Buyer>(buyerService.getBuyerById(buyerEmail),HttpStatus.OK);
            return responseEntity;
        }


    /**
     * Routing of delete request to delete buyer via email
     */

        @DeleteMapping("buyer/{buyerEmail}")
        public ResponseEntity<?> deleteBuyerById(@PathVariable String buyerEmail) throws DatabaseConnectivityFailedException, BuyerNotFoundException, Exception{
            responseEntity = new ResponseEntity<Buyer>(buyerService.deleteBuyerById(buyerEmail),HttpStatus.OK);
            return responseEntity;
        }

    /**
     * Routing of delete request to delete buyers
     */

        @DeleteMapping("buyers")
        public ResponseEntity<?> deleteAllBuyer() throws DatabaseConnectivityFailedException, Exception{
            responseEntity = new ResponseEntity<>(buyerService.deleteAllBuyer(),HttpStatus.OK);
            return responseEntity;
        }

    /**
     * Routing of put request to update buyer
     */

        @PutMapping("buyer")
        public ResponseEntity<?> updateBuyer(@RequestBody Buyer buyer) throws DatabaseConnectivityFailedException, BuyerNotFoundException, Exception{
            responseEntity = new ResponseEntity<Buyer>(buyerService.updateBuyer(buyer),HttpStatus.OK);
            return responseEntity;
        }

    /**
     * Routing of get request to get buyer name
     */

        @GetMapping("buyers/{buyerName}")
        public ResponseEntity<?> getBuyerByName(@PathVariable String buyerName) throws DatabaseConnectivityFailedException, BuyerNotFoundException, Exception{

            responseEntity = new ResponseEntity<List<Buyer>>(buyerService.getBuyerByName(buyerName),HttpStatus.OK);
            return  responseEntity;
        }

    }

