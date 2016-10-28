package com.aidanogrady.cs547.assignment03;

import com.aidanogrady.cs547.assignment03.util.FileParser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Main application class for the system.
 *
 * @author Aidan O'Grady
 * @since 0.0
 */
public class Application {
    public static void main(String[] args) throws IOException {
        System.out.println("CS547 Assignment 03: Requirements Prioritisation");
        System.out.println("Author: Aidan O'Grady (201218150)");
        System.out.println();


        String fileName = "data/classic/nrp3.txt";
        File file = new File(fileName);
        FileParser.readFile(file.toPath());
    }
}
