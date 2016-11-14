package com.aidanogrady.cs547.assignment03.eval;

import com.aidanogrady.cs547.assignment03.model.NextReleaseProblem;
import org.opt4j.core.problem.Evaluator;

/**
 * The NRPEvaluator is an abstract class that contains the various evaluation
 * methods required for form fitness functions. Since both single and multiple
 * objective evaluation require these calculations, the abstract class contains
 * these implementations.
 *
 * @author Aidan O'Grady
 * @since 0.3
 */
abstract class NRPEvaluator implements Evaluator<String> {
    /**
     * The problem representation.
     */
    private final NextReleaseProblem nrp;

    /**
     * Constructs a new NRPEvaluator.
     *
     * @param nrp the problem space
     */
    NRPEvaluator(NextReleaseProblem nrp) {
        this.nrp = nrp;
    }

    /**
     * Evaluates the score of the given solution. The score is defined as the
     * sum of weights of each customer who has this solution in their interest.
     *
     * @param phenotype the potential solution
     * @return score
     */
    public double evalScore(String phenotype) {
        double score = 0;
        for (int i = 0; i < phenotype.length(); i++) {
            if (phenotype.charAt(i) == '1') {
                score += nrp.score(i);
            }
        }
        return score;
    }

    /**
     * Evaluates the cost of the given solution. The cost is defined as the sum
     * of each requirements cost.
     *
     * @param phenotype the potential solution
     * @return cost
     */
    public double evalCost(String phenotype) {
        double cost = 0;
        for (int i = 0; i < phenotype.length(); i++) {
            if (phenotype.charAt(i) == '1') {
                cost += nrp.getRequirement(i).getCost();
            }
        }
        // Cost is reflected as negated percentage of budget.
        return 0 - (cost / nrp.getCostTotal());
    }
}
