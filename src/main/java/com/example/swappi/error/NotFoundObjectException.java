package com.example.swappi.error;

import lombok.Getter;

@Getter
public class NotFoundObjectException extends PersonBaseException {
    private final String objectClazz;
    private final String id;

    public  NotFoundObjectException(String objectClazz, String id, String message){
        super(message);
        this.objectClazz = objectClazz;
        this.id = id;




    }


}
