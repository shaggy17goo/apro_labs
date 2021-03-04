import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;
import java.util.Scanner;

/**
 * Some CSV file utilities.
 * @author kmi
8
 * @version 1.0.0 13 listopada 2019 3:14
 */
public class CSVFile {
    /**
     * The list of rows read from a file.
     */
    private static List<String[]> rows = new ArrayList<>();

    /**
     * Reads given file as CSV with given separator.
     *
     * @param fileName  the name of the CSV file to read.
     * @param separator the separator used in CSV file.
     */
    public CSVFile(String fileName, String separator) {
        try (BufferedReader br = new BufferedReader(
                new FileReader(fileName))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                rows.add(line.split(separator));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String[] row : rows) {
            for (String value : row) {
                sb.append(value).append(":");
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    /**
     *
     * @param rows List tab String with data of CSV file
     * @param sign new separator
     * @return file "plik.csv" with new separator
     */


    public static void writeCSV(List<String[]> rows, String sign)
    {


        ArrayList<String[]> changedRow=null;
        try {
            File file = new File("plik.csv");
            // create FileWriter object with file as parameter
            PrintWriter outputFile = new PrintWriter(file);
            for(int i=0;i<rows.size();i++) {
                String[] dataLines = rows.get(i);
                String lineRows="";
                for(int j=0; j<dataLines.length;j++) {
                    lineRows+=dataLines[j];
                    if(j<dataLines.length-1){
                        lineRows+=sign;
                    }
                }
                outputFile.println(lineRows);
            }
            outputFile.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("podaj nazwę pliku: ");
        String fileName=scan.nextLine();
        System.out.println("podaj separator: ");
        String separator=scan.nextLine();
        System.out.println(new CSVFile(fileName, separator));
        System.out.println("wprowadź zastępczy separator: ");
        String sign = scan.nextLine();
        writeCSV(rows, sign);
    }
}