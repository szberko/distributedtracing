package com.szberko.ditributedtracing.model;

import com.szberko.ditributedtracing.measure.MeasureLatencyOnShortestTrace;
import com.szberko.ditributedtracing.measure.MeasureNoOfTracesWithHopsLimit;
import com.szberko.ditributedtracing.measure.MeasureNumberOfTraces;
import com.szberko.ditributedtracing.exception.NoSuchTraceException;

import java.util.*;
import java.util.function.Predicate;

public class Graph {

    private final Map<String, Node> nodes;

    public Graph(Map<String, Node> nodes) {
        this.nodes = nodes;
    }

    public Map<String, Node> getNodes() {
        return nodes;
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
