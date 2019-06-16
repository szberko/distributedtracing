package com.szberko.ditributedtracing;

import java.util.Objects;

public class Edge {

    private final String name;
    private final Integer latency;
    private final Node startingNode;
    private final Node endingNode;

    public Edge(String name, Integer latency, Node startingNode, Node endingNode) {
        this.name = name;
        this.latency = latency;
        this.startingNode = startingNode;
        this.endingNode = endingNode;
    }

    public String getName() {
        return name;
    }

    public Integer getLatency() {
        return latency;
    }

    public Node getStartingNode() {
        return startingNode;
    }

    public Node getEndingNode() {
        return endingNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Objects.equals(name, edge.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "name='" + name + '\'' +
                ", latency=" + latency +
                ", startingNode=" + startingNode +
                ", endingNode=" + endingNode +
                '}';
    }
}
