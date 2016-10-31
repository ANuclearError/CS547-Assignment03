package com.aidanogrady.cs547.assignment03.util;

import com.aidanogrady.cs547.assignment03.model.Customer;
import com.aidanogrady.cs547.assignment03.model.Requirement;

import java.io.*;
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
    private File file;

    /**
     * The list of requirements gathered from the file.
     */
    private List<Requirement> requirements;

    /**
     * The customers obtained from the file.
     */
    private List<Customer> customers;

    /**
     * Constructs a new file parser.
     *
     * @param file the file to parse
     */
    public FileParser(File file) {
        this.file = file;
    }

    /**
     * Attempts to read and parse the file, returning whether it was successful
     * or not.
     *
     * @return true if success, otherwise false.
     */
    public boolean readFile() {
        List<String> lines = new ArrayList<>();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                if (line.length() > 0) {
                    lines.add(line);
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            return false;
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

        requirements(reqLines, levels);
        dependecies(depLines);
        customers(custLines);
        return true;
    }

    /**
     * Parses the list of requirements from the file, returning the list
     * obtained.
     *
     * @param lines the lines that correspond to the requirements
     * @param levels the number of levels of requirements possible.
     */
    private void requirements(List<String> lines, int levels) {
        requirements = new ArrayList<>();
        for (int i = 1; i <= levels; i++) {
            int size = Integer.parseInt(lines.get((i - 1) * 2));
            String[] arr = lines.get(i * 2 - 1).split("\\s");

            if (size != arr.length) {
                System.out.println("Given size does not match actual size.");
                break;
            }

            for (String s : arr) {
                requirements.add(new Requirement(i, Integer.parseInt(s)));
            }
        }
    }

    /**
     * Performs an update of the given requirements to include the dependencies
     * obtained from the given list of files.
     *
     * @param lines the dependencies to update the requirements with
     */
    private void dependecies(List<String> lines) {
        for (String line : lines) {
            String[] arr = line.split("\\s");
            if (arr.length != 2) {
                System.out.println("Invalid format on: '" + line + "'");
            } else {
                int firstID = Integer.parseInt(arr[0]) - 1;
                int secondID = Integer.parseInt(arr[1]) - 1;
                // Probably shouldn't assume ID and index match.
                Requirement req = requirements.get(secondID);
                requirements.get(firstID).addDependency(req);
            }
        }
    }

    /**
     * Extracts the customer information from the related lines taken from the
     * file, parsing the file to ensure create formatting of each customer.
     *
     * @param lines the lines from file that define customers.
     */
    private void customers(List<String> lines) {
        customers = new ArrayList<>();

        double weightSum = 0;
        for (String line : lines) {
            String[] arr = line.split(" ");
            double weight = Double.parseDouble(arr[0]);
            int reqSize = Integer.parseInt(arr[1]);
            List<Integer> reqs = new ArrayList<>();
            for (int i = 0; i < reqSize; i++) {
                int id = Integer.parseInt(arr[i + 2]);
                if (requirements.size() > id) {
                    reqs.add(id);
                }
            }
            customers.add(new Customer(weight, reqs));
            weightSum += weight;
        }

        // Balance the weights so they sum to 1 (or 0.999...)
        for (Customer cust : customers) {
            double newWeight = cust.getWeight() / weightSum;
            cust.setWeight(newWeight);
        }
    }

    /**
     * Returns the requirements parsed by this file.
     *
     * @return requirements
     */
    public List<Requirement> getRequirements() {
        return requirements;
    }

    /**
     * Returns the customers parsed by this file.
     *
     * @return customers
     */
    public List<Customer> getCustomers() {
        return customers;
    }
}
