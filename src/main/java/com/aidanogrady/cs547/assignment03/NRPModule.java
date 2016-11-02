package com.aidanogrady.cs547.assignment03;

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
    protected String file = "C:\\Users\\aidan\\Desktop\\workspace\\CS547-Assignment03\\data\\classic\\nrp1.txt";

    @Constant(value = "costRatio")
    protected double costRatio = 0.3;

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

    @Override
    protected void config() {
        bindProblem(NRPCreator.class, NRPDecoder.class, NRPEvaluator.class);
    }
}
