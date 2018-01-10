package org.kurron.simulator

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph
import spock.lang.Specification

import static org.apache.tinkerpop.gremlin.process.traversal.P.eq
import static org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__.*

class TinkerPopLearningTest extends Specification implements DataGenerator {

    // see http://tinkerpop.apache.org/docs/current/reference/#terminal-steps on how to get path information out
    def 'learning test'() {

        expect:
        def graph = TinkerGraph.open()
        def vertices = (1..10).collect { graph.addVertex( it as String ) }
        while( false == vertices.empty ) {
            def from = vertices.pop()
            def to = vertices.pop()
            from.addEdge( 'linked', to )
        }
/*
        Vertex marko = graph.addVertex("name","marko","age",29)
        Vertex lop = graph.addVertex("name","lop","lang","java")
        marko.addEdge("created",lop,"weight",0.6d)
*/
        //def result = graph.traversal().V().has("name","marko").out("created").values("name").toList()

        // http://tinkerpop.apache.org/docs/current/recipes/#connected-components
        def result = graph.traversal().V().emit( cyclicPath().or().not( both() ) ).repeat( both() ).until( cyclicPath() ).
                             path().aggregate( 'p' ).
                             unfold().dedup().
                             map( __.as( 'v' ).select( 'p' ).unfold().
                             filter( unfold().where( eq( 'v' ) ) ).
                             unfold().dedup().fold() ).
                             dedup().toList()
        println( result )
    }
}
