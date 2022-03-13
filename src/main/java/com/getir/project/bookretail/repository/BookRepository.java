package com.getir.project.bookretail.repository;

import com.getir.project.bookretail.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, Long> {

    List<Book> getBooksByTitleAndAuthor(String title, String author);
}
