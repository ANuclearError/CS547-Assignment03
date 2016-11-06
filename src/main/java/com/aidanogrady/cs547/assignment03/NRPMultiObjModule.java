package com.aidanogrady.cs547.assignment03;

import com.aidanogrady.cs547.assignment03.eval.NRPMultiObjEvaluator;
import org.opt4j.core.problem.ProblemModule;
import org.opt4j.core.start.Constant;

/**
 * The NRPMultiObjModule is the multi-objective solution to the NRP Problem,
 * using an evaluator that considers the separate objectives.
 *
 * @author Aidan O'Grady
 * @since 0.3
 */
public class NRPMultiObjModule extends ProblemModule {
    /**
     * The file to read data from.
     */
    @Constant(value = "file")
    private String file = "";

    /**
     * The cost ratio used to determine budget.
     */
    @Constant(value = "costRatio")
    private double costRatio = 0.3;

    /**
     * Returns the filename.
     *
     * @return file
     */
    public String getFile() {
        return file;
    }

    /**
     * Sets the filename to the given string.
     *
     * @param file the new filename to read from.
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     * Returns the cost ratio of this module.
     *
     * @return cost ratio
     */
    public double getCostRatio() {
        return costRatio;
    }

    /**
     * Sets the cost ratio to the given value.
     *
     * @param costRatio the new cost ratio.
     */
    public void setCostRatio(double costRatio) {
        this.costRatio = costRatio;
    }

    @Override
    protected void config() {
        bindProblem(NRPCreator.class, NRPDecoder.class, NRPMultiObjEvaluator.class);
    }
}
