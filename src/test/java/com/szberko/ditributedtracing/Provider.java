package com.szberko.ditributedtracing;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class Provider {

    public static final String inputData = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";

    public static final Node A = new Node("A");
    public static final Node B = new Node("B");
    public static final Node C = new Node("C");
    public static final Node D = new Node("D");
    public static final Node E = new Node("E");



    public static final Edge AB = new Edge("AB", 5, A, B);
    public static final Edge BC = new Edge("BC", 4, B, C);
    public static final Edge CD = new Edge("CD", 8, C, D);
    public static final Edge DC = new Edge("DC", 8, D, C);
    public static final Edge DE = new Edge("DE", 6, D, E);
    public static final Edge AD = new Edge("AD", 5, A, D);
    public static final Edge CE = new Edge("CE", 2, C, E);
    public static final Edge EB = new Edge("EB", 3, E, B);
    public static final Edge AE = new Edge("AE", 7, A, E);

    public static final Node newA = new Node("A", Set.of(AB, AD, AE), Collections.emptySet());
    public static final Node newB = new Node("B", Set.of(BC), Set.of(AB, EB));
    public static final Node newC = new Node("C", Set.of(CD, CE), Set.of(BC, DC));
    public static final Node newD = new Node("D", Set.of(DC, DE), Set.of(CD, AD));
    public static final Node newE = new Node("E", Set.of(EB), Set.of(DE, CE, AE));

    public static final Graph graph = new Graph(
            Map.of("A", A,
                    "B", B,
                    "C", C,
                    "D", D,
                    "E", E),
            Map.of("AB", AB,
                    "BC", BC,
                    "CD", CD,
                    "DC", DC,
                    "DE", DE,
                    "AD", AD,
                    "CE", CE,
                    "EB", EB,
                    "AE", AE)
    );

    public static final Graph newGraph = new Graph(
            Map.of("A", newA,
                    "B", newB,
                    "C", newC,
                    "D", newD,
                    "E", newE)
    );
}
