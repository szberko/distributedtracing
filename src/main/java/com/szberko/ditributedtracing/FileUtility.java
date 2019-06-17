package com.szberko.ditributedtracing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtility {

    public static Graph parseGraph(String input){
        final List<String> connections = separateEdges(input);

        final Map<String, Node> nodes = parseNodes(connections);
        final Map<String, Edge> edges = parseEdges(connections, nodes);

        final Map<String, Node> fullNodes = combine(nodes, edges);
        return new Graph(fullNodes);
    }

    private static Map<String, Node> combine(Map<String, Node> nodes, Map<String, Edge> edges){
        edges.forEach((edgeName, edge)->
                nodes.get(edge.getStartingNode().getName()).addOutGoingEdge(edge));
        edges.forEach((edgeName, edge)->
                nodes.get(edge.getEndingNode().getName()).addInComingEdge(edge));
        return nodes;
    }

    private static Map<String, Edge> parseEdges(List<String> connections, Map<String, Node> nodes){
        return connections.stream()
                .map(connection -> new Edge(
                        "" + connection.charAt(0) + connection.charAt(1),
                        Integer.parseInt("" + connection.charAt(2)),
                        nodes.get("" + connection.charAt(0)),
                        nodes.get("" + connection.charAt(1))))
                .collect(Collectors.toMap(Edge::getName, edge -> edge));
    }

    private static Map<String, Node> parseNodes(List<String> connections){
        return connections.stream().map(connection -> connection.replaceAll("[^A-Za-z]+", ""))
                .map(connection -> connection.split(""))
                .flatMap(Arrays::stream)
                .map(Node::new)
                .collect(Collectors.toMap(Node::getName, node -> node, (node1, node2) -> node1));
    }

    protected static List<String> separateEdges(String input){
        return Stream.of(input).map(string -> string.split(","))
                .flatMap(Arrays::stream)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    protected static Stream<String> readFromFile(final String filePath) throws IOException {
        return Files.lines(Paths.get(filePath));
    }
}
