package com.stackroute.service;

import com.stackroute.domain.Book;
import com.stackroute.domain.Products;
import com.stackroute.exceptions.BookNotFoundException;
import com.stackroute.kafka.BookRecomDto;
import com.stackroute.kafka.NewSellerDto;
import com.stackroute.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public List<Book> getAll() {
        return (List<Book>) bookRepository.findAll();
    }
    public Book saveBook(Book book){
        Book savedBook =bookRepository.save(book);
        //BookRepository.postBookSamePriceRange(savedBooks.getId());
        //BookRepository.postBookSameBrand(savedBooks.getId());
        //BookRepository.postBookCategory(savedBooks.getId());
        return savedBook;
    }

    public Book deleteBookById(Long id) throws BookNotFoundException {
        /**Throw BookNotFoundException if Book we want to delete is not found*/
        if (bookRepository.existsById(id)) {
            Book deletedBooks = bookRepository.findById(id).get();
            bookRepository.deleteById(id);
            return deletedBooks;
        } else {
            throw new BookNotFoundException("Book you want to delete is not found");
        }
    }

    public Book updateBook(Book book) throws BookNotFoundException {
        /**Throw BookNotFoundException if Book we want to update is not found*/
        if (bookRepository.existsById(book.getId())) {
            Book getBooks = bookRepository.findById(book.getId()).get();
            getBooks.setBookTitle(book.getBookTitle());
            //getBooks.setSellers(Books.getSellers());
            getBooks.setBookPublisher(book.getBookPublisher());
            return bookRepository.save(getBooks);
        } else {
            throw new BookNotFoundException("Book you want to update is not found");
        }
    }

    @KafkaListener(topics = "book-recom-Info", groupId = "book-id",containerFactory = "kafkaListenerContainerFactory1")
    public void consumeBook(@Payload BookRecomDto bookRecomDTO){
        System.out.println(bookRecomDTO.toString());
        Book newBook = new Book();
        newBook.setBookTitle(bookRecomDTO.getBookTitle());
        newBook.setBookPublisher(bookRecomDTO.getBookPublisher());
        newBook.setBookAuthor(bookRecomDTO.getBookAuthor());
        newBook.setBookISBN(bookRecomDTO.getBookISBN());
        newBook.setBookCategory(bookRecomDTO.getBookCategory());
        newBook.setBookSubcategory(bookRecomDTO.getBookSubcategory());

        Book savedBook=this.saveBook(newBook);
        System.out.println(savedBook);
        bookRepository.AddSellers(bookRecomDTO.getSellerEmail(),bookRecomDTO.getBookTitle(),bookRecomDTO.getBookStock(),bookRecomDTO.getBookPrice());

    }

    @KafkaListener(topics = "product-new-seller", groupId = "new-seller-id",containerFactory = "kafkaListenerContainerFactory1")
    public void consumeProduct(@Payload NewSellerDto newSellerDto){
        System.out.println(newSellerDto.toString());
        Book newBook = new Book();
        bookRepository.AddNewSellers(newSellerDto.getSellerEmail(),newSellerDto.getProductName(),newSellerDto.getProductStock(),newSellerDto.getProductPrice());
    }

}
