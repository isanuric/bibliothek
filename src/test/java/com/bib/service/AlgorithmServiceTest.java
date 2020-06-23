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
        int[] toBeSorted = {9, 6, 7, 4, 2, 5, 1, 3, 0, 8};
        System.out.println(Arrays.toString(toBeSorted));
        algorithmService.quickSort(toBeSorted, 0, toBeSorted.length - 1);

        System.out.println(Arrays.toString(toBeSorted));
        int[] expected = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertEquals(Arrays.toString(expected), Arrays.toString(toBeSorted));
    }

    @Test
    public void sumOfCharacterIndex() {
        String testString = "pelicann";
        int n = algorithmService.sumOfCharacterIndex(testString, "n");
        System.out.println(n);

    }
}
