package org.kurron.simulator

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical

@Canonical
class ServiceCommand {

    @JsonProperty( 'subject' )
    String subject

    @JsonProperty( 'verb' )
    String verb

    @JsonProperty( 'recipient-list' )
    List<ServiceCommand> recipientList = []
}
