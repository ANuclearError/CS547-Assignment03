package com.aidanogrady.cs547.assignment03;

import com.aidanogrady.cs547.assignment03.eval.NRPSingleObjEvaluator;
import org.opt4j.core.problem.ProblemModule;
import org.opt4j.core.start.Constant;

/**
 * The single objective version of the NRP Problem module. The Single Objective
 * module requires an additional field for weighing the two fitness functions
 * and the use of a different evaluator.
 *
 * @author Aidan O'Grady
 * @since 0.3
 */
public class NRPSingleObjModule extends ProblemModule {
    /**
     * The file to read data from.
     */
    @Constant(value = "file")
    private String file = "C:\\Users\\aidan\\Desktop\\workspace\\CS547-Assignment03\\data\\classic\\nrp1.txt";

    /**
     * The cost ratio used to determine budget.
     */
    @Constant(value = "costRatio")
    private double costRatio = 0.3;

    /**
     * The weight that should be applied to the score fitness function.
     * The cost fitness function's weight is 1 - this value.
     */
    @Constant(value = "weight")
    private double weight = 0.7;

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

    /**
     * Returns the weight defined for this module run.
     *
     * @return weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the weight to the given value.
     *
     * @param weight the new weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    protected void config() {
        bindProblem(NRPCreator.class, NRPDecoder.class, NRPSingleObjEvaluator.class);
    }
}
