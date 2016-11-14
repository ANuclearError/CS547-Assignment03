package com.aidanogrady.cs547.assignment03.results;

import com.aidanogrady.cs547.assignment03.model.Customer;
import com.aidanogrady.cs547.assignment03.model.NextReleaseProblem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class was used to assist in the analysis of results by extracting
 * interesting trends in the results, such as the average number of requirements
 * selected in a solution, or the percentage of customers the result satisfies.
 *
 * @author Aidan O'Grady
 * @since 1.0
 */
public class ResultTrend {
    /**
     * The hardcoded number of requirements.
     */
    private static int requirements;

    /**
     * Main method, a bit more spaghetti code due to the nature of being used
     * for result analysis.
     *
     * @param args program args.
     */
    public static void main(String[] args) {
        String file = "";
        String resultsFile = "";

        NextReleaseProblem nrp = new NextReleaseProblem(file, 0.3);

        List<Result> results = new ArrayList<>();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(resultsFile));
            String line = br.readLine();
            while (line != null) {
                Result res = new Result(line);
                results.add(res);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Oh dear");
        }

        List<Result> sublsit = new ArrayList<>();

        double averageCusts = 0;
        double averageReqs = 0;
        double averageScore = 0;

        // Focus on results whsoe cost are similar to budget.
        for (Result result : results) {
            if (result.cost > -0.325 && result.cost < -0.275) {
                sublsit.add(result);
                String p = result.phenotype;

                // Extract info
                int count = p.replaceAll("0", "").length();
                double satisfied = satisfiedCusts(p, nrp);
                averageCusts += satisfied;
                averageReqs += count;
                averageScore += result.score;

                // Display
                System.out.println(p + " " + result.cost + " " + count + " " + result.score + " " + satisfied);
            }
        }

        // Calc and display averages
        averageCusts /= sublsit.size();
        averageReqs /= sublsit.size();
        averageScore /= sublsit.size();
        System.out.println("Average customer: " + averageCusts);
        System.out.println("Average reqs: " + averageReqs);
        System.out.println("Average score: " + averageScore);

        // Discover requirements found in all results.
        int count = 0;
        for (int i = 0; i < 3502; i++) {
            boolean all = true;
            for (Result result : sublsit) {
                if (result.phenotype.charAt(i) != '1')
                    all = false;
            }
            if (all) {
                count++;
                System.out.print((i + 1) + " ");
            }
        }
        System.out.println(count);
    }

    /**
     * Returns the number of satisfied customers for a given result and
     * next release problem space.
     * @param p the result being analysed
     * @param nrp the search space
     * @return satisfied customers
     */
    private static double satisfiedCusts(String p, NextReleaseProblem nrp) {
        Set<Customer> custs = new HashSet<>();

        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '1') {
                for (Customer c : nrp.getCustomers()) {
                    if (c.hasRequirement(i) && !custs.contains(c)) {
                        custs.add(c);
                    }
                }
            }
        }
        return custs.size();
    }
}

/**
 * Wrapper class for storing result data, splitting given string into each
 * value.
 */
class Result {
    String phenotype;
    double cost;
    double score;

    public Result(String line) {
        String[] split = line.split("\t");
        phenotype = split[0];
        cost = Double.parseDouble(split[1]);
        score = Double.parseDouble(split[2]);
    }
}
