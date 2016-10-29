package com.aidanogrady.cs547.assignment03.util;

import com.aidanogrady.cs547.assignment03.Requirement;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the file parsing of the requirements provided. It ensures
 * that the required objects can be created from the file, and stores these
 * objects to be used in the prioritisation.
 *
 * @author Aidan O'Grady
 * @since 0.1
 */
public class FileParser {
    /**
     * The path of the file to read the data from.
     */
    private Path path;

    /**
     * The list of requirements gathered from the file.
     */
    private List<Requirement> requirements;

    /**
     * Constructs a new file parser.
     *
     * @param path the file to parse
     * @throws FileNotFoundException the provided file doesn't exist.
     */
    public FileParser(Path path) throws FileNotFoundException {
        this.path = path;
        File file = path.toFile();
        if (!file.isFile()) {
            throw new FileNotFoundException();
        }
    }

    /**
     * Attempts to read and parse the file, returning whether it was successful
     * or not.
     *
     * @return true if success, otherwise false.
     * @throws IOException there is a problem parsing the file.
     */
    public boolean readFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path.toFile()));
        List<String> lines = new ArrayList<>();
        String line = br.readLine();
        while (line != null) {
            if (line.length() > 0) {
                lines.add(line);
            }
            line = br.readLine();
        }

        // Start and end points for the three segments of the file.
        int levels = Integer.parseInt(lines.get(0));
        int depStart = levels * 2 + 1;
        int numDeps = Integer.parseInt(lines.get(depStart));
        int custStart = depStart + numDeps + 1;
        int numCusts = Integer.parseInt(lines.get(custStart));
        int end = custStart + numCusts + 1;

        // Split file into three lists for each segment
        List<String> reqLines = lines.subList(1, depStart);
        List<String> depLines = lines.subList(depStart + 1, custStart);
        List<String> custLines = lines.subList(custStart + 1, end);

        requirements = reqs(reqLines, levels);
        if (requirements == null) {
            return false;
        }
        requirements = deps(depLines,requirements);
        return true;
    }

    /**
     * Parses the list of requirements from the file, returning the list
     * obtained.
     *
     * @param lines the lines that correspond to the requirements
     * @param levels the number of levels of requirements possible.
     * @return list of requirements
     */
    private List<Requirement> reqs(List<String> lines, int levels) {
        List<Requirement> requirements = new ArrayList<>();
        for (int i = 1; i <= levels; i++) {
            int size = Integer.parseInt(lines.get((i - 1) * 2));
            String[] arr = lines.get(i * 2 - 1).split("\\s");

            if (size != arr.length) {
                System.out.println("Given size does not match actual size.");
                return null;
            }

            for (String s : arr) {
                requirements.add(new Requirement(i, Integer.parseInt(s)));
            }
        }
        return requirements;
    }

    /**
     * Returns an update of the given requirements to include the dependencies
     * obtained from the given list of files.
     *
     * @param lines the dependencies to update the requirements with
     * @param reqs the requirements to be updated
     * @return the updated requirements
     */
    private List<Requirement> deps(List<String> lines, List<Requirement> reqs) {
        for (String line : lines) {
            String[] arr = line.split("\\s");
            if (arr.length != 2) {
                System.out.println("Invalid format on: '" + line + "'");
            } else {
                int firstID = Integer.parseInt(arr[0]) - 1;
                int secondID = Integer.parseInt(arr[1]) - 1;
                // Probably shouldn't assume ID and index match.
                reqs.get(firstID).addDependency(reqs.get(secondID));
            }
        }
        return reqs;
    }

    /**
     * Returns the requirements parsed by this file.
     * @return requirements
     */
    public List<Requirement> getRequirements() {
        return requirements;
    }
}
