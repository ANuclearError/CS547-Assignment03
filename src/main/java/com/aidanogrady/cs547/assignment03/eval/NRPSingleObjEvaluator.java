package com.aidanogrady.cs547.assignment03.eval;

import com.aidanogrady.cs547.assignment03.model.NextReleaseProblem;
import com.google.inject.Inject;
import org.opt4j.core.Objective;
import org.opt4j.core.Objectives;
import org.opt4j.core.start.Constant;

/**
 * A Single Objective evaluator for NRP. A single fitness value is calculated
 * using the weighted-sum fitness.
 *
 * @author Aidan O'Grady
 * @since 0.2
 */
public class NRPSingleObjEvaluator extends NRPEvaluator {
    /**
     * The weight used for the weighted-sum fitness.
     */
    private double weight;

    /**
     * Constructs a new NRPEvaluator.
     *
     * @param nrp the problem space
     */
    @Inject
    public NRPSingleObjEvaluator(NextReleaseProblem nrp,
                                 @Constant(value="weight") double weight) {
        super(nrp);
        if (weight > 0 && weight < 1) {
            this.weight = weight;
        }
        else {
            System.out.println("Invalid weight, assuming 0.5");
            this.weight = 0.5;
        }
    }

    @Override
    public Objectives evaluate(String phenotype) {
        Objectives objectives = new Objectives();
        objectives.add("Fitness", Objective.Sign.MAX, eval(phenotype));
        return objectives;
    }

    /**
     * Evaluates the Single objective fitness of this solution candidate.
     *
     * @param phenotype the solution being evaluated.
     * @return fitness
     */
    private double eval(String phenotype) {
        double fitness = weight * evalScore(phenotype);
        fitness -= (1 - weight) * evalCost(phenotype);
        return fitness;
    }
}
