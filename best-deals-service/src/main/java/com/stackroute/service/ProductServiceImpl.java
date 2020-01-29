package com.stackroute.service;

import com.stackroute.domain.Product;
import com.stackroute.domain.Seller;
import com.stackroute.exceptions.ProductAlreadyExistsException;
import com.stackroute.exceptions.ProductNotFoundException;
import com.stackroute.kafka.ProductDTO;
import com.stackroute.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Service indicates annotated class is a service which hold business logic in the Service layer
 */
@Service
/**
 * use @Primary to give higher preference to a bean when there are multiple beans of the same type.
 */
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    /**
     * Constructor based Dependency injection to inject productRepository here
     */
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Implementation of saveproduct method
     */
    @Override
    public Product saveProduct(Product product) throws ProductAlreadyExistsException {
        /**Throw productAlreadyExistsException if product already exists*/
        if (productRepository.existsById(product.getProductId())) {
            throw new ProductAlreadyExistsException();
        }
        Product savedProduct = productRepository.save(product);
        if (savedProduct == null) {
            throw new ProductAlreadyExistsException();
        }
        return savedProduct;
    }

    /**
     * Implementation of getproductById method
     */
    @Override
    public Product getProductById(String id) throws ProductNotFoundException {
        /**Throw productNotFoundException if product we want to get is not found*/
        if (productRepository.existsById(id)) {
            Product retrieveProductById = productRepository.findById(id).get();
            return retrieveProductById;
        } else {
            throw new ProductNotFoundException("product you want to get is not found");
        }
    }

    /**
     * Implementation of getAllproducts method
     */
    @Override
    public List<Product> getAllProducts() throws Exception {
        /**Throws Exception if Database connection issue happens*/
        productRepository.findAll();
        return productRepository.findAll();
    }

    /**
     * Implementation of deleteproductById method
     *
     * @return
     */
    @Override
    public Optional<Product> deleteProductById(String id) throws ProductNotFoundException {
        /**Throw productNotFoundException if product we want to delete is not found*/
        if (productRepository.existsById(id)) {
            Optional<Product> deletedproduct = productRepository.findById(id);
            productRepository.deleteById(id);
            return deletedproduct;
        } else {
            throw new ProductNotFoundException("product you want to delete is not found");
        }
    }

    @Override
    public Product getProductByName(String productName) throws ProductNotFoundException {
        /**Throw productNotFoundException if product we want to delete is not found*/
        if (productRepository.findByProductName(productName).isEmpty()) {
            throw new ProductNotFoundException("Product you want to get is not found");
        }
        System.out.println(productName);
        Product getProduct=productRepository.findByProductName(productName).get(0);
        System.out.println(getProduct);
            return getProduct;
    }

    /**
     * Implementation of deleteAllproducts method
     */
    @Override
    public boolean deleteAllProducts() throws Exception {
        /**Throws Exception if Database connection issue happens*/
        if (productRepository.findAll().isEmpty()) {
            return false;
        }
        productRepository.deleteAll();
        return true;
    }


    /**
     * Implementation of updateproduct method
     */
    @Override
    public Product updateProduct(Product product) throws ProductNotFoundException {
        /**Throw productNotFoundException if product we want to update is not found*/
        if (productRepository.existsById(product.getProductId())) {
            Product getProduct = productRepository.findById(product.getProductId()).get();
            getProduct.setProductName(product.getProductName());
            getProduct.setSellers(product.getSellers());
            return productRepository.save(getProduct);
        } else {
            throw new ProductNotFoundException("product you want to update is not found");
        }
    }

    /**
     * Implementation of getproductByName method
     */
    @Override
    public List<Seller> getSellerByProductName(String productName) throws ProductNotFoundException {
        //**Throw productNotFoundException if product we want to get is not found*//*
        if (productRepository.findByProductName(productName).isEmpty()) {
            throw new ProductNotFoundException("Product you want to get is not found");
        }
        Product product=productRepository.findByProductName(productName).get(0);
        List<Seller> sellers=product.getSellers();

        if(sellers.size()==1)
            return sellers;
        else {
            double maxPrice, minPrice, buyPercentage, sRatings, invScore, normPrice;
            maxPrice = minPrice = sellers.get(0).getProductPrice();
            if (sellers.get(1).getProductPrice() > sellers.get(0).getProductPrice()) {
                maxPrice = sellers.get(1).getProductPrice();
                minPrice = sellers.get(0).getProductPrice();
            } else {
                minPrice = sellers.get(1).getProductPrice();
                maxPrice = sellers.get(0).getProductPrice();
            }

            for (int i = 2; i < sellers.size(); i++) {
                if (sellers.get(i).getProductPrice() > maxPrice)
                    maxPrice = sellers.get(i).getProductPrice();

                else if (sellers.get(i).getProductPrice() < minPrice)
                    minPrice = sellers.get(i).getProductPrice();
            }

            for (int i = 0; i < sellers.size(); i++) {
                if (sellers.get(i).getProductSold() != 0) {
                    buyPercentage = 100 * (sellers.get(i).getProductSold() - sellers.get(i).getProductReturned()) / sellers.get(i).getProductSold();
                } else {
                    buyPercentage = 100.0;
                }
                sRatings = 20 * sellers.get(i).getSellerRatings();
                if ((sellers.get(i).getProductStock() + sellers.get(i).getProductSold()) != 0) {
                    invScore = (100 * (sellers.get(i).getProductStock()) / (sellers.get(i).getProductStock() + sellers.get(i).getProductSold()));
                } else {
                    invScore = 100.0;
                }
                normPrice = (100 * (maxPrice - sellers.get(i).getProductPrice()) / (maxPrice - minPrice));
                sellers.get(i).setSellerIndex(0.2 * buyPercentage + 0.25 * sRatings + 0.2 * invScore + 0.35 * normPrice);
            }
            product.setSellers(sellers);
            //this.updateProduct(product);
            List<Seller> sortedSellers = sellers.stream()
                    .sorted(Comparator.comparing(Seller::getSellerIndex).reversed())
                    .collect(Collectors.toList());
            return sortedSellers;
        }
    }

    //Implementation of Buying product
    public void buyProduct(String productName,String sellerId) throws ProductNotFoundException {
        if (productRepository.findByProductName(productName).isEmpty()) {
            throw new ProductNotFoundException("Product you want to get is not found");
        }
        Product product=productRepository.findByProductName(productName).get(0);
        List<Seller> sellers=product.getSellers();
        Seller seller=sellers.stream().filter(x->x.getSellerId().equalsIgnoreCase(sellerId)).collect(Collectors.toList()).get(0);
        seller.setProductSold(seller.getProductSold()+1);
        seller.setProductStock(seller.getProductStock()-1);
        for(int i=0;i<sellers.size();i++){
            if(sellers.get(i).getSellerId()==sellerId){
                sellers.set(i,seller);
            }
        }
        product.setSellers(sellers);
        this.updateProduct(product);
    }

    public Seller productWithSellerId(String productName,String sellerId) throws ProductNotFoundException{
        if (productRepository.findByProductName(productName).isEmpty()) {
            throw new ProductNotFoundException("Product you want to get is not found");
        }
        Product product=productRepository.findByProductName(productName).get(0);
        List<Seller> sellers=product.getSellers();
        System.out.println(sellers.get(0));
        Seller seller=sellers.stream().filter(x->x.getSellerId().equalsIgnoreCase(sellerId)).collect(Collectors.toList()).get(0);
        System.out.println(seller);
        return seller;
    }

    @KafkaListener(topics = "product-Info", groupId = "product-id",containerFactory = "kafkaListenerContainerFactory")
    public void consumeSeller(@Payload Product product){
        System.out.println(product.toString());
        if(productRepository.findByProductName(product.getProductName()).isEmpty()){
            System.out.println(product);
            productRepository.save(product);
        }
        else{
            Product getProduct = productRepository.findByProductName(product.getProductName()).get(0);
            getProduct.setProductName(product.getProductName());
            getProduct.setSellers(product.getSellers());
            productRepository.save(getProduct);
        }
    }
}
