package com.szberko.ditributedtracing;

import java.util.*;

public class Graph {

    private final Map<String, Node> nodes;
    private final Map<String, Edge> edges;

    public Graph(Map<String, Node> nodes, Map<String, Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    //TODO Improve me
    //TODO Add exception handling when there no such trace
    //TODO Add exception handling when there is no sufficient input parameters
    public int calculatedAvgLatency(Node... trace){
        int overallLatency = 0;
        for (int i = 1; i < trace.length; i++){
            overallLatency += edges.get(trace[i-1].getName() + trace[i].getName()).getLatency();
        }
        return overallLatency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Graph graph = (Graph) o;
        return Objects.equals(nodes, graph.nodes) &&
                Objects.equals(edges, graph.edges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodes, edges);
    }

    @Override
    public String toString() {
        return "Graph{" +
                "nodes=" + nodes +
                ", edges=" + edges +
                '}';
    }
}
