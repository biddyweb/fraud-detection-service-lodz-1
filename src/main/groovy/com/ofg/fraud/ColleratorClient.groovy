package com.ofg.fraud

import com.netflix.hystrix.HystrixCommand
import com.netflix.hystrix.HystrixCommandGroupKey
import com.netflix.hystrix.HystrixCommandKey
import com.nurkiewicz.asyncretry.AsyncRetryExecutor
import com.ofg.infrastructure.web.resttemplate.fluent.ServiceRestClient
import groovy.transform.CompileStatic

@CompileStatic
class ColleratorClient {

    private final ServiceRestClient serviceRestClient

    private final AsyncRetryExecutor asyncRetryExecutor;

    ColleratorClient(ServiceRestClient serviceRestClient, AsyncRetryExecutor asyncRetryExecutor) {
        this.serviceRestClient = serviceRestClient;
        this.asyncRetryExecutor = asyncRetryExecutor;
    }

    void notifyDecisionMaker(long applicationId, ApplicationDecisionDTO applicationDecisionDTO) {
        serviceRestClient.forService(Collaborators.COLLERATOR_DEPENDENCY_NAME)
                .retryUsing(asyncRetryExecutor.withMaxRetries(5))
                .put()
                .withCircuitBreaker(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("FraudServiceLdz"))
                    .andCommandKey(HystrixCommandKey.Factory.asKey("ApplicationRequestEvent")),
                {
                    ''

                })
                .onUrlFromTemplate("/loanApplication/{applicationId}").withVariables(applicationId)
                .body(applicationDecisionDTO)
                .anObject()
                .ofType(ApplicationDecisionDTO)
    }

}