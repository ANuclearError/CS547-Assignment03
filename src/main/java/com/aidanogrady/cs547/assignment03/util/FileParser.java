package com.aidanogrady.cs547.assignment03.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO: Add Class Desc.
 *
 * @author Aidan O'Grady
 * @since 0.1
 */
public class FileParser {
    public static void readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        String content = new String(encoded, Charset.defaultCharset());

        String reqRegex = "\\d+\\r\\n(\\d+\\r\\n(\\d+(\\s\\d+)+\\s\\r\\n))+";
        Pattern pattern = Pattern.compile(reqRegex);
        Matcher matcher = pattern.matcher(content);

        String reqString = "";
        if (matcher.find()) {
            reqString = matcher.group();
        }
        System.out.println(reqString);
    }
}
