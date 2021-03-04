package com.company;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SampleTest {
    @DisplayName("Dzielenie całkowite")
    @Test
    void divide1() {
        assertEquals(3.0,Sample.divide(15,4));
    }


    @DisplayName("Dzielenie Wielu liczb")
    @Test
    void divide2() {

        for(int i=1;i<50;i++)
            for(int j=1;j<100;j++)
                assertEquals(i/j ,Sample.divide(i,j));
    }

    @DisplayName("Dzielenie zmiennoprzecinkowe")
    @Test
    void divide3() {
        assertEquals(3.0,Sample.divide(15.0,5));
    }

    @Test
    void testExpectedException() {

        assertThrows(ArithmeticException.class, () -> Sample.divide(2,0));
    }
    @ParameterizedTest
    @MethodSource("Provider")
    void divide(int a, int b, int result) {
        assertEquals(result, Sample.divide(a,b));
    }

    private static Stream<Arguments> Provider() {
        return Stream.of(
                Arguments.of(1, 1, 1),
                Arguments.of(2, 3, 0),
                Arguments.of(0, 1, 0),
                Arguments.of(2, 8, 0),
                Arguments.of(8, 4, 2),
                Arguments.of(16, 4, 4),
                Arguments.of(15, 4, 3),
                Arguments.of(21, 3, 7)
        );
    }

}