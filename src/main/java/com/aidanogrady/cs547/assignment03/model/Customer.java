package com.aidanogrady.cs547.assignment03.model;

import java.util.List;

/**
 * A customer has influence on what requirements should be prioritised. Each
 * customer has a certain weight to them as well as a list of requirements they
 * desire.
 *
 * @author Aidan O'Grady
 * @since 0.1
 */
public class Customer {
    /**
     * The profit of this customer, defining how important they are.
     */
    private double weight;

    /**
     * The list of requirements this customer wants.
     */
    private List<Integer> requirements;

    /**
     * Constructs a new customer.
     *
     * @param weight the weight of this customer.
     * @param requirements the requirements of this customer.
     */
    public Customer(double weight, List<Integer> requirements) {
        this.weight = weight;
        this.requirements = requirements;
    }

    /**
     * Returns this customer's weight.
     *
     * @return weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the weight of this customer to the given value.
     *
     * @param weight the new weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Returns this customer's requirements.
     *
     * @return requirements
     */
    public List<Integer> getRequirements() {
        return requirements;
    }


    public void setRequirements(List<Integer> requirements) {
        this.requirements = requirements;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "weight=" + weight +
                ", requirements=" + requirements.size() +
                '}';
    }
}
