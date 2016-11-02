package com.aidanogrady.cs547.assignment03.model;

import com.aidanogrady.cs547.assignment03.util.FileParser;
import com.google.inject.Inject;
import org.opt4j.core.start.Constant;

import java.io.File;
import java.util.List;

/**
 * The representation of the problem, containing all the information such as
 * the requirements and the customers found within the given file.
 *
 * @author Aidan O'Grady
 * @since 0.2
 */
public class NextReleaseProblem {
    /**
     * The budget of the next release.
     */
    private int budget;

    /**
     * The cost ratio of the budget
     */
    private double costRatio;

    /**
     * The list of customers.
     */
    private List<Customer> customers;

    /**
     * The list of requirements that are available.
     */
    private List<Requirement> requirements;

    @Inject
    public NextReleaseProblem(@Constant(value = "file") String file,
                              @Constant(value = "costRatio") double costRatio)
    {
        this.costRatio = costRatio;
        FileParser parser = new FileParser(new File(file));
        if (parser.readFile()) {
            this.requirements = parser.getRequirements();
            this.customers = parser.getCustomers();
        }

        this.budget = 0;
        for (Requirement req : requirements) {
            budget += req.getCost();
        }
        budget *= costRatio;
    }

    /**
     * Returns the budget of this next release.
     *
     * @return budget
     */
    public int getBudget() {
        return budget;
    }

    /**
     * Sets the budget of the next release.
     *
     * @param budget the new budget
     */
    public void setBudget(int budget) {
        this.budget = budget;
    }

    /**
     * Returns the cost ratio that creates the budget.
     *
     * @return costRatio
     */
    public double getCostRatio() {
        return costRatio;
    }

    /**
     * Sets the new cost ratio of this release.
     *
     * @param costRatio new cost ratio
     */
    public void setCostRatio(double costRatio) {
        this.costRatio = costRatio;
    }

    /**
     * Returns the list of customers.
     *
     * @return customers
     */
    public List<Customer> getCustomers() {
        return customers;
    }

    /**
     * Sets the customers to the given list.
     * @param customers the new list of customers
     */
    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    /**
     * Returns the requirement found at the given index.
     *
     * @param index the index to look for
     * @return requirement at index if valid index, otherwise null
     */
    public Requirement getRequirement(int index) {
        if (index < 0 || index >= requirements.size()) {
            return null;
        }
        return requirements.get(index);
    }

    /**
     * Returns the list of requirements.
     *
     * @return requirements
     */
    public List<Requirement> getRequirements() {
        return requirements;
    }

    /**
     * Sets the requirements to the given list.
     *
     * @param requirements the new list of requirements
     */
    public void setRequirements(List<Requirement> requirements) {
        this.requirements = requirements;
    }

    /**
     * Returns the score of the given requirement, by examining each customers'
     * own requirements.
     *
     * @param requirementIndex the requirement being scored.
     * @return score
     */
    public double score(int requirementIndex) {
        if (requirementIndex < 0 || requirementIndex >= requirements.size())
            return 0;

        double score = 0;

        for (Customer customer : customers) {
            if (customer.hasRequirement(requirementIndex)) {
                int size = customer.getRequirements().size();

                double div = 0;
                for (int i = 1; i <= size; i++) {
                    div += i;
                }
                double num = size - customer.getRequirements().indexOf(requirementIndex);

                score += customer.getWeight() * (num / div);
            }
        }
        return score;
    }
}
