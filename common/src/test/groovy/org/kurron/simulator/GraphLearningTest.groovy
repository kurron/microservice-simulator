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
        def serviceTier = vertexes.subList( 0, 8 )
        def dataTier = vertexes.subList( 8, vertexes.size() )
        def combinations = [serviceTier,dataTier].combinations()
        builder.addVertex( serviceTier ).addVertex( dataTier )
        combinations.each { from, to ->
            builder.addEdge( from, to )
        }

        when: 'the graph is dumped'
        def edges = graph.edgeSet()
        def vertexSet = graph.vertexSet()

        then: 'it looks correct'
        println "Entire graph: ${graph.toString()}"
        println "Edges: ${edges}"
        println "Vertexes: ${vertexSet}"
    }
}
