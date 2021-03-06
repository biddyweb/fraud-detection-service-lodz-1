package com.ofg.fraud

import com.nurkiewicz.asyncretry.AsyncRetryExecutor
import com.ofg.infrastructure.web.resttemplate.fluent.ServiceRestClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FraudConfiguration {

    @Bean
    ColleratorClient colleratorClient(ServiceRestClient serviceRestClient, AsyncRetryExecutor asyncRetryExecutor) {
        return new ColleratorClient(serviceRestClient, asyncRetryExecutor)
    }

}
