package com.ofg.fraud

import com.ofg.infrastructure.web.resttemplate.fluent.ServiceRestClient
import groovy.transform.CompileStatic

@CompileStatic
class ColleratorClient {

    private final ServiceRestClient serviceRestClient

    ColleratorClient(ServiceRestClient serviceRestClient) {
        this.serviceRestClient = serviceRestClient
    }

    void notifyDecisionMaker(long applicationId, ApplicationDecisionDTO applicationDecisionDTO) {
        serviceRestClient.forService(Collaborators.COLLERATOR_DEPENDENCY_NAME)
                .post()
                .onUrlFromTemplate("/{applicationId}").withVariables(applicationId)
                .body(applicationDecisionDTO)
                .anObject()
                .ofType(ApplicationDecisionDTO)
    }

}