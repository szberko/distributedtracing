package com.szberko.ditributedtracing;

import com.szberko.ditributedtracing.model.Edge;
import com.szberko.ditributedtracing.model.Graph;
import com.szberko.ditributedtracing.model.Node;
import com.szberko.ditributedtracing.utility.FileUtility;

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

    static {
        A.addOutGoingEdge(AB);
        A.addOutGoingEdge(AD);
        A.addOutGoingEdge(AE);

        B.addOutGoingEdge(BC);
        B.addInComingEdge(AB);
        B.addInComingEdge(EB);

        C.addOutGoingEdge(CD);
        C.addOutGoingEdge(CE);
        C.addInComingEdge(BC);
        C.addInComingEdge(DC);

        D.addOutGoingEdge(DC);
        D.addOutGoingEdge(DE);
        D.addInComingEdge(CD);
        D.addInComingEdge(AD);

        E.addOutGoingEdge(EB);
        E.addInComingEdge(DE);
        E.addInComingEdge(CE);
        E.addInComingEdge(AE);


    }

    public static final Graph graph = FileUtility.parseGraph(inputData);

}
