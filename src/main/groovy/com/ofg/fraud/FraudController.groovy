package com.ofg.fraud

import com.wordnik.swagger.annotations.Api
import com.wordnik.swagger.annotations.ApiOperation
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.validation.constraints.NotNull
import java.util.concurrent.Callable

import static org.springframework.web.bind.annotation.RequestMethod.PUT

@Slf4j
@RestController
@RequestMapping('/api/loanApplication')
@TypeChecked
@Api(value = "fraud", description = "Collects places from tweets and propagates them to Collerators")
class FraudController {

    private final ColleratorClient client

    @Autowired FraudController(ColleratorClient client) {
        this.client = client
    }
    //private final PropagationWorker propagationWorker

//    @Autowired
//    FraudController(PropagationWorker propagationWorker) {
//        this.propagationWorker = propagationWorker
//    }

    @RequestMapping(
            value = '{loanApplicationId}',
            method = PUT
            /*consumes = TWITTER_PLACES_ANALYZER_JSON_VERSION_1,
            produces = TWITTER_PLACES_ANALYZER_JSON_VERSION_1*/)
    @ApiOperation(value = "Async collecting and propagating of tweets for a given pairId",
            notes = "This will asynchronously call tweet collecting, place extracting and their propagation to Collerators")
    Callable<Void> getPlacesFromTweets(@PathVariable @NotNull long loanApplicationId, @RequestBody ApplicationReqestedEventDTO body) {
        return {
            def decisionDTO = new ApplicationDecisionDTO()
            client.notifyDecisionMaker(loanApplicationId, decisionDTO)
        }
    }

}
