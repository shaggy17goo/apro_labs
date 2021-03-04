package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    private List<String[]> rows = new ArrayList<>();
    private String fileName;
    private String separator;

    public CSVReader(String fileName, String separator) {
        this.fileName = fileName;
        this.separator = separator;
    }

    private boolean doesItHaveNames(String line) {
        boolean result=true;
        String[] names=line.split(separator);
        for(int i=0;i<names.length;i++){
            if(!(names[i].substring(0,2).equals(" \"") || names[i].substring(0,1).equals("\"")) || !names[i].substring(names[i].length()-1).equals("\""))
                result=false;//sprawdzam czy pierwszym i ostatnim znakiem każdego bloku są cudzysłowa, ewentualnie spacja i cudzysłów
        }

        return result;
    }
    private int howManyColumns(String line) {
        String[] names=line.split(separator);
        return names.length;
    }
    public List<String[]> read() throws NotACSVFileException, NoColumnNamesException, NotAConsistantNumberOfColumnsException {
        if(!fileName.substring(fileName.length()-3).equals("csv")) throw new NotACSVFileException("Not a .csv file");
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = "";
            int index=0;
            int numberOfColumns=0;
            while ((line = br.readLine()) != null) {
                if(index==0 &&!doesItHaveNames(line)) throw new NoColumnNamesException("No column names");

                if(index==0)  numberOfColumns=howManyColumns(line);
                else if(numberOfColumns!=howManyColumns(line)) throw new NotAConsistantNumberOfColumnsException("File does not have a consistant number of columns");//file musn't have commas in the fields
                rows.add(line.split(separator));
                index++;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return rows;
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

    public static void main(String[] args) throws NotACSVFileException, NoColumnNamesException, NotAConsistantNumberOfColumnsException {
        CSVReader csvReader = new CSVReader("oscar_age_male.csv", ",");
        System.out.println(csvReader.read());
    }
}
