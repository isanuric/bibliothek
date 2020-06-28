package com.bib.service;

import static java.util.stream.IntStream.range;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
        callQuicksortRecursive(arr, low, high, lowDynamic, highDynamic);
    }

    private void exchange(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    private void callQuicksortRecursive(int[] arr, int low, int high, int lowDynamic, int highDynamic) {
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
                countRectangle += range(0, yAxis.length).map(yIndex -> (int) range(0, yAxis.length - yIndex)
                        .filter(z -> xDifference == yAxis[z + yIndex] - yAxis[z]).count()).sum();
            }
        }
        return countRectangle;
    }

    public int calculateRectanglesLegacy(int[] xAxis, int[] yAxis) {
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
    public String enigmaEncode(String plaintext, int startingNumber) {
        String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("(?!^)(?=.)");
        String[] text = plaintext.split("(?!^)(?=.)");
        String[] textAfterShift = new String[text.length];

        IntStream.range(0, text.length)
                .forEach(i -> range(0, alphabet.length)
                .filter(j -> text[i].equals(alphabet[j]))
                .forEach(j -> textAfterShift[i] = alphabet[i + j + startingNumber]));

        System.out.println(Arrays.toString(textAfterShift));
        return "";
    }

    private String enigmaEncodeLegacy(String plaintext, int startingNumber) {
        String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("(?!^)(?=.)");
        String[] text = plaintext.split("(?!^)(?=.)");
        String[] textAfterShift = new String[text.length];
        for (int i = 0; i < text.length; i++) {
            for (int j = 0; j < alphabet.length; j++) {
                if (text[i].equals(alphabet[j])) {
                    textAfterShift[i] = alphabet[i + j + startingNumber];
                    continue;
                }
            }
        }
        return "";
    }
}

