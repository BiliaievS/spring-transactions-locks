package com.example.transactionslocks;

import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.OpenInjectionStep.ConstantRate.ConstantRateOpenInjectionStep;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpDsl;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.StringBody;

public class RestVotesSimulation extends Simulation {

    private static final HttpProtocolBuilder PROTOCOL_BUILDER = setupProtocol().acceptEncodingHeader("gzip, deflate, br").maxConnectionsPerHost(10).userAgentHeader("Add votes performance test");

    private static HttpProtocolBuilder setupProtocol() {
        return HttpDsl.http.baseUrl("http://localhost:8080").acceptHeader("*/*");
    }

    private static final ScenarioBuilder POST_SCN = setupScenario();

    private static ScenarioBuilder setupScenario() {
        return CoreDsl.scenario("Load test Add Votes")
                .exec(HttpDsl.http("post_add_votes_C#")
                        .post("/addvotes")
                        .header("Content-Type", "application/json")
                        .body(StringBody(postBodyJson_WC123())))
                .exec(HttpDsl.http("post_add_votes_java")
                        .post("/addvotes")
                        .header("Content-Type", "application/json")
                        .body(StringBody(postBodyJson())));
    }

    private static String postBodyJson_WC123() {
        return """
                {
                "code": "WC123",
                "technology": "C#",
                "votes": 1
                }
                """;
    }

    private static String postBodyJson() {
        return """
                {
                "code": "WP001",
                "technology": "Java",
                "votes": 1
                }
                """;
    }

    public RestVotesSimulation() {
        setUp(POST_SCN.injectOpen(postInjectionProfile())).protocols(PROTOCOL_BUILDER);
    }

    private ConstantRateOpenInjectionStep postInjectionProfile() {
        return CoreDsl.constantUsersPerSec(250).during(2);
    }
}
