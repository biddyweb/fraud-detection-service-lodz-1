package com.ofg.fraud

import com.netflix.hystrix.HystrixCommand
import com.netflix.hystrix.HystrixCommandGroupKey
import com.netflix.hystrix.HystrixCommandKey
import com.nurkiewicz.asyncretry.AsyncRetryExecutor
import com.ofg.infrastructure.web.resttemplate.fluent.ServiceRestClient
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

@Slf4j
@CompileStatic
class ColleratorClient {

    private final ServiceRestClient serviceRestClient

    private final AsyncRetryExecutor asyncRetryExecutor;

    ColleratorClient(ServiceRestClient serviceRestClient, AsyncRetryExecutor asyncRetryExecutor) {
        this.serviceRestClient = serviceRestClient;
        this.asyncRetryExecutor = asyncRetryExecutor;
    }

    String notifyDecisionMaker(long applicationId, ApplicationDecisionDTO applicationDecisionDTO) {
        serviceRestClient.forService(Collaborators.COLLERATOR_DEPENDENCY_NAME)
                .retryUsing(asyncRetryExecutor.withMaxRetries(5))
                .put()
                .withCircuitBreaker(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("FraudServiceLdz"))
                    .andCommandKey(HystrixCommandKey.Factory.asKey("ApplicationRequestEvent")),
                {
                    log.error("ERROR")
                    return 'ERROR'

                })
                .onUrlFromTemplate("/api/loanApplication/{applicationId}").withVariables(applicationId)
                .body(applicationDecisionDTO)
                .ignoringResponse()

        ""
    }

}