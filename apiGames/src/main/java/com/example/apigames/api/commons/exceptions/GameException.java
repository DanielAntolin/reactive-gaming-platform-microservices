package com.example.apigames.api.commons.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class GameException  extends  RuntimeException{
    private HttpStatus HttpStatus;
    public GameException( HttpStatus httpStatus, String message) {
        super(message);
        this.HttpStatus = httpStatus;
    }
}
