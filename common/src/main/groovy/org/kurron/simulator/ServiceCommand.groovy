package org.kurron.simulator

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical

@Canonical
class ServiceCommand {

    @JsonProperty( 'subject' )
    String subject

    @JsonProperty( 'verb' )
    String verb

    @JsonProperty( 'fail-health-check' )
    boolean failHealthCheck

    @JsonProperty( 'latency' )
    Latency latency = new Latency()

    @JsonProperty( 'recipient-list' )
    List<ServiceCommand> recipientList = []

    @Canonical
    static class Latency {
        @JsonProperty( 'minimum' )
        int minimum

        @JsonProperty( 'maximum' )
        int maximum
    }
}
