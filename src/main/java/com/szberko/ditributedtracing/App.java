package com.szberko.ditributedtracing;

import com.szberko.ditributedtracing.exception.GraphCannotCreatedException;
import com.szberko.ditributedtracing.measure.Measure;
import com.szberko.ditributedtracing.model.Node;
import com.szberko.ditributedtracing.utility.GraphProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class App {

    String name;
    final static Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {

        try {
            Measure measure = new Measure(GraphProvider.provideGraph("test.txt"));
            logger.info("1 : {}",
                    measure.calculatedAvgLatency(
                            new Node("A"),
                            new Node("B"),
                            new Node("C"))
            );
            logger.info("2 : {}",
                    measure.calculatedAvgLatency(
                            new Node("A"),
                            new Node("D"))
            );
            logger.info("3 : {}",
                    measure.calculatedAvgLatency(
                            new Node("A"),
                            new Node("D"),
                            new Node("C"))
            );
            logger.info("4 : {}",
                    measure.calculatedAvgLatency(
                            new Node("A"),
                            new Node("E"),
                            new Node("B"),
                            new Node("C"),
                            new Node("D"))
            );
            logger.info("5 : {}",
                    measure.calculatedAvgLatency(
                            new Node("A"),
                            new Node("E"),
                            new Node("D"))
            );
            logger.info("6 : {}",
                    measure.getNumberOfRoutesWithSpecificHopsCriteria(
                            "C",
                            "C",
                            hopsLimit -> hopsLimit <= 3
                    )
            );
            logger.info("7 : {}",
                    measure.getNumberOfRoutesWithSpecificHopsCriteria(
                            "A",
                            "C",
                            hopsLimit -> hopsLimit == 4
                    )
            );
            logger.info("8 : {}",
                    measure.getLengthOfTheShortestTrace(
                            "A",
                            "C"
                    )
            );
            logger.info("9 : {}",
                    measure.getLengthOfTheShortestTrace(
                            "B",
                            "B"
                    )
            );
            logger.info("10 : {}",
                    measure.getNumberOfRoutes(
                            "C",
                            "C",
                            latencyLimit -> latencyLimit < 30
                    )
            );
        } catch (IOException e) {
            logger.error("File cannot be read by the program or corrupted.");
        } catch (GraphCannotCreatedException e) {
            logger.error("There is no valid input in the provided file");
        }
    }
}
