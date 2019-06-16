package com.szberko.ditributedtracing;

import java.util.*;

public class Graph {

    private final Map<String, Node> nodes;
    private final Map<String, Edge> edges;

    public Graph(Map<String, Node> nodes, Map<String, Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public int calculatedAvgLatency(Node... trace){
        int overalLatency = 0;
        for (int i = 1; i < trace.length; i++){
            overalLatency += edges.get(trace[i-1].getName() + trace[i].getName()).getLatency();
        }
        return overalLatency;
    }

    public Map<String, Node> getNodes() {
        return nodes;
    }

    public Map<String, Edge> getEdges() {
        return edges;
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
