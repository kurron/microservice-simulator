package org.kurron.simulator

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import spock.lang.Specification

class ServiceCommandSerializationTest extends Specification implements DataGenerator {

    def 'verify serialization works'() {

        given: 'valid object'
        def original = build( 6 )

        when: 'the original is encoded into JSON'
        def mapper = new ObjectMapper().enable( SerializationFeature.INDENT_OUTPUT )
        def bytes = mapper.writeValueAsBytes( original )
        println mapper.writeValueAsString( original )

        and: 'and decoded back again'
        def reconstituted = mapper.readValue( bytes, ServiceCommand ) as ServiceCommand

        then: 'the two versions match'
        original == reconstituted
    }

    private ServiceCommand build( int depth )  {
        def subject = randomString()
        def verb = randomString()
        def recurse = { build( depth - 1 ) }
        depth ? new ServiceCommand( subject: subject, verb: verb, recipientList: depth.collect( recurse ) ) : new ServiceCommand( subject: subject, verb: verb )
    }
}
