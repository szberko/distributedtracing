package com.szberko.ditributedtracing.utility;

import com.szberko.ditributedtracing.exception.GraphCannotCreatedException;
import com.szberko.ditributedtracing.model.Edge;
import com.szberko.ditributedtracing.model.Graph;
import com.szberko.ditributedtracing.model.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class GraphProvider {

    public static Graph provideGraph(final String fileName) throws IOException, GraphCannotCreatedException {
        return readFromFile(fileName).map(GraphProvider::separateEdges)
                .map(GraphProvider::parse)
                .map(Graph::new)
                .findAny()
                .orElseThrow(() -> new GraphCannotCreatedException("Cannot create graph from file"));
    }

    public static Graph parseGraph(String input){
        final Stream<String> connections = separateEdges(input);

        final Map<String, Node> nodes = parse(connections);
        return new Graph(nodes);
    }

    private static Map<String, Node> parse(Stream<String> connections){
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

    private static Stream<String> separateEdges(String input){
        return Stream.of(input).map(string -> string.split(","))
                .flatMap(Arrays::stream)
                .map(String::trim);
    }

    private static Stream<String> readFromFile(final String fileName) {
        InputStream stream = GraphProvider.class.getClassLoader().getResourceAsStream(fileName);
        return new BufferedReader(new InputStreamReader(Objects.requireNonNull(stream))).lines();
    }
}
