package com.aidanogrady.cs547.assignment03.results;

import com.aidanogrady.cs547.assignment03.eval.NRPMultiObjEvaluator;
import com.aidanogrady.cs547.assignment03.model.NextReleaseProblem;

import java.util.List;

/**
 * Simple analysis class for converting phenotypes into costs and scores. This
 * was required to convert the results of a single-objective search into the
 * results of a multi-objective search.
 *
 * @author Aidan O'Grady
 * @since 1.0
 */
public class SingleObj {
    public static void main(String[] args) {
        String file = "";
        NextReleaseProblem nrp = new NextReleaseProblem(file, 0.3);
        NRPMultiObjEvaluator e = new NRPMultiObjEvaluator(nrp);

        String[] phenotypes = {
        };

        for (String s : phenotypes) {
            System.out.println(s + "\t" + e.evalCost(s) + "\t" + e.evalScore(s));
        }
    }
}
