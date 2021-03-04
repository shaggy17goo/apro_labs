package com.company;

import java.io.IOException;

public class NoColumnNamesException extends Exception {
    public NoColumnNamesException(){
        System.out.println("No Column Names in CSV file");
        this.printStackTrace();

    }
    public NoColumnNamesException(String str) {super(str);}

}
