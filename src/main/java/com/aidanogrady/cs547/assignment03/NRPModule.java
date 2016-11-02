package com.aidanogrady.cs547.assignment03;

import com.aidanogrady.cs547.assignment03.eval.NRPMultiObjEvaluator;
import org.opt4j.core.problem.ProblemModule;
import org.opt4j.core.start.Constant;

/**
 * The NRPModule contains the configuration of the Opt4J module, ensuring that
 * the proper classes are bound together.
 *
 * @author Aidan O'Grady
 * @since 0.2
 */
public class NRPModule extends ProblemModule {

    @Constant(value = "file")
    private String file = "C:\\Users\\aidan\\Desktop\\workspace\\CS547-Assignment03\\data\\classic\\nrp1.txt";

    @Constant(value = "costRatio")
    private double costRatio = 0.3;

    @Constant(value = "weight")
    private double weight;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public double getCostRatio() {
        return costRatio;
    }

    public void setCostRatio(double costRatio) {
        this.costRatio = costRatio;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    protected void config() {
        bindProblem(NRPCreator.class, NRPDecoder.class, NRPMultiObjEvaluator.class);
    }
}
