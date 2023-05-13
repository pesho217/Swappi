package com.example.swappi.error;

import lombok.Getter;

import java.util.UUID;

@Getter
public class PersonBaseException extends RuntimeException{

    private final UUID errorId;

    public PersonBaseException(String message){
        super(message);
       this.errorId = UUID.randomUUID();
    }

}
