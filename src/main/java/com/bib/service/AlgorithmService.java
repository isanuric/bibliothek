package com.bib.service;

import static java.util.stream.IntStream.range;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

/**
 * Some algorithm implementations as experiment.
 */
@Service
public class AlgorithmService {

    @Value("classpath:types.txt")
    Resource resourceFile;

    @Autowired
    ResourceLoader resourceLoader;

    private static final String CLOSETOGETHER_REGEX = "(?!^)(?=.)";
    private static final String[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split(CLOSETOGETHER_REGEX);

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
     * Returns the sum of all indexes of element in text. The index of the first character is 0.
     */
    public int sumOfCharacterIndex(String text, String element) {
        int result = 0;
        char[] charArray = text.toCharArray();
        result += range(0, charArray.length).filter(j -> element.equals(String.valueOf(charArray[j]))).sum();
        return result;
    }

    /**
     * Detects the MIME type of a file based on its name.
     */
    public String[] makeupFileNamesWithMimeTypes() throws IOException {
        String[] typesLines = getInputContent("classpath:mime/types.txt").split("\n");
        String[] inputs = getInputContent("classpath:mime/inputs.txt").split("\n");
        String[] results = new String[typesLines.length];
        String unknown = "UNKNOWN";

        range(0, inputs.length).forEach(i -> {
            String fileName = inputs[i];
            if (fileName == "." || fileName.endsWith(".") || fileName.endsWith("..") || !fileName.contains(".")) {
                results[i] = unknown;
                return;
            }

            Hashtable<String, String> mimes = getStandardMimeTypes(typesLines).stream()
                    .collect(Collectors.toMap(makeup -> makeup[0], makeup -> makeup[1], (a, b) -> b, Hashtable::new));

            String[] splittedFileName = fileName.split("\\.");
            String fileExtention = splittedFileName[splittedFileName.length > 0 ? splittedFileName.length - 1 : 0];
            results[i] = mimes.containsKey(fileExtention) ? mimes.get(fileExtention) : unknown;
        });
        return results;
    }

    private List<String[]> getStandardMimeTypes(String[] splittedMimesLines) {
        return Arrays.stream(splittedMimesLines)
                .map(splittedMimesLine -> splittedMimesLine.split(" "))
                .collect(Collectors.toList());
    }

    private String getInputContent(String path) throws IOException {
        return new String(Files.readAllBytes(resourceLoader.getResource(path).getFile().toPath()));
    }

    String[] getMimeExpected() throws IOException {
        return getInputContent("classpath:mime/expected.txt").split("\n");
    }

    private void makeupFile(String[] results, List<String[]> standardMimeTypes, int mimeIndex, String fileExtention) {
        range(0, standardMimeTypes.size())
                .filter(j -> fileExtention.equalsIgnoreCase(standardMimeTypes.get(j)[0]))
                .forEach(j -> {
                    results[mimeIndex] = standardMimeTypes.get(j)[1];
                    return;
                });
//        for (int j = 0; j < standardMimeTypes.size(); j++) {
//            if (fileExtention.equalsIgnoreCase(standardMimeTypes.get(j)[0])) {
//                results[mimeIndex] = standardMimeTypes.get(j)[1];
//                continue;
//            }
//        }
    }

    /**
     * Sorts array using quick sort algorithm.
     */
    public void quickSort(int[] arr, int low, int high) {
        int lowDynamic = low;
        int highDynamic = high;
        int pivot = arr[low + (high - low) / 2];

        // Divide into two lists
        while (lowDynamic <= highDynamic) {
            // Find first element from the left list that is bigger than pivot.
            while (arr[lowDynamic] < pivot) {
                lowDynamic++;
            }
            // Find first element from the right list that is smaller than pivot.
            while (pivot < arr[highDynamic]) {
                highDynamic--;
            }

            // Exchange founded values from left and right
            if (lowDynamic <= highDynamic) {
                exchange(arr, lowDynamic, highDynamic);
                // Update lowDynamic and highDynamic for recursive call.
                lowDynamic++;
                highDynamic--;
            }
        }
        callQuickSortRecursive(arr, low, high, lowDynamic, highDynamic);
    }

    private void exchange(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    private void callQuickSortRecursive(int[] arr, int low, int high, int lowDynamic, int highDynamic) {
        if (low < highDynamic) {
            quickSort(arr, low, highDynamic);
        }
        if (lowDynamic < high) {
            quickSort(arr, lowDynamic, high);
        }
    }

    /**
     * Rectangle counter.
     */
    public int calculateRectangles(int[] xAxis, int[] yAxis) {
        int countRectangle = 0;
        for (int i = 1; i < xAxis.length; i++) {
            for (int j = 0; j < xAxis.length - i; j++) {
                int xDifference = xAxis[j + i] - xAxis[j];
                // Go through y axis
                countRectangle += range(0, yAxis.length)
                        .map(yIndex -> (int) range(0, yAxis.length - yIndex)
                                .filter(z -> xDifference == yAxis[z + yIndex] - yAxis[z]).count())
                        .sum();
            }
        }
        return countRectangle;
    }

    private int calculateRectanglesSlow(int[] xAxis, int[] yAxis) {
        //  0, 3, 6, 9, 12
        //  3,  ,  ,  ,  ,
        //  6,  ,  ,  ,  ,
        //  9,  ,  ,  ,  ,
        // 12,  ,  ,  ,  ,

        int countRectangle = 0;
        for (int z = 1; z < xAxis.length; z++) {
            for (int i = 0; i < xAxis.length - z; i++) {
                int xDifference = xAxis[i + z] - xAxis[i];

                // Go throw y axis
                for (int yVertical = 0; yVertical < yAxis.length; yVertical++) {

                    int yIndex = yVertical;
                    countRectangle += range(0, yAxis.length - yIndex)
                            .filter(j -> xDifference == yAxis[j + yIndex] - yAxis[j]).count();
                }
            }
        }
        return countRectangle;
    }

    /**
     * Enigma encryption.
     */
    public String enigmaEncode(String plaintext, int startingNumber, String[] rotors) {
        var rotorsSplited = splitRotors(rotors);
        String[] plain = plaintext.split(CLOSETOGETHER_REGEX);
        String[] shiftedPlain = shiftText(plain, startingNumber);
        return String.join("", rotate(shiftedPlain, rotorsSplited, 0));
    }

    private String[] shiftText(String[] plain, int startingNumber) {
        String[] shiftedPlain = new String[plain.length];
        range(0, plain.length).forEach(i -> range(0, ALPHABET.length)
                .filter(j -> plain[i].equals(ALPHABET[j]))
                .forEach(j -> {
                    var index = i + j + startingNumber;
                    while (index >= 26) index = index - 26;
                    shiftedPlain[i] = ALPHABET[index];
                }));
        return shiftedPlain;
    }

    private String[] rotate(String[] text, String[][] rotors, int count) {
        if (count == rotors.length) {
            return text;
        }
        String[] textAfterRotor = new String[text.length];
        int finalCount = count;
        range(0, text.length).forEach(i -> range(0, ALPHABET.length)
                .filter(j -> text[i].equals(ALPHABET[j]))
                .forEach(j -> textAfterRotor[i] = rotors[finalCount][j]));
        count++;
        return rotate(textAfterRotor, rotors, count++);
    }

    public String enigmaDecode(String chiferText, int startingNumber, String[] rotors) {
        var chifer = chiferText.split(CLOSETOGETHER_REGEX);
        var rotate = rotateRevers(chifer, rotors, 0);
        return String.join("", reversShiftText(chifer, rotate, startingNumber));
    }

    private String[] rotateRevers(String[] text, String[] rotors, int count) {
        if (count == rotors.length) {
            return text;
        }
        var rotorsSplited = splitRotors(rotors);
        String[] textAfterRotor = new String[text.length];
        int finalCount = count;
        range(0, text.length).forEach(i -> range(0, ALPHABET.length)
                .filter(j -> text[i].equals(rotorsSplited[finalCount][j]))
                .forEach(j -> textAfterRotor[i] = ALPHABET[j]));
        count++;
        return rotateRevers(textAfterRotor, rotors, count++);
    }

    private String[] reversShiftText(String[] chifer, String[] rotate, int startingNumber) {
        var plain = new String[chifer.length];
        range(0, chifer.length).forEach(i -> range(0, ALPHABET.length)
                .filter(j -> rotate[i].equals(ALPHABET[j]))
                .forEach(j -> {
                    var index = j - i - startingNumber;
                    while (index < 0) index = index + 26;
                    plain[i] = ALPHABET[index];
                }));
        return plain;
    }

    private String[][] splitRotors(String[] rotors) {
        String[][] rotorsSplited = new String[3][];
        range(0, rotors.length).forEach(j -> rotorsSplited[j] = rotors[j].split(CLOSETOGETHER_REGEX));
        return rotorsSplited;
    }
}
