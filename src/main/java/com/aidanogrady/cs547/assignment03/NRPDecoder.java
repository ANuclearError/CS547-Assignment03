package com.aidanogrady.cs547.assignment03;

import org.opt4j.core.genotype.BooleanGenotype;
import org.opt4j.core.problem.Decoder;

/**
 * Decoder for converting the NRP genotype into a phenotype.
 *
 * @author Aidan O'Grady
 * @since 0.2
 */
public class NRPDecoder implements Decoder<BooleanGenotype, String> {
    @Override
    public String decode(BooleanGenotype genotype) {
        String phenotype = "";
        for (Boolean aGenotype : genotype) {
            phenotype += aGenotype ? "1" : "0";
        }
        return phenotype;
    }
}
