package com.phonebook.exceptions;

public class ContactServiceException extends RuntimeException{

    public ContactServiceException(){
        super();
    }
    public ContactServiceException(String message, Throwable cause){
        super(message, cause);
    }

    public ContactServiceException(String message) {
        super(message);
    }
}
