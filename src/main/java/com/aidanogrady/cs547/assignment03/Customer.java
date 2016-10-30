package com.aidanogrady.cs547.assignment03;

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
    private int profit;

    /**
     * The list of requirements this customer wants.
     */
    private List<Integer> requirements;

    /**
     * Constructs a new customer.
     *
     * @param profit the profit of this customer.
     * @param requirements the requirements of this customer.
     */
    public Customer(int profit, List<Integer> requirements) {
        this.profit = profit;
        this.requirements = requirements;
    }

    /**
     * Returns this customer's profit.
     *
     * @return profit
     */
    public int getProfit() {
        return profit;
    }

    /**
     * Returns this customer's requirements.
     *
     * @return requirements
     */
    public List<Integer> getRequirements() {
        return requirements;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "profit=" + profit +
                ", requirements=" + requirements.size() +
                '}';
    }
}
