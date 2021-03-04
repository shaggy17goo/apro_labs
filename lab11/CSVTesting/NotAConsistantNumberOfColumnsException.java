package com.company;

import java.io.IOException;

public class NotAConsistantNumberOfColumnsException extends Exception {
    public NotAConsistantNumberOfColumnsException(){
        System.out.println("File does not have a consistant number of columns");
        this.printStackTrace();

    }
    public NotAConsistantNumberOfColumnsException(String str) {super(str);}

}
