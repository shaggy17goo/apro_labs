package com.company;

import java.util.Hashtable;
import java.util.Scanner;

import static java.lang.Character.isLetter;

public class Palindrome {

    static int cFLP=0, cAW=0, cIS=0;

    /**
     * Text of this palindrome.
     */
    private static String palindrome = null;

    /**
     * Text of this palindrome without white signs
     */
    private static String rawPalindrome = null;

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
        return super.hashCode();
    }

    public static void main(String[] arguments) {

        String pal="";
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj palindrom: ");
        pal = scan.nextLine();
        String abc="abc";

        String pal1=pal;
        for(int i=0; i<(pal.length())/3;i++)
            pal1+=abc;
        pal1+=pal;

        System.out.println("dla palindromu długości N w ciągu znaków długosci N:");
        Palindrome p1 =new Palindrome(pal1);
        String N1 = findLongestPalindrome(p1.palindrome);;
        System.out.println("metoda findLongestPalindrome wykonała się: "+cFLP+" razy.");
        System.out.println("metoda analyzeWord wykonała się: "+cAW+" razy.");
        System.out.println("metoda isPalindrom wykonała się: "+cIS+" razy.");
        System.out.println("Najdłuższy znaleziony palindrom to: "+ N1);
        System.out.println();


        cFLP=0;
        cAW=0;
        cIS=0;

        String pal2=pal;
        for(int i=0; i<(10*(pal.length())/3);i++)
            pal2+=abc;
        pal2+=pal;
        System.out.println("dla palindromu długości N w ciągu znaków długosci 10N:");
        Palindrome p2 =new Palindrome(pal2);
        String N2 = findLongestPalindrome(p2.palindrome);;
        System.out.println("metoda findLongestPalindrome wykonała się: "+cFLP+" razy.");
        System.out.println("metoda analyzeWord wykonała się: "+cAW+" razy.");
        System.out.println("metoda isPalindrom wykonała się: "+cIS+" razy.");
        System.out.println("Najdłuższy znaleziony palindrom to: "+N2);
        System.out.println();


        cFLP=0;
        cAW=0;
        cIS=0;

        String pal3=pal;
        for(int i=0; i<(100*(pal.length())/3);i++)
            pal3+=abc;
        pal3+=pal;

        System.out.println("dla palindromu długości N w ciągu znaków długosci 100N:");
        Palindrome p3 =new Palindrome(pal3);
        String N3 = findLongestPalindrome(p3.palindrome);;
        System.out.println("metoda findLongestPalindrome wykonała się: "+cFLP+" razy.");
        System.out.println("metoda analyzeWord wykonała się: "+cAW+" razy.");
        System.out.println("metoda isPalindrom wykonała się: "+cIS+" razy.");
        System.out.println("Najdłuższy znaleziony palindrom to: "+ N3);
        System.out.println();



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
        cAW++;
        String substring = "";
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