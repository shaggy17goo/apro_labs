package com.company;

import java.io.IOException;

public class NotACSVFileException extends IOException {
    public NotACSVFileException(){
        System.out.println("Not a .csv file");
        this.printStackTrace();

    }
    public NotACSVFileException(String str) {super(str);}

}
