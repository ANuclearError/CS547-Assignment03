package com.aidanogrady.cs547.assignment03;

import com.aidanogrady.cs547.assignment03.model.NextReleaseProblem;
import com.aidanogrady.cs547.assignment03.model.Requirement;
import com.google.inject.Inject;
import com.sun.org.apache.regexp.internal.RE;
import org.opt4j.core.genotype.PermutationGenotype;
import org.opt4j.core.problem.Creator;

import java.util.Random;

/**
 * The creator for the genotypes for the next release problem.
 *
 * @author Aidan O'Grady
 * @since 0.2
 */
public class NRPCreator implements Creator<PermutationGenotype<Requirement>> {
    /**
     * The problem representation.
     */
    private final NextReleaseProblem nrp;

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
    public PermutationGenotype<Requirement> create() {
        PermutationGenotype<Requirement> genotype = new PermutationGenotype<>();

        int size = nrp.getRequirements().size();
        int budget = 0;
        while (nrp.getBudget() > budget) { // Fill genotype till budget met.
            genotype.add(nrp.getRequirement(random.nextInt(size)));
        }
        return genotype;
    }
}
