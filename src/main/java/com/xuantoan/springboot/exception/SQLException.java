package com.xuantoan.springboot.exception;

public class SQLException extends RuntimeException{
    public SQLException(String mess){
        super(mess);
    }
}
