package com.example.demo.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.models.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

    Book getBookModelById(Long id);
}
