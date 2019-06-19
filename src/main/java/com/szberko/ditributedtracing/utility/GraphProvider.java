package com.szberko.ditributedtracing.utility;

import com.google.common.annotations.VisibleForTesting;
import com.szberko.ditributedtracing.exception.GraphCannotCreatedException;
import com.szberko.ditributedtracing.model.Edge;
import com.szberko.ditributedtracing.model.Graph;
import com.szberko.ditributedtracing.model.Node;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class GraphProvider {

    public static Graph provideGraph(final String fileName) throws GraphCannotCreatedException {
        return readFromFile(fileName).map(GraphProvider::separateEdges)
                .map(GraphProvider::parse)
                .map(Graph::new)
                .findAny()
                .orElseThrow(() -> new GraphCannotCreatedException("Cannot create graph from file"));
    }

    private static Map<String, Node> parse(final Stream<String> connections){
        Map<String, Node> nodes = new HashMap<>();
        connections.forEach(connection -> {
            final Node start = nodes.computeIfAbsent("" + connection.charAt(0), Node::new);
            final Node end = nodes.computeIfAbsent("" + connection.charAt(1), Node::new);
            final Edge edge = new Edge(
                    "" + connection.charAt(0) + connection.charAt(1),
                    Integer.parseInt("" + connection.charAt(2)),
                    start,
                    end);
            start.addOutGoingEdge(edge);
            end.addInComingEdge(edge);
        });
        return nodes;
    }

    @VisibleForTesting
    static Stream<String> separateEdges(final String input){
        return Stream.of(input).map(string -> string.split(","))
                .flatMap(Arrays::stream)
                .map(String::trim);
    }

    @VisibleForTesting
    static Stream<String> readFromFile(final String fileName) {
        final InputStream stream = GraphProvider.class.getClassLoader().getResourceAsStream(fileName);
        return new BufferedReader(new InputStreamReader(Objects.requireNonNull(stream))).lines();
    }
}
