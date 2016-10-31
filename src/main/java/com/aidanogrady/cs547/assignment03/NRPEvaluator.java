package com.aidanogrady.cs547.assignment03;

import com.aidanogrady.cs547.assignment03.model.NextReleaseProblem;
import com.google.inject.Inject;
import org.opt4j.core.Objective;
import org.opt4j.core.Objectives;
import org.opt4j.core.problem.Evaluator;

/**
 * Evaluates the given solution. Each solution is evaluated by its score and
 * cost. The cost of the solution should be low, while the score of the system
 * should be high.
 *
 * @author Aidan O'Grady
 * @since 0.2
 */
public class NRPEvaluator implements Evaluator<String> {
    /**
     * The problem representation.
     */
    private NextReleaseProblem nrp;

    /**
     * Constructs a new NRP evaluator.
     *
     * @param nrp the problem space
     */
    @Inject
    public NRPEvaluator(NextReleaseProblem nrp) {
        this.nrp = nrp;
    }

    @Override
    public Objectives evaluate(String phenotype) {
        Objectives objectives = new Objectives();
        objectives.add("Cost", Objective.Sign.MIN, evalCost(phenotype));
        objectives.add("Score", Objective.Sign.MAX, evalScore(phenotype));
        return objectives;
    }

    /**
     * Evaluates the cost of the given solution. The cost is defined as the sum
     * of each requirements cost.
     *
     * @param phenotype the potential solution
     * @return cost
     */
    private double evalCost(String phenotype) {
        double cost = 0;
        for (int i = 0; i < phenotype.length(); i++) {
            if (phenotype.charAt(i) == '1') {
                cost += nrp.getRequirement(i).getCost();
            }
        }

        if (cost > nrp.getBudget()) {
            cost = Double.MAX_VALUE;
        }
        return cost;
    }

    /**
     * Evaluates the score of the given solution. The score is defined as the
     * sum of weights of each customer who has this solution in their interest.
     *
     * @param phenotype the potential solution
     * @return score
     */
    private double evalScore(String phenotype) {
        double score = 0;
        for (int i = 0; i < phenotype.length(); i++) {
            if (phenotype.charAt(i) == '1') {
                score += nrp.score(i);
            }
        }
        return score;
    }
}
