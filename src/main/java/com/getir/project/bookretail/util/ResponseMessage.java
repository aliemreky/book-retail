package com.getir.project.bookretail.util;

public class ResponseMessage {

    public ResponseMessage() { }

    //CUSTOMER - USER
    public static final String CUSTOMER_CREATED = "CUSTOMER_CREATED";
    public static final String CUSTOMER_ALREADY_EXISTS = "CUSTOMER_ALREADY_EXISTS";

    //BOOK
    public static final String BOOK_CREATED = "BOOK_CREATED";
    public static final String BOOK_UPDATED = "BOOK_UPDATED";
    public static final String BOOK_ALREADY_EXISTS = "BOOK_ALREADY_EXISTS";
    public static final String BOOK_NOT_FOUND = "BOOK_NOT_FOUND";

    //ORDER
    public static final String ORDER_CREATED = "ORDER_CREATED";
    public static final String ORDER_ID_NOT_BE_EMPTY = "ORDER_ID_NOT_BE_EMPTY";
    public static final String ORDER_CREATED_WITH_WARNING = "ORDER_CREATED_WITH_WARNING";
    public static final String NO_STOCK_BOOK_IN_REQUEST = "NO_STOCK_BOOK_IN_REQUEST";

    public static final String SUCCESS = "SUCCESS";
    public static final String ID_NOT_NULL = "ID_NOT_NULL";
    public static final String EMAIL_NOT_VALID = "EMAIL_NOT_VALID";

}
