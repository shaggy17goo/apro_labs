package com.company;

import java.io.IOException;

public class NotACSVFileException extends Exception {
    public NotACSVFileException(){
        System.out.println("Not a .csv file");
        this.printStackTrace();

    }
    public NotACSVFileException(String str) {super(str);}

}
