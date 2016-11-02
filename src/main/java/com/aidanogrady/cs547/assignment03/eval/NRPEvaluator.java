package com.aidanogrady.cs547.assignment03.eval;

import com.aidanogrady.cs547.assignment03.model.NextReleaseProblem;
import org.opt4j.core.problem.Evaluator;

/**
 * TODO: Add Class Desc.
 *
 * @author Aidan O'Grady
 * @since TODO: Add Version
 */
abstract class NRPEvaluator implements Evaluator<String> {
    /**
     * The problem representation.
     */
    private NextReleaseProblem nrp;

    /**
     * Constructs a new NRPEvaluator.
     *
     * @param nrp the problem space
     */
    NRPEvaluator(NextReleaseProblem nrp) {
        this.nrp = nrp;
    }

    /**
     * Evaluates the cost of the given solution. The cost is defined as the sum
     * of each requirements cost.
     *
     * @param phenotype the potential solution
     * @return cost
     */
    double evalCost(String phenotype) {
        double cost = 0;
        for (int i = 0; i < phenotype.length(); i++) {
            if (phenotype.charAt(i) == '1') {
                cost += nrp.getRequirement(i).getCost();
            }
        }

        return cost - nrp.getBudget();
    }

    /**
     * Evaluates the score of the given solution. The score is defined as the
     * sum of weights of each customer who has this solution in their interest.
     *
     * @param phenotype the potential solution
     * @return score
     */
    double evalScore(String phenotype) {
        double score = 0;
        for (int i = 0; i < phenotype.length(); i++) {
            if (phenotype.charAt(i) == '1') {
                score += nrp.score(i);
            }
        }
        return score;
    }
}
