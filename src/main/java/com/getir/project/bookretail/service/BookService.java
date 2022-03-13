package com.getir.project.bookretail.service;

import com.getir.project.bookretail.entity.Book;
import com.getir.project.bookretail.repository.BookRepository;
import com.getir.project.bookretail.request.BookRequest;
import com.getir.project.bookretail.response.BaseResponse;
import com.getir.project.bookretail.util.ResponseMessage;
import com.getir.project.bookretail.util.enums.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    @Autowired
    private CommonService commonService;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BaseResponse createBook(BookRequest request) {
        BaseResponse response = new BaseResponse();

        List<Book> bookList = bookRepository.getBooksByTitleAndAuthor(request.getTitle(), request.getAuthor());

        if (bookList.size() > 0) {
            response.setStatus(StatusEnum.FAILED.getValue());
            response.setMessage(ResponseMessage.BOOK_ALREADY_EXISTS);
        } else {
            Book newBook = new Book();
            newBook.setId(commonService.generateSequence(Book.SEQUENCE_NAME));
            mapVariablesOfEntity(newBook, request);
            bookRepository.save(newBook);

            response.setStatus(StatusEnum.SUCCESS.getValue());
            response.setMessage(ResponseMessage.BOOK_CREATED);
        }

        return response;
    }

    @Override
    public BaseResponse updateBook(BookRequest request) {
        BaseResponse response = new BaseResponse();

        if (request.getId() == null) {
            response.setStatus(StatusEnum.FAILED.getValue());
            response.setMessage(ResponseMessage.ID_NOT_NULL);
            return response;
        }

        Optional<Book> getBook = bookRepository.findById(request.getId());

        if (getBook.isPresent()) {
            Book bookEntity = getBook.get();
            mapVariablesOfEntity(bookEntity, request);
            bookRepository.save(bookEntity);

            response.setStatus(StatusEnum.SUCCESS.getValue());
            response.setMessage(ResponseMessage.BOOK_UPDATED);
        } else {
            response.setStatus(StatusEnum.FAILED.getValue());
            response.setMessage(ResponseMessage.BOOK_NOT_FOUND);
        }

        return response;
    }

    private void mapVariablesOfEntity(Book book, BookRequest request) {
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setDescription(request.getDescription());
        book.setPrice(request.getPrice());
        book.setPublisher(request.getPublisher());
        book.setImageUrl(request.getImageUrl());
        book.setPublisherYear(request.getPublisherYear());
        book.setStock(request.getStock());
        book.setCreatedDate(LocalDateTime.now());
    }
}
