package org.kurron.simulator

import com.fasterxml.jackson.databind.ObjectMapper
import spock.lang.Specification

class ServiceCommandSerializationTest extends Specification {

    def 'verify serialization works'() {

        given: 'valid object'
        def original = new ServiceCommand( subject: 'subject', verb: 'verb', recipientList: [] )

        when: 'the original is encoded into JSON'
        def mapper = new ObjectMapper()
        def bytes = mapper.writeValueAsBytes( original )

        and: 'and decoded back again'
        def reconstituted = mapper.readValue( bytes, ServiceCommand ) as ServiceCommand

        then: 'the two versions match'
        original == reconstituted

    }
}
