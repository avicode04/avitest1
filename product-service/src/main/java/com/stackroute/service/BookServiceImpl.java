package com.stackroute.service;

import com.stackroute.domain.Book;
import com.stackroute.domain.Product;
import com.stackroute.domain.Seller;
import com.stackroute.exception.ProductAlreadyExistsException;
import com.stackroute.exception.ProductNotExistsException;
import com.stackroute.kafka.NewSellerDto;
import com.stackroute.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private BookRepository bookRepository;
    @Autowired
    private Producer producer;
    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     Returns product details given product name
     */
    @Override
    public Book getProductDetails(String bookTitle) throws ProductNotExistsException {
        bookTitle = bookTitle.toLowerCase();
        if(bookRepository.findByBookTitle(bookTitle)==null) {
            throw new ProductNotExistsException("Product does not exists");
        }
        Book product = bookRepository.findByBookTitle(bookTitle);
        return product;
    }
    /**
     Save product
     */
    @Override
    public Book saveProduct(Book book) throws ProductAlreadyExistsException {
        book.setBookTitle(book.getBookTitle().toLowerCase());
        if(bookRepository.findByBookTitle(book.getBookTitle())==null) {
            Book book1 = bookRepository.save(book);
            return book1;
        }
        throw new ProductAlreadyExistsException("Product does not exists");

    }
    /**
     Delete product given product name
     */
    @Override
    public boolean deleteProduct(String bookTitle) throws ProductNotExistsException {
        bookTitle = bookTitle.toLowerCase();
        if(bookRepository.findByBookTitle(bookTitle)==null) {
            throw new ProductNotExistsException("Product does not exists");
        }
        Book book =  bookRepository.findByBookTitle(bookTitle);
        bookRepository.deleteById(book.getBookId());
        return true;
    }
    /**
     Update product given product name
     */
    @Override
    public Book updateProduct(Book book) throws ProductNotExistsException {
        book.setBookTitle(book.getBookTitle().toLowerCase());
        if(bookRepository.findByBookTitle(book.getBookTitle())==null) {
            throw new ProductNotExistsException("Product does not exists");
        }
        Book book1 = bookRepository.save(book);
        return book1;
    }
    /**
     Add seller to a product
     */
    @Override
    public boolean addSeller(String bookTitle, Seller seller) throws ProductNotExistsException {
        bookTitle = bookTitle.toLowerCase();
        if(bookRepository.findByBookTitle(bookTitle)==null) {
            throw new ProductNotExistsException("Product does not exists");
        }
            Book book = getProductDetails(bookTitle);
            List<Seller> sellerList = book.getSellers();
            sellerList.add(seller);
            book.setSellers(sellerList);
            updateProduct(book);
        NewSellerDto newSellerDto = new NewSellerDto();
        newSellerDto.setProductName(book.getBookTitle());
        newSellerDto.setSellerEmail(seller.getSellerId());
        newSellerDto.setProductPrice(seller.getProductPrice());
        newSellerDto.setProductStock(seller.getProductStock());
        this.producer.sendNewSeller(newSellerDto);
        return true;
    }
    /**
     Update seller details
     */
    @Override
    public boolean updateSellerDetails(String productId, Seller seller) throws ProductNotExistsException {
        if(bookRepository.findByBookTitle(productId)==null){
            throw new ProductNotExistsException("Product does not exists..");
        }
        Book book = getProductDetails(productId);
        List<Seller> sellerList= book.getSellers();
        for(Seller s:sellerList){
            if(s.getSellerId()==seller.getSellerId()){
                boolean b=false;
                for(Seller s1:sellerList){
                    if(seller.getSellerId().equals(s1.getSellerId())){
                        b = sellerList.remove(s1);
                        sellerList.add(seller);
                        break;
                    }
                }
                book.setSellers(sellerList);
                updateProduct(book);
                return b;
            }
        }
        boolean b=false;
        b = this.addSeller(productId,seller);
        return b;

    }
    /**
     Returns Product list for a particular seller
     */
    @Override
    public List<Book> getProductListOfSeller(String sellerId) {

        return null;
    }
    /**
     Returns seller list for a product
     */
    @Override
    public List<Seller> getSellerListOfProduct(String bookTitle) throws ProductNotExistsException {
        bookTitle = bookTitle.toLowerCase();
        if(bookRepository.findByBookTitle(bookTitle)==null) {
            throw new ProductNotExistsException("Product does not exists");
        }

            Book book = bookRepository.findByBookTitle(bookTitle);
            List<Seller> sellerList = book.getSellers();
            return sellerList;
    }
    /**
     Delete seller for a product
     */
    @Override
    public boolean deleteSeller(String bookTitle, String sellerId) throws ProductNotExistsException {
        bookTitle = bookTitle.toLowerCase();
        if(bookRepository.findByBookTitle(bookTitle)==null) {
            throw new ProductNotExistsException("Product does not exists");
        }
        Book book = getProductDetails(bookTitle);
        List<Seller> sellerList= book.getSellers();
        boolean b=false;
        for(Seller s:sellerList){
            if(sellerId.equals(s.getSellerId())){
                b = sellerList.remove(s);
                break;
            }
        }
        book.setSellers(sellerList);
        updateProduct(book);
        return b;
    }
    /**
     delete all product for a seller
     */
    @Override
    public Seller deleteAllProductOfSeller(Seller seller) {
        return null;
    }

}
