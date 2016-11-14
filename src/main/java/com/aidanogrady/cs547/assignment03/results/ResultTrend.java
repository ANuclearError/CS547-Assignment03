package com.aidanogrady.cs547.assignment03.results;

import com.aidanogrady.cs547.assignment03.model.NextReleaseProblem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Add Class Desc
 *
 * @author Aidan O'Grady
 * @since 0.0
 */
public class ResultTrend {


    public static void main(String[] args) {
        String file = "data/classic/nrp1.txt";
        String resultsFile = "data/results/nrp1.txt";

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

        for (Result result : results) {
            if (result.cost > -1.10 && result.cost < -0.90) {
                int count = result.phenotype.replaceAll("0", "").length();
                System.out.println(result.phenotype + " " + result.cost + " " + count);
                sublsit.add(result);
            }
        }

        int count = 0;
        for (int i = 0; i < 100; i++) {
            boolean all = true;
            for (Result result : sublsit) {
                if (result.phenotype.charAt(i) != '1')
                    all = false;
            }
            if (all) {
                count++;
                System.out.print(i + " ");
            }
        }
        System.out.println(count);
    }
}

class Result {
    String phenotype;
    double cost;
    double score;

    public Result(String line) {
        String[] split = line.split("\t");
        phenotype = split[1];
        cost = Double.parseDouble(split[2]);
        score = Double.parseDouble(split[3]);
    }
}
