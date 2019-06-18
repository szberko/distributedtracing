package com.szberko.ditributedtracing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtility {

    public static Graph parseGraph(String input){
        final List<String> connections = separateEdges(input);

        final Map<String, Node> nodes = parse(connections);
        return new Graph(nodes);
    }

    private static Map<String, Node> parse(List<String> connections){
        Map<String, Node> nodes = new HashMap<>();
        connections.forEach(connection -> {
            Node start = nodes.computeIfAbsent("" + connection.charAt(0), Node::new);
            Node end = nodes.computeIfAbsent("" + connection.charAt(1), Node::new);
            Edge edge = new Edge(
                    "" + connection.charAt(0) + connection.charAt(1),
                    Integer.parseInt("" + connection.charAt(2)),
                    start,
                    end);
            start.addOutGoingEdge(edge);
            end.addInComingEdge(edge);
        });
        return nodes;
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
