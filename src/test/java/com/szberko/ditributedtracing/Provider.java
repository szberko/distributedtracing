package com.szberko.ditributedtracing;

import java.util.List;
import java.util.Map;

public class Provider {

    public static final String inputData = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";

    public static final Graph graph = new Graph(
            Map.of("A", new Node("A"),
                    "B", new Node("B"),
                    "C", new Node("C"),
                    "D", new Node("D"),
                    "E", new Node("E")),
            Map.of("AB", new Edge("AB", 5, new Node("A"), new Node("B")),
                    "BC", new Edge("BC", 4, new Node("B"), new Node("C")),
                    "CD", new Edge("CD", 8, new Node("C"), new Node("D")),
                    "DC", new Edge("DC", 8, new Node("D"), new Node("C")),
                    "DE", new Edge("DE", 6, new Node("D"), new Node("E")),
                    "AD", new Edge("AD", 5, new Node("A"), new Node("D")),
                    "CE", new Edge("CE", 2, new Node("C"), new Node("E")),
                    "EB", new Edge("EB", 3, new Node("E"), new Node("B")),
                    "AE", new Edge("AE", 7, new Node("A"), new Node("E")))
    );

    public static Graph aGraph(){
        return new Graph(
                Map.of("A", new Node("A"),
                        "B", new Node("B"),
                        "C", new Node("C"),
                        "D", new Node("D"),
                        "E", new Node("E")),
                Map.of("AB", new Edge("AB", 5, new Node("A"), new Node("B")),
                        "BC", new Edge("BC", 4, new Node("B"), new Node("C")),
                        "CD", new Edge("CD", 8, new Node("C"), new Node("D")),
                        "DC", new Edge("DC", 8, new Node("D"), new Node("C")),
                        "DE", new Edge("DE", 6, new Node("D"), new Node("E")),
                        "AD", new Edge("AD", 5, new Node("A"), new Node("D")),
                        "CE", new Edge("CE", 2, new Node("C"), new Node("E")),
                        "EB", new Edge("EB", 3, new Node("E"), new Node("B")),
                        "AE", new Edge("AE", 7, new Node("A"), new Node("E")))
        );
    }
}
