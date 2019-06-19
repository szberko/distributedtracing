package com.instana.ditributedtracing.model;

import java.util.Map;
import java.util.Objects;

public class Graph {

    private final Map<String, Node> nodes;
    private final Integer hopsLimit;

    public Graph(Map<String, Node> nodes) {
        this.nodes = nodes;
        this.hopsLimit = nodes.size() * 2;
    }

    public Map<String, Node> getNodes() {
        return nodes;
    }

    public Integer getHopsLimit() {
        return hopsLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Graph graph = (Graph) o;
        return Objects.equals(nodes, graph.nodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodes);
    }

    @Override
    public String toString() {
        return "Graph{" +
                "nodes=" + nodes +
                '}';
    }
}
