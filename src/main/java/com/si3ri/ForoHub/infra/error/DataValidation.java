package com.si3ri.ForoHub.infra.error;

public class DataValidation extends RuntimeException{
    public DataValidation(String s) {
        super(s);
    }
}
