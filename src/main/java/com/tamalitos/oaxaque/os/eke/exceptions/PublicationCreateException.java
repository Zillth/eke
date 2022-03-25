package com.tamalitos.oaxaque.os.eke.exceptions;

public class PublicationCreateException extends Exception{
    public PublicationCreateException() {
        super("Failed on create a publication, try again later");
    }
}
