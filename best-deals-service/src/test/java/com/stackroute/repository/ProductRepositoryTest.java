/*
 package com.stackroute.repository;



 import com.stackroute.domain.Product;
 import com.stackroute.domain.Seller;
 import org.junit.After;
 import org.junit.Assert;
 import org.junit.Before;
 import org.junit.Test;
 import org.junit.runner.RunWith;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
 import org.springframework.test.context.junit4.SpringRunner;

 import java.util.ArrayList;
 import java.util.List;

 */
/**
  * @RunWith(SpringRunner.class) is used to provide a bridge between Spring Boot test features and JUnit
  *//*

 @RunWith(SpringRunner.class)
 */
/**Using @DataMongoTest annotation will disable full auto-configuration and instead apply only configuration relevant to MongoDB tests.
  *//*

 @DataMongoTest
 public class ProductRepositoryTest {

     */
/**
      * Used AutoWire by property to inject TrackRepository here
      *//*

     @Autowired
     private ProductRepository productRepository;
     private Product product;


     */
/**
      * Execute this before each test case
      *//*

     @Before
     public void setUp() {
         //Add new track by setter methods
         product = new Product();
         product.setId(1);
         product.setProductName("Music track1");


     }

     */
/**
      * Execute this after every test case
      *//*

     @After
     public void tearDown() {
         //Free the repository after every test case
         productRepository.deleteAll();
         this.product = null;
     }


     @Test
     public void givenProductShouldSaveThatProduct() {
         //assert
         Assert.assertEquals(product, productRepository.save(product));

     }

     @Test
     public void givenProductShouldSaveproductFailure() {
         //act
         List<Seller> list1 = null;
         list1 = new ArrayList<Seller>();
         Seller s1=new Seller(1,"Seller1",3.5,10,7,10,0,0,80);
         list1.add(s1);
         Seller s2=new Seller(2,"Seller2",4.0,10,7,10,0,0,80);
         list1.add(s2);
         Product expectedProduct = new Product(1,"Mobile",list1);
         //assert
         Assert.assertNotSame(expectedProduct, productRepository.save(product));

     }

     @Test
     public void givenTrackIdShouldReturnTrackOfThatId() {
         //act
         productRepository.save(product);
         Product actualResult = productRepository.findById(product.getId()).get();
         //assert
         Assert.assertEquals(product, actualResult);
     }

     @Test
     public void givenTrackIdShouldReturnTrackOfThatIdFailure() {
         //act
         List<Seller> list1 = null;
         list1 = new ArrayList<Seller>();
         Seller s1=new Seller(1,"Seller1",3.5,10,7,10,0,0,80);
         list1.add(s1);
         Seller s2=new Seller(2,"Seller2",4.0,10,7,10,0,0,80);
         list1.add(s2);
         Product expectedProduct = new Product(1,"Mobile",list1);
         productRepository.save(product);
         Product actualResult = productRepository.findById(product.getId()).get();
         //assert
         Assert.assertNotSame(product, actualResult);
     }

     @Test
     public void givenTracksShouldReturnListOfAllTracks() {
         //act
         List<Seller> list1 = null;
         list1 = new ArrayList<Seller>();
         Seller s1=new Seller(1,"Seller1",3.5,10,7,10,0,0,80);
         list1.add(s1);
         Seller s2=new Seller(2,"Seller2",4.0,10,7,10,0,0,80);
         list1.add(s2);
         Product expectedProduct1 = new Product(1,"Mobile",list1);

         List<Seller> list2 = null;
         list2 = new ArrayList<Seller>();
         Seller s3=new Seller(1,"Seller1",3.5,10,7,10,0,0,80);
         list2.add(s1);
         Seller s4=new Seller(2,"Seller2",4.0,10,7,10,0,0,80);
         list2.add(s2);
         Product expectedProduct2 = new Product(2,"Books",list1);
         productRepository.save(expectedProduct1);
         productRepository.save(expectedProduct2);

         List<Product> list = new ArrayList<>();
         list.add(expectedProduct1);
         list.add(expectedProduct2);

         List<Product> actualResult = productRepository.findAll();
         //assert
         Assert.assertEquals(list, actualResult);

     }

     @Test
     public void givenMethodCallToGetAllTracksShouldReturnListOfAllTracksFailure() {
          track1 = new Track(1, "Music track1", "comment1");
         Track track2 = new Track(2, "Music track2", "comment2");
         trackRepository.save(track1);
         trackRepository.save(track2);
         trackRepository.save(track);

         List<Track> list = new ArrayList<>();
         list.add(track1);
         list.add(track2);

         List<Track> actualResult = trackRepository.findAll();
         //assert
         Assert.assertNotSame(list, actualResult);

     }

     @Test
     public void givenTrackIdTODeleteShouldDeleteTheTrack() {
         //act
         trackRepository.deleteById(track.getId());
         List expectedResult = new ArrayList();
         //assert
         Assert.assertEquals(expectedResult, trackRepository.findAll());

     }

     @Test
     public void givenTrackIdShouldDeleteTrackFailure() {
         //act
         trackRepository.deleteById(track.getId());
         //assert
         Assert.assertNotSame(" ", trackRepository.findById(track.getId()));

     }

     @Test
     public void givenMethodCallToDeleteAllTracksShouldReturnTrue() {
         //act
         trackRepository.deleteAll();
         //assert
         Assert.assertEquals(true, trackRepository.findAll().isEmpty());

     }

     @Test
     public void givenMethodCallToDeleteAllTracksShouldReturnFalse() {
         //act
         trackRepository.deleteAll();
         //assert
         Assert.assertEquals(false, !(trackRepository.findAll().isEmpty()));

     }

     @Test
     public void givenTrackShouldReturnUpdatedTrack() {
         //act
         trackRepository.save(track);
         Track getTrack = trackRepository.findById(track.getId()).get();
         getTrack.setComments("Updated comment");
         Track track = new Track(1, "Music track1", "Updated comment");
         //assert
         Assert.assertEquals(track, getTrack);
     }

     @Test
     public void givenTrackIdShouldReturnUpdatedTrackFailure() {
         //act
         trackRepository.save(track);
         Track getTrack = trackRepository.findById(track.getId()).get();
         getTrack.setComments("Updated comment");
         //assert
         Assert.assertNotSame(track, getTrack);
     }

     @Test
     public void givenTrackNameShouldReturnTracksWithThatName() {
         //act
         trackRepository.save(track);
         List<Track> expectedResult = new ArrayList<>();
         expectedResult.add(track);
         //assert
         Assert.assertEquals(expectedResult, trackRepository.findBytrackName(track.getTrackName()));
     }

     @Test
     public void givenTrackNameShouldReturnTracksWithThatNameFailure() {
         //act
         productRepository.save(product);
         //assert
         Assert.assertNotSame(product, productRepository.findSellersByProductName(g));
     }
 }
*/
