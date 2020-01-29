//package com.stackroute.domain;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//
///**
// * Document annotated class will have the ability to represent objects in the database
// */
//@Document(collection = "database_sequences")
//
///**With @Data, Lombok will generate getter and setter methods, toString methods, Equal & Hashcode methods*/
//@Data
//
///**@NoArgsConstructor will generate constructor with no arguments*/
//@NoArgsConstructor
//
///**@AllArgsConstructor will generate constructor with all properties in the class*/
//@AllArgsConstructor
//@Builder
//public class DatabaseSequence {
//
//    @Id
//    private String id;
//    private long seq;
//
//}