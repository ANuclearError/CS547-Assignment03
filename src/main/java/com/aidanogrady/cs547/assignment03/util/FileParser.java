package com.aidanogrady.cs547.assignment03.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO: Add Class Desc.
 *
 * @author Aidan O'Grady
 * @since 0.1
 */
public class FileParser {
    public static void readFile(Path path) throws IOException {
        byte[] encoded = Files.readAllBytes(path);
        String content = new String(encoded, Charset.defaultCharset()).trim();
        content = content.replaceAll(" +|\\t", " ");

        String reqRegex = "\\d+\\r\\n(\\d+\\r\\n(\\d+( \\d+)+ \\r\\n))+";
        Pattern pattern = Pattern.compile(reqRegex);
        Matcher matcher = pattern.matcher(content);

        String reqString = "";
        if (matcher.find()) {
            reqString = matcher.group();
        }
        System.out.println("Req: " + reqString.isEmpty());
        content = content.replaceAll(reqString, "");

        String depRegex = "\\d+\\r\\n(\\d+ \\d+\\r\\n)+";
        pattern = Pattern.compile(depRegex);
        matcher = pattern.matcher(content);
        String depString = "";
        if (matcher.find()) {
            depString = matcher.group();
        }
        System.out.println("Dep: " + depString.isEmpty());
        content = content.replaceAll(depString, "");

        String custRegex = "\\d+\\r\\n(\\d+ \\d+( \\d+)+\\r\\n)+";
        pattern = Pattern.compile(custRegex);
        matcher = pattern.matcher(content);
        String custString = "";
        if (matcher.find()) {
            custString = matcher.group();
        }

        System.out.println("Cust: " + custString.isEmpty());
        System.out.println();
    }
}
