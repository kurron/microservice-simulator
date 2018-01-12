package org.kurron.simulator

import static org.apache.tinkerpop.gremlin.process.traversal.P.eq
import static org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__.V
import static org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__.as as TinkerPopAs
import static org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__.both
import static org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__.cyclicPath
import static org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__.unfold

import org.apache.tinkerpop.gremlin.structure.Vertex
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerVertex
import spock.lang.Specification

class TinkerPopLearningTest extends Specification implements DataGenerator {

    // see http://tinkerpop.apache.org/docs/current/reference/#terminal-steps on how to get path information out
    def 'learning test'() {

        expect:
        def graph = TinkerGraph.open()
        def vertices = (1..6).collect {
            def label = Integer.toHexString(it).toUpperCase()
            graph.addVertex( 'name', label )
        }
        def alpha = vertices.pop()
        def bravo = vertices.pop()
        def charlie = vertices.pop()
        def delta = vertices.pop()
        alpha.addEdge( 'linked', bravo )
        bravo.addEdge( 'linked', charlie )
        bravo.addEdge( 'linked', delta )

        def echo = vertices.pop()
        def foxtrot = vertices.pop()
        echo.addEdge( 'linked', foxtrot )

/*
        while(!vertices.empty) {
            def to = vertices.pop()
            alpha.addEdge( 'linked', to )
        }
*/
/*
        Vertex marko = graph.addVertex("name","marko","age",29)
        Vertex lop = graph.addVertex("name","lop","lang","java")
        marko.addEdge("created",lop,"weight",0.6d)
*/

        // http://tinkerpop.apache.org/docs/current/recipes/#connected-components
        def result = graph.traversal().V()
                          .emit( cyclicPath().or().not( both() ) ).repeat( both() ).until( cyclicPath() )
                          .path().aggregate( 'p' )
                          .unfold().dedup()
                          .map( TinkerPopAs( 'v' ).select( 'p' ).unfold()
                          .filter( unfold().where( eq( 'v' ) ) )
                          .unfold().dedup().fold() )
                          .dedup()
                          .toList()
        result.each { List<Vertex> path ->
            println path.collect { it.id() }.join( '->' )
        }
    }
}
