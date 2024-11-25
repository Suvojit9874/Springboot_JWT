package com.example.demo.controller;//package com.example.demo.controller;
//
//import com.example.demo.models.Book;
//import com.example.demo.service.BookService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/books")
//public class BookController {
//
//    @Autowired
//    private BookService bookService;
//
//    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
//    @GetMapping
//    public List<Book> getAllBooks() {
//        return bookService.getAllBooks();
//    }
//
//    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
//    @GetMapping("/{id}")
//    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
//        return bookService.getBookById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @PostMapping("/create")
//    public Book createBook(@RequestBody Book book) {
//        return bookService.createBook(book);
//    }
//
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @PutMapping("/{id}")
//    public Book updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
//        return bookService.updateBook(id, bookDetails);
//    }
//
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @DeleteMapping("/{id}")
//    public ResponseEntity <String> deleteBook(@PathVariable Long id) {
//        return ResponseEntity.ok(bookService.deleteBook(id));
//    }
//}


import com.example.demo.models.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        Book response=bookService.createBook(book);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book){
        return bookService.updateBook(id, book);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id){
        return bookService.deleteBook(id);
    }
}