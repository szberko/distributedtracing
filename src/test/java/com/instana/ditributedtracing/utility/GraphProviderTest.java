package com.instana.ditributedtracing.utility;

import com.instana.ditributedtracing.exception.GraphCannotCreatedException;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.instana.ditributedtracing.providers.Provider.graph;
import static com.instana.ditributedtracing.providers.Provider.inputData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GraphProviderTest {

    private static String inputFile = "test.txt";
    private static String emptyInputFile = "testEmpty.txt";
    private static String inputFileWithNonValidContent = "testNonValidContent.txt";
    private static String nonExistingInputFileName = "something";

    @Test
    void givenEmptyInputFile_whenProvidingGraph_thenThrowGraphCannotCreatedException() {
        assertThrows(GraphCannotCreatedException.class, () -> GraphProvider.provideGraph(emptyInputFile));
    }

    @Test
    void givenInputFileWithNonValidDefinition_whenProvidingGraph_thenThrowGraphCannotCreatedException() {
        assertThrows(GraphCannotCreatedException.class, () -> GraphProvider.provideGraph(inputFileWithNonValidContent));
    }

    @Test
    void givenInputFile_whenCallingProvideGraph_thenReturnAProperGraph() throws GraphCannotCreatedException {
        assertThat(GraphProvider.provideGraph(inputFile), equalTo(graph));
    }

    @Test
    void givenNonExistingInputFile_whenReadFromFile_thenThrowNPE(){
        assertThrows(NullPointerException.class, () -> GraphProvider.readFromFile(nonExistingInputFileName));
    }

    @Test
    void givenInputFile_whenReadFromFile_thenReturnStreamOfLines(){
        assertThat(GraphProvider.readFromFile(inputFile).collect(Collectors.toList()),
                equalTo(Stream.of(inputData).collect(Collectors.toList())));
    }
}