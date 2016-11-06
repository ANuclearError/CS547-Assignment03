package com.aidanogrady.cs547.assignment03.eval;

import com.aidanogrady.cs547.assignment03.model.NextReleaseProblem;
import com.google.inject.Inject;
import org.opt4j.core.Objective;
import org.opt4j.core.Objectives;

/**
 * Evaluates the given solution.
 *
 * Each solution is evaluated by its score and cost. The cost of the solution
 * should be low, while the score of the system should be high.
 *
 * @author Aidan O'Grady
 * @since 0.2
 */
public class NRPMultiObjEvaluator extends NRPEvaluator {
    /**
     * Constructs a new NRP evaluator.
     *
     * @param nrp the problem space
     */
    @Inject
    public NRPMultiObjEvaluator(NextReleaseProblem nrp) {
        super(nrp);
    }

    @Override
    public Objectives evaluate(String phenotype) {
        Objectives objectives = new Objectives();
        objectives.add("Score", Objective.Sign.MAX, evalScore(phenotype));
        objectives.add("Cost", Objective.Sign.MAX, evalCost(phenotype));
        return objectives;
    }

}
