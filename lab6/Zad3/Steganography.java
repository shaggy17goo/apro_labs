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