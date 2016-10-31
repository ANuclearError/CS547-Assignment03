package com.aidanogrady.cs547.assignment03;

import com.aidanogrady.cs547.assignment03.model.NextReleaseProblem;
import com.google.inject.Inject;
import org.opt4j.core.genotype.BooleanGenotype;
import org.opt4j.core.problem.Creator;

import java.util.Random;

/**
 * The creator for the genotypes for the next release problem.
 *
 * @author Aidan O'Grady
 * @since 0.2
 */
public class NRPCreator implements Creator<BooleanGenotype> {
    /**
     * The problem representation.
     */
    protected final NextReleaseProblem nrp;

    /**
     * The random generator.
     */
    private final Random random;

    /**
     * Constructs a new Creator
     *
     * @param nrp the problem representation
     */
    @Inject
    public NRPCreator(NextReleaseProblem nrp) {
        this.nrp = nrp;
        this.random = new Random();
    }

    @Override
    public BooleanGenotype create() {
        BooleanGenotype genotype = new BooleanGenotype();
        genotype.init(random, nrp.getRequirements().size());
        return genotype;
    }
}
