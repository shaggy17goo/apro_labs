package com.company;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CSVReaderTest {

    // Testy czy wystąpił dany wyjątek, jeśli wystąpił test jest pozytywny
    @DisplayName("NoColumnNamesException?")
    @Test
    void NoColumnNamesException() {
          CSVReader csvReader = new CSVReader("oscar_age_male.csv", ",");
           assertThrows(NoColumnNamesException.class, csvReader::read);
    }

    @DisplayName("NotAConsistantNumberOfColumnsException?")
    @Test
    void NotAConsistantNumberOfColumnsException() {
        CSVReader csvReader = new CSVReader("oscar_age_male.csv", ",");
        assertThrows(NotAConsistantNumberOfColumnsException.class, csvReader::read);
    }

    @DisplayName("NotACSVFileException?")
    @Test
    void NotACSVFileException() {
        CSVReader csvReader = new CSVReader("oscar_age_male.csv", ",");
        assertThrows(NotACSVFileException.class, csvReader::read);
    }





}