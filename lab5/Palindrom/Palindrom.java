package com.company;

import java.util.Scanner;

public class Palindrom {

    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        System.out.println("Podaj słowo a ja znajdę w nim najdłuższy palindrom: ");
        String word = scan.nextLine();
        String longestPalindrome="";

        for(int i=0; i< word.length(); i++) {
            String current="";
            String subword = word.substring(i, word.length());
            int index =subword.length();
            while(index>0){
                String substring=subword.substring(0, (index));
                int i1=0;
                int i2=substring.length()-1;
                boolean isPalindrome=true;
                while(i2>i1){
                    if(substring.charAt(i2)!=substring.charAt(i1)){
                        isPalindrome=false;
                        break;
                    }
                    i1++;
                    i2--;
                }
                if(isPalindrome==false){
                    index=index-1;
                    continue;
                }
                if(isPalindrome==true){
                    break;
                }
            }
            current=subword.substring(0, index);
            if(longestPalindrome.length()<current.length()){
                longestPalindrome=current;
            }
        }
        if(longestPalindrome.length()>1) {
            System.out.println("Najdłuższy palindrom to: " + longestPalindrome);
        }
        else{
            System.out.println("Brak palindromu :(");
        }
    }
}

