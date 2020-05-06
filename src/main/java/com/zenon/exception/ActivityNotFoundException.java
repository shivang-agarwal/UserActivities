package com.zenon.exception;

public class ActivityNotFoundException extends RuntimeException{
    public ActivityNotFoundException(){
        super();
    }

    public ActivityNotFoundException(String id){
        super("Activity with id["+id+"] doesn't exists.");
    }
}
