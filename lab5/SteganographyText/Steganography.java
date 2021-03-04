package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;


public class Steganography {
    public static void main(String[] arguments) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String codedString;
        Steganography steganography = new Steganography();

        String inputPath = "./first.bmp";
        String outputPath = "./output.bmp";
        String textPath = "./decodedText.txt";
                System.out.println("Input text that you want to encode:");
                codedString=reader.readLine();
                byte[] encoded = steganography.encode(getImageBytes(inputPath), codedString);
                steganography.saveImage(encoded, outputPath);
                steganography.decodeText(getImageBytes(outputPath));
                System.out.println("Encoded text to image and decoded it to decodedText.txt");
        }

    public byte[] encode(byte[] image, String toAdd) {
        byte[] secretMessage = toAdd.getBytes();
        addHiddenText(image, secretMessage);
        return image;
    }

    private void addHiddenText(byte[] image, byte[] addition) {
        int bitValue, byteValue;
        int k=54,length=addition.length;
        for(int i=54; i<image.length; i++){
            if(length==0){
                break;
            }
            byteValue=addition[i-54];
            for(int j=0; j<8;j++){
                bitValue=(byteValue>>>j)&0x01;

                image[k]=(byte)(image[k]>>>1);
                image[k]=(byte)(image[k]<<1);
                image[k]=(byte)(image[k] | bitValue);
                k++;
            }
            length--;
        }
    }

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

    public void saveImage(byte[] image, String path) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(image);
            BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
            ImageIO.write(bufferedImage, "bmp", new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveText(String text, String path)  throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write(text);

        writer.close();
    }

}


