package com.aidanogrady.cs547.assignment03;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
     * The level of this requirement.
     */
    private int level;

    /**
     * The cost of this requirement.
     */
    private int cost;

    private Set<Requirement> dependencies;

    /**
     * Consturcts a new requirement.
     *
     * @param level the level of the requirement.
     * @param cost the cost of the requirement.
     */
    public Requirement(int level, int cost) {
        this.level = level;
        this.cost = cost;
        dependencies = new HashSet<>();
    }

    /**
     * Adds the given dependency to this requirements list of dependencies.
     *
     * @param dependency the requirement that this depends on.
     * @return addition is successful
     */
    public boolean addDependency(Requirement dependency) {
        return dependencies.add(dependency);
    }

    @Override
    public String toString() {
        return "Req{" +
                "level=" + level +
                ", cost=" + cost +
                ", dependencies=" + dependencies.size() +
                '}';
    }
}
