package com.company;

import java.io.*;

/**
 * Simple program to read a palindrome from the file.
 *
 * @author mwawrzyn
 */
public class ReadPalindrome{
    /**
     * Read the palindrome from the file.
     */
    public ReadPalindrome(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            System.out.println(ois.readObject());
        }
    }


    /**
     * Starts the program.
     *
     * @param args not used.
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new ReadPalindrome("palindrome.plr");
    }
}
