import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
public class Steganography {
    public static void main(String[] arguments) {
        Steganography steganography = new Steganography();
        String inputPath = "/home/mion/s/241/mwawrzyn/IdeaProjects/Kodowanie/first.bmp";
        String secretImagePath = "/home/mion/s/241/mwawrzyn/IdeaProjects/Kodowanie/second.bmp";
        String outputPath = "./output.bmp";
        byte[] encoded = steganography.encode(inputPath, secretImagePath);
        steganography.saveImage(encoded, outputPath);
        String output_decoded = "./decoded.bmp";
        steganography.decodeImage(outputPath, output_decoded);
    }
    private void addHiddenImage(byte[] image, byte[] addition) {
        int mask=0b00000001;
        int k=54;
        for(int i=54; i<image.length;){
            if(k==addition.length)
                break;
            for(int j=0; j<=7;j++) {
                image[i] = (byte) (image[i] & ~mask);
                image[i] = (byte) (image[i] | (addition[k] >> j & mask));
                i++;
            }
            k++;
        }
    }


    public byte[] encode(String imagePath, String secretImagePath) {
        byte[] imageBytes = getImageBytes(imagePath);
        byte[] secretImageBytes = getImageBytes(secretImagePath);
        addHiddenImage(imageBytes, secretImageBytes);
        return imageBytes;
    }
    public void decodeImage(String imagePath, String codedImagePath) {
        byte[] imageBytes = getImageBytes(imagePath);
// 54 - length of the BMP header
// 24 -> because of the 8 bytes for one pixel and 3 channels (RGB)
        int byteSize = (imageBytes.length - 54)/(24 * 24) + 54;
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
                }
                else {
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
}
