package com.aidanogrady.cs547.assignment03;

/**
 * Each requirement that has been identified. Different customers may require
 * different requirements, which all have different costs and level of
 * importance.
 *
 * @author Aidan O'Grady
 * @since 0.1
 */
public class Requirement {
    /**
     * The unique id of this requirement.
     */
    private int id;

    /**
     * The level of this requirement.
     */
    private int level;

    /**
     * The cost of this requirement.
     */
    private int cost;

    /**
     * Consturcts a new requirement.
     *
     * @param id the id of the requirement.
     * @param level the level of the requirement.
     * @param cost the cost of the requirement.
     */
    public Requirement(int id, int level, int cost) {
        this.id = id;
        this.level = level;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Req{" +
                "id=" + id +
                ", level=" + level +
                ", cost=" + cost +
                '}';
    }
}
