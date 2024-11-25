package com.example.demo.service;//package com.example.demo.service;
//
//import com.example.demo.models.Book;
//import com.example.demo.repository.BookRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class BookService {
//
//    @Autowired
//    private BookRepository bookRepository;
//
//    public List<Book> getAllBooks() {
//        return bookRepository.findAll();
//    }
//
//    public Optional<Book> getBookById(Long id) {
//        return bookRepository.findById(id);
//    }
//
//    public Book createBook(Book book) {
//        return bookRepository.save(book);
//    }
//
//    public Optional<Book> updateBook(Long id, Book bookDetails) {
//        return bookRepository.findById(id).map(existingBook -> {
//            existingBook.setTitle(bookDetails.getTitle());
//            existingBook.setAuthor(bookDetails.getAuthor());
//            existingBook.setDescription(bookDetails.getDescription());
//            return bookRepository.save(existingBook);
//        });
//    }
//
//
//    public boolean deleteBook(Long id) {
//        if (bookRepository.existsById(id)) {
//            bookRepository.deleteById(id);
//            return true;
//        }
//        return false;
//    }
//}

import com.example.demo.models.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    public BookRepository bookRepository;

    public Book createBook(Book book){
        return bookRepository.save(book);
    }

    public Optional <Book> getBookById(Long id){
        return bookRepository.findById(id);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book updateBook (Long id, Book book){
        Book book1=bookRepository.getBookModelById(id);
        book1.setTitle(book.getTitle());
        book1.setAuthor(book.getAuthor());
        book1.setDescription(book.getDescription());
        return bookRepository.save(book1);
    }

    public String deleteBook(Long id){
        if(bookRepository.existsById(id))
            bookRepository.deleteById(id);

        return "Deleted";
    }
}