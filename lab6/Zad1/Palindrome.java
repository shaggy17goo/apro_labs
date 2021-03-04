package com.company;

import java.util.Hashtable;
import java.util.Scanner;

import static java.lang.Character.isLetter;

public class Palindrome {

    static int cFLP=0, cAW=0, cIS=0;

    /**
     * Text of this palindrome.
     */
    private String palindrome = null;

    /**
     * Text of this palindrome without white signs
     */
    private String rawPalindrome = null;

    /**
     * @return palindrome - value of palindrome.
     */
    public String getPalindrome() {
        return palindrome;
    }

    /**
     * @return rawPalindrome - value of palindrome without white sign.
     */
    public String getRawPalindrome() {
        return rawPalindrome;
    }

    /**
     * @return rawPalindrome - palindrome without white signs.
     */
    public String makeRawPalindrome(){
        String rawPalindrome="";
        for(int i=0; i<palindrome.length();i++){
            if(isLetter(palindrome.charAt(i))){
                rawPalindrome=rawPalindrome+palindrome.charAt(i);
            }
        }
        return rawPalindrome;
    }

    /**
     * @return rawPalindrome - palindrome without white signs.
     */
    public static String makeRawPalindrome(String word){
        String rawPalindrome="";
        for(int i=0; i<word.length();i++){
            if(isLetter(word.charAt(i))){
                rawPalindrome=rawPalindrome+word.charAt(i);
            }
        }
        return rawPalindrome;
    }


    /**
     * @return Palindrom to string
     */
    @Override
    public String toString(){
        return "Palindrom text: " + palindrome;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Palindrome that = (Palindrome) o;
        return getPalindrome().equals(that.getPalindrome());
    }

    @Override
    public int hashCode() {
        return palindrome.hashCode();
    }

    public static void main(String[] arguments) {
        Palindrome p1 = new Palindrome("kobyłamamałybok");
        Palindrome p2 = new Palindrome("kobyła ma mały bok!");
        Palindrome p3 = new Palindrome("elf układał kufle");
        System.out.println("p1 == p2 : " + (p1 == p2));
        System.out.println("p1.equals(p2) : " + p1.equals(p2));
        Hashtable<Palindrome, String> ps = new Hashtable<Palindrome, String>();
        ps.put(new Palindrome("a car boso kokos obraca"), "Andrzej Pacierpnik");
        ps.put(p2, "autor nieznany");
        ps.put(new Palindrome("muzo, raz daj jad za rozum"), "Julian Tuwim");
        ps.put(p3, "Tadeusz Morawski");
        System.out.println("Autorem palindromu " + p3 + " jest " + ps.get(p3));
        String word = "rrrrr abccba kobyłamamałybok";
        String longestPalindrome = findLongestPalindrome(word);
        System.out.println("longestPalindrome: " + longestPalindrome);

        System.out.println("metoda findLongestPalindrome wykonała się: "+cFLP+" razy.");
        System.out.println("metoda analyzeWord wykonała się: "+cAW+" razy.");
        System.out.println("metoda isPalindrom wykonała się: "+cIS+" razy.");

    }

    public Palindrome(String palindrome) {
        this.palindrome=palindrome;
    }

    public static String findLongestPalindrome(String word) {
        String current = "";
        cFLP++;
        for (int i = 0; i < word.length() - 1; i++) {
            String subWord = word.substring(i, word.length());
            String substring = analyzeWord(subWord);
            if (substring.length() > current.length()) {
                current = substring;
            }
        }
        return current;
    }

    public static String analyzeWord(String subWord) {
        int index = subWord.length();
        String substring = "";
        cAW++;
        while (index > 0) {
            substring = subWord.substring(0, (index));
            if (isPalindrome(substring) == true) {
                break;
            }
            index--;
        }
        return substring;
    }

    private static boolean isPalindrome(String substring) {
        cIS++;
        String rawSubstring=makeRawPalindrome(substring);
        int i1 = 0;
        int i2 = rawSubstring.length() - 1;
        boolean isPalindrome = true;
        while (i2 > i1) {
            if (rawSubstring.charAt(i2) != rawSubstring.charAt(i1)) {
                isPalindrome = false;
                break;
            }
            i1++;
            i2--;
        }
        return isPalindrome;
    }




}