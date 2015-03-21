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
@Api(value = "fraud", description = "Fraudy cmon")
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
    @ApiOperation(value = "Fraudy",
            notes = "Fraudy")
    Callable<Void> getPlacesFromTweets(@PathVariable @NotNull long loanApplicationId, @RequestBody ApplicationReqestedEventDTO body) {
        return {
            ApplicationDecisionDTO decisionDTO = new ApplicationDecisionDTO()
            decisionDTO.amount = body.amount
            decisionDTO.firstName = body.firstName
            decisionDTO.lastName = body.lastName
            decisionDTO.job = body.job

            switch (loanApplicationId % 3) {
                case 0L:
                    decisionDTO.fraudStatus = "fraud"
                    break
                case 1L:
                    decisionDTO.fraudStatus = "fishy"
                    break
                case 2L:
                    decisionDTO.fraudStatus = "good"
                    break
            }

            client.notifyDecisionMaker(loanApplicationId, decisionDTO)
        }
    }

}
