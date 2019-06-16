package com.szberko.ditributedtracing;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Node {

    private final String name;
    private Set<Edge> outgoingEdges;
    private Set<Edge> incomingEdges;

        public Node(String name) {
        this.name = name;
        this.outgoingEdges = new HashSet<>();
        this.incomingEdges = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void addOutGoingEdge(Edge edge) {
            this.outgoingEdges.add(edge);
    }

    public void addInComingEdge(Edge edge) {
            this.incomingEdges.add(edge);
    }

    public Set<Edge> getOutgoingEdges() {
        return outgoingEdges;
    }

    public Set<Edge> getIncomingEdges() {
        return incomingEdges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(name, node.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                '}';
    }
}
