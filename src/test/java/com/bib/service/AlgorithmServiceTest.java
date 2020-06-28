package com.bib.service;

import static org.junit.Assert.assertEquals;

import com.bib.BibliothekApplicationTests;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.IntStream;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AlgorithmServiceTest extends BibliothekApplicationTests {

    @Autowired
    private AlgorithmService algorithmService;

    @Test
    public void getClosestToValueZero() {
       assertEquals(1, algorithmService.getClosestToZero(new int[]{1}));
    }

    @Test
    public void getClosestToValueZero_allPositive() {
       assertEquals(3, algorithmService.getClosestToZero(new int[]{7, 12, 54, 8, 3, 1233, 11}));
    }

    @Test
    public void getClosestToValueZero_positiveAndNegative() {
       assertEquals(2, algorithmService.getClosestToZero(new int[]{7, 12, 54, 8, -3, 2, -1233, 11, -43, }));
    }

    @Test
    public void getClosestToValueZero_positiveEqualNegative() {
       assertEquals(3, algorithmService.getClosestToZero(new int[]{7, 12, 54, 3, -3, -1233, 11, -43, }));
    }

    @Test
    public void getClosestToValueZero_negativeEqualNegative() {
       assertEquals(-3, algorithmService.getClosestToZero(new int[]{7, 12, 54, -3, -3, -1233, 11, -43, }));
    }

    @Test
    public void mimeType_success() throws IOException {
        String[] mimeExpected = algorithmService.getMimeExpected();
        String[] mimeResult = algorithmService.makeupFileNamesWithMimeTypes();
        IntStream.range(0, mimeExpected.length).forEach(i -> assertEquals(mimeExpected[i], mimeResult[i]));
    }

    @Test
    public void quickSort() {
        int[] toBeSorted = {9, 6, 7, 4, 2, 5, 1, 3, 0, 8, 32, 76, 98, 34, 876, 5, 1, 398, 78, 27, 85, 39, 20, 45, 2876};
        System.out.println(Arrays.toString(toBeSorted));
        algorithmService.quickSort(toBeSorted, 0, toBeSorted.length - 1);

        System.out.println(Arrays.toString(toBeSorted));
        int[] expected = {0, 1, 1, 2, 3, 4, 5, 5, 6, 7, 8, 9, 20, 27, 32, 34, 39, 45, 76, 78, 85, 98, 398, 876, 2876};
        assertEquals(Arrays.toString(expected), Arrays.toString(toBeSorted));
    }

    @Test
    public void sumOfCharacterIndex() {
        String testString = "pelicann";
        int n = algorithmService.sumOfCharacterIndex(testString, "n");
        System.out.println(n);

    }

    @Test
    public void calculateRectangles_Simple() {
        assertEquals(4, algorithmService.calculateRectangles(new int[]{0, 2, 5, 10}, new int[]{0, 3, 5}));
    }

    @Test
    public void calculateRectangles_Square() {
        assertEquals(14, algorithmService.calculateRectangles(new int[]{0, 3, 6, 9}, new int[]{0, 3, 6, 9}));
    }

    @Test
    public void calculateRectangles_SquareLength5() {
        assertEquals(30, algorithmService.calculateRectangles(new int[]{0, 3, 6, 9, 12}, new int[]{0, 3, 6, 9, 12}));
    }

    @Test
    public void calculateRectangles_Big() {
        int[] xAxis = new int[]{0, 11, 25, 26, 29, 30, 40, 44, 56, 65, 71, 87, 98, 100, 108, 130, 149, 153, 161, 173,
                179, 200};
        int[] yAxis = new int[]{0, 1, 11, 16, 17, 19, 37, 38, 53, 65, 69, 100};
        int sum = Arrays.stream(xAxis).sum();
        assertEquals(123, algorithmService.calculateRectangles(xAxis, yAxis));
    }
}
