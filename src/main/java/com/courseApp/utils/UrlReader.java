package com.courseApp.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Helper class for reading url.
 */
public class UrlReader {
    /**
     * URL-reading function
     *
     * @param url URL object of targeted url
     * @return String of the response
     * @throws IOException IO exception
     */
    public static String read(URL url) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;

        try (InputStream in = url.openStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
