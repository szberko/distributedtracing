package com.szberko.ditributedtracing;

import com.szberko.ditributedtracing.exception.GraphCannotCreatedException;
import com.szberko.ditributedtracing.measure.Measure;
import com.szberko.ditributedtracing.model.Node;
import com.szberko.ditributedtracing.utility.GraphProvider;

import java.io.File;
import java.io.IOException;

public class App {

    public static void main(String[] args) {

        try {
            Measure measure = new Measure(GraphProvider.provideGraph("test.txt"));
            System.out.println(
                    measure.calculatedAvgLatency(
                            new Node("A"),
                            new Node("B"),
                            new Node("C"))
            );
            System.out.println(
                    measure.calculatedAvgLatency(
                            new Node("A"),
                            new Node("D"))
            );
            System.out.println(
                    measure.calculatedAvgLatency(
                            new Node("A"),
                            new Node("D"),
                            new Node("C"))
            );
            System.out.println(
                    measure.calculatedAvgLatency(
                            new Node("A"),
                            new Node("E"),
                            new Node("B"),
                            new Node("C"),
                            new Node("D"))
            );
            System.out.println(
                    measure.calculatedAvgLatency(
                            new Node("A"),
                            new Node("E"),
                            new Node("D"))
            );
            System.out.println(
                    measure.getNumberOfRoutesWithSpecificHopsCriteria(
                            "C",
                            "C",
                            hopsLimit -> hopsLimit <= 3,
                            10
                    )
            );
            System.out.println(
                    measure.getNumberOfRoutesWithSpecificHopsCriteria(
                            "A",
                            "C",
                            hopsLimit -> hopsLimit == 4,
                            10
                    )
            );
            System.out.println(
                    measure.getLengthOfTheShortestTrace(
                            "A",
                            "C"
                    )
            );
            System.out.println(
                    measure.getLengthOfTheShortestTrace(
                            "B",
                            "B"
                    )
            );
            System.out.println(
                    measure.getNumberOfRoutes(
                            "C",
                            "C",
                            latencyLimit -> latencyLimit < 30
                    )
            );
        } catch (IOException e) {
            System.out.println("File cannot be read by the program or corrupted.");
        } catch (GraphCannotCreatedException e) {
            System.out.println("There is no valid input in the provided file");
        }
    }
}
