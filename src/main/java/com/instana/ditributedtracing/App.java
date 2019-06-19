package com.instana.ditributedtracing;

import com.instana.ditributedtracing.exception.GraphCannotCreatedException;
import com.instana.ditributedtracing.measure.Measure;
import com.instana.ditributedtracing.utility.GraphProvider;

public class App {

    private final static String inputFileName = "test.txt";

    public static void main(String[] args) {

        try {
            Measure measure = new Measure(GraphProvider.provideGraph(inputFileName));
            System.out.println(String.format("1 : %s", measure.calculatedAvgLatency("A", "B", "C")));
            System.out.println(String.format("2 : %s", measure.calculatedAvgLatency("A", "D")));
            System.out.println(String.format("3 : %s", measure.calculatedAvgLatency("A", "D", "C")));
            System.out.println(String.format("4 : %s", measure.calculatedAvgLatency("A", "E", "B", "C", "D")));
            System.out.println(String.format("5 : %s", measure.calculatedAvgLatency("A", "E", "D")));
            System.out.println(String.format("6 : %s",
                    measure.getNumberOfRoutesWithSpecificHopsCriteria("C", "C", hopsLimit -> hopsLimit <= 3)));
            System.out.println(String.format("7 : %s",
                    measure.getNumberOfRoutesWithSpecificHopsCriteria("A", "C", hopsLimit -> hopsLimit == 4)));
            System.out.println(String.format("8 : %s",
                    measure.getLengthOfTheShortestTrace("A", "C")));
            System.out.println(String.format("9 : %s",
                    measure.getLengthOfTheShortestTrace("B", "B")));
            System.out.println(String.format("10 : %s",
                    measure.getNumberOfRoutes("C", "C", latencyLimit -> latencyLimit < 30)));
        } catch (GraphCannotCreatedException e) {
            System.out.println("There is no valid input in the provided file");
        }
    }
}
