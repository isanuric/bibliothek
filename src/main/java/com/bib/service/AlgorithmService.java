package com.bib.service;

import org.springframework.stereotype.Service;

@Service
public class AlgorithmService {

    /**
     * Returns the value closest to 0 among input data. If two numbers are equally close to zero, positive integer has
     * to be considered closest to zero.
     */
    public int getClosestToZero(int[] array) {
        int closest = 1000000000;
        for (int i = 0; i < array.length; i++) {
            int currentValue = array[i];

            if (Math.abs(currentValue) < Math.abs(closest)) {
                closest = currentValue;
            } else if (Math.abs(currentValue) == Math.abs(closest)) {
                if (currentValue + closest == 0) {
                    closest = Math.abs(currentValue);
                }
            }

        }
        return closest;
    }

    /**
     * Returns the sum of all indexes of toFindChar in text. The index of the first character is 0.
     */
    public int sumOfCharacterIndex(String text, String toFindChar) {
        int index = 0;
        int result = 0;

        for (char ch : text.toCharArray()) {
            if (toFindChar.equals(String.valueOf(ch))) {
                System.err.println(ch);
                result += index;
            }
            index += 1;
        }
        return result;
    }


}
