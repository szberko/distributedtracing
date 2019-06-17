package com.szberko.ditributedtracing;

import org.junit.jupiter.api.Test;

import static com.szberko.ditributedtracing.Provider.graph;
import static com.szberko.ditributedtracing.Provider.inputData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class FileUtilityTest {

    @Test
    void givenInputString_whenParseGraph_thenReturnMappedGraph(){
        assertThat(graph, equalTo(FileUtility.parseGraph(inputData)));
    }
}