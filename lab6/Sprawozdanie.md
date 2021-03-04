##### Michał Wawrzyńczak
# **Lab6**

### **Zad 4.1**
Plik Palindrome.java zawarty w folderze **Zad1** 

```java
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
```




### Podpunkt 7

-**Zwrotna**
Oznacza to że jeśli porównamy metodą equals obiekt z nim samym to zawsze otrzymamy wartość true.


-**Symetryczna**
Porównując obiekt A z obiektem B zawsze otrzymamy taki sam wynik jak przy porównywaniu B z A.


-**Przechodnia**
Jeśli mamy 3 obiekty A, B, C i metoda equals zwróci dana wartość dla par AB i BC, to dla pary AC zwróci identyczna wartość


-**Spójna**
Zakładając że obiekty A i B nie były modyfikowane pomiędzy wywołaniami, to metoda equals zawsze zwróci tą samą wartość


### **Zad 4.2**
Plik PalindormeTest.java znajduje się w folderze **Zad2**
Zwracający ilość wykonania się metod:
- findLongestPalindrome
- analyzeWord
- isPalindrome

Gdy palindrom długości N znajduję się w ciągu znaków długości N, 10N, 100N.

```java
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

```

### **Zad 4.3**
Plika Steganography.java znajduje się w folderze **Zad3**

```java
package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;


public class Steganography {

    public Steganography(String inputImagePath,
                         String secretImagePath,
                         String encodedImagePath) {
        byte[] encoded= encode(inputImagePath,secretImagePath);
        saveImage(encoded, encodedImagePath);
    }

    public Steganography(String encodedImagePath,
                         String secretImagePath) {
        decodeImage(encodedImagePath,secretImagePath);
    }



    public static void main(String[] arguments) throws IOException {
        Steganography encoder = new Steganography("./first.bmp", "second.bmp","./output.bmp");
        Steganography decoder= new Steganography("./output.bmp","decoded.bmp");
    }

    private void addHiddenImage(byte[] image, byte[] addition) {
        int bitValue, byteValue;
        int k=54;
        for(int i=54; i<addition.length; i++){
            byteValue=addition[i];
            for(int j=0; j<8;j++) {
                bitValue = ((byteValue >>> j) & 0x01);      //0x01=0b00000001
                image[k] = (byte) (image[k] & 0xFE);        //0xFE=0b11111110
                image[k] = (byte) (image[k] | bitValue);
                k++;
            }
        }
    }
    private void addHiddenText(byte[] image, byte[] addition) {
        int bitValue, byteValue;
        int k=54;
        for(int i=0; i<addition.length; i++){
            byteValue=addition[i];
            for(int j=0; j<8;j++){
                bitValue=((byteValue>>>j) & 0x00000001);
                image[k]=(byte)(image[k] & 0x11111110);
                image[k]=(byte)(image[k] | bitValue);
                k++;
            }
        }
    }


    private void addHiddenBetterImage(byte[] image, byte[] addition) {
        int bitValue, byteValue;
        int k=54;
        for(int i=54; i<image.length; i++){
            byteValue=addition[i];
            bitValue=((byteValue>>>4) & 0b00001111);
            image[k]=(byte)(image[k]&  0b11110000);
            image[k]=(byte)(image[k] | bitValue);
            k++;
        }
    }

    /**
     *
     * @param imagePath image
     * @param secretPath image
     * @return imageBytes
     */
    public byte[] encode(String imagePath, String secretPath) {
        byte[] imageBytes = getImageBytes(imagePath);
        byte[] secretImageBytes = getImageBytes(secretPath);
        addHiddenImage(imageBytes, secretImageBytes);
        return imageBytes;
    }

    /**
     *
     * @param imagePath image
     * @param secretPath image
     * @return encode image
     */
    public byte[] encode2(String imagePath, String secretPath) {
        byte[] imageBytes = getImageBytes(imagePath);
        byte[] secretImageBytes = getImageBytes(secretPath);
        addHiddenBetterImage(imageBytes, secretImageBytes);
        return imageBytes;
    }

    /**
     *
     * @param image
     * @param toAdd string
     * @return encode image
     */
    public byte[] encode(byte[] image, String toAdd) {
        byte[] secretMessage = toAdd.getBytes();
        addHiddenText(image, secretMessage);
        return image;
    }

    /**
     *
     * @param imagePath
     * @param codedImagePath
     */
    public void decodeImage(String imagePath, String codedImagePath) {
        byte[] imageBytes = getImageBytes(imagePath);
        // 54 - length of the BMP header
        // 24 -> because of the 8 bytes for one pixel and 3 channels (RGB)
        int byteSize = (imageBytes.length - 54) / (24 * 24) + 54;
        byte[] outputImage = new byte[byteSize];
        int mask = 0x01;
        int index = 0;
        for (int i = 0; i < outputImage.length; ++i) {
            // omit the header
            if (i < 54) {
                outputImage[i] = imageBytes[i];
                ++index;
                continue;
            }
            byte accumulator = 0;
            for (int j = 0; j < 8; ++j) {
                int flag = imageBytes[index] & mask;
                accumulator >>= 1;
                if (flag == 1) {
                    accumulator |= 0x80;
                } else {
                    accumulator &= 0x7F;
                }
                ++index;
            }
            outputImage[i] = accumulator;
        }



        //modifying the bmp header -> otherwise we will get the bad result
        // bytes 2-5 file size in bytes
        outputImage[2] = (byte) byteSize;
        outputImage[3] = (byte) (byteSize >> 8);
        outputImage[4] = 0;
        // bytes 18-21 width in pixels
        int size = ((imageBytes[19] << 8) | (imageBytes[18] & 0x00FF)) / 24;
        outputImage[18] = (byte) size;
        outputImage[19] = 0;
        // bytes 22-25 height in pixels
        size = ((imageBytes[23] << 8) | (imageBytes[22] & 0x00FF)) / 24;
        outputImage[22] = (byte) size;
        outputImage[23] = 0;
        saveImage(outputImage, codedImagePath);
    }

    /**
     *
     * @param image witk encode text
     * @return decoded text
     */
    private void decodeText(byte[] image) throws IOException {
        int byteSize = (image.length - 54) / (24 * 24) + 54;
        byte[] outputText = new byte[byteSize];
        int mask = 0x01;
        int index = 0;
        for (int i = 0; i < outputText.length; ++i) {
            // omit the header
            if (i < 54) {
                outputText[i] = image[i];
                ++index;
                continue;
            }
            byte accumulator = 0;
            for (int j = 0; j < 8; ++j) {
                int flag = image[index] & mask;
                accumulator >>= 1;
                if (flag == 1) {
                    accumulator |= 0x80;
                } else {
                    accumulator &= 0x7F;
                }
                ++index;
            }
            outputText[i] = accumulator;
        }

        outputText[2] = (byte) byteSize;
        outputText[3] = (byte) (byteSize >> 8);
        outputText[4] = 0;
        // bytes 18-21 width in pixels
        int size = ((image[19] << 8) | (image[18] & 0x00FF)) / 24;
        outputText[18] = (byte) size;
        outputText[19] = 0;
        // bytes 22-25 height in pixels
        size = ((image[23] << 8) | (image[22] & 0x00FF)) / 24;
        outputText[22] = (byte) size;
        outputText[23] = 0;
        //outputText=outputText.substring()
        saveText(new String(outputText).substring(54,byteSize/100 +400),"./decodedText.txt");//Mógłbym wyświetlać całego stringa ale chyba nie ma sensu
        //System.out.println(new String(outputText).substring(54,byteSize/100 +400));
    }

    static private byte[] getImageBytes(String path) {
        File file = new File(path);
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param byte of image
     * @throws save the image
     */
    public void saveImage(byte[] image, String path) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(image);
            BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
            ImageIO.write(bufferedImage, "bmp", new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param text
     * @param path
     * @throws save the text
     */
    public void saveText(String text, String path)  throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write(text);

        writer.close();
    }

}

```