package org.kurron.simulator

import org.jgrapht.Graph
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.DirectedAcyclicGraph
import org.jgrapht.graph.builder.GraphBuilder
import spock.lang.Specification

class GraphLearningTest extends Specification implements DataGenerator {

    def 'learning test'() {

        given: 'valid graph'
        Graph<String, DefaultEdge> graph = new DirectedAcyclicGraph<>( DefaultEdge )
        def builder = new GraphBuilder( graph )
        def vertexes = (1..10).collect { it as String }
        builder.addVertex( vertexes ).addEdge( vertexes[0], vertexes[1] ).addEdge( vertexes[1], vertexes[2] )

        when: 'the graph is dumped'
        def edges = graph.edgeSet()
        def vertexSet = graph.vertexSet()

        then: 'it looks correct'
        println "Entire graph: ${graph.toString()}"
        println "Edges: ${edges}"
        println "Vertexes: ${vertexSet}"
    }
}
