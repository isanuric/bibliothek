package com.bib.service;

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

    /**
     * Detects the MIME type of a file based on its name.
     */
    public String[] makeupFileNamesWithMimeTypes() throws IOException {
        String[] typesLines = getInputContent("classpath:mime/types.txt").split("\n");
        String[] inputs = getInputContent("classpath:mime/inputs.txt").split("\n");
        String[] results = new String[typesLines.length];
        String unknown = "UNKNOWN";

        IntStream.range(0, inputs.length).forEach(i -> {
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

    String[] getMimeExpected() throws IOException {
        return getInputContent("classpath:mime/expected.txt").split("\n");
    }

    private String getInputContent(String path) throws IOException {
        return new String(Files.readAllBytes(resourceLoader.getResource(path).getFile().toPath()));
    }

    private void makeupFile(String[] results, List<String[]> standardMimeTypes, int mimeIndex, String fileExtention) {
        IntStream.range(0, standardMimeTypes.size())
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
    public int[] quickSort(int[] arr) {
        return null;
    }
}
