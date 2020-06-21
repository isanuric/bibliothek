package com.bib.service;

import static org.junit.Assert.assertEquals;

import com.bib.BibliothekApplicationTests;
import java.io.IOException;
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
    public void getMime_success() throws IOException {
        String[] mimeExpected = algorithmService.getMimeExpected();
        String[] mimeResult = algorithmService.findMime();
        for (int i = 0; i < mimeExpected.length; i++) {
            System.out.println(i + ". " + mimeExpected[i] + ", result " + mimeResult[i]);
            assertEquals(mimeExpected[i], mimeResult[i]);
        }
    }
}
