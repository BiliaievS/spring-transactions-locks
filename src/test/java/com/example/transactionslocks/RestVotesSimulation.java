package com.example.transactionslocks;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.HttpDsl;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.http.HttpDsl.http;

public class RestVotesSimulation extends Simulation {

    public RestVotesSimulation() {
        ScenarioBuilder scn = scenario("Load rest test Add Votes")
                .exec(HttpDsl.http("Rest addvotes request 0")
                        .post("/addvotes")
                        .header("Content-Type", "application/json")
                        .body(StringBody(postBodyJson_WC123())))
                .exec(HttpDsl.http("Rest addvotes request 1")
                        .post("/addvotes")
                        .header("Content-Type", "application/json")
                        .body(StringBody(postBodyJson())));

        ProtocolBuilder localProtocol = http.baseUrl("http://localhost:8080")
                .acceptHeader("*/*")
                .acceptEncodingHeader("gzip, deflate, br")
                .maxConnectionsPerHost(10)
                .userAgentHeader("Add votes performance test");

        setUp(scn.injectOpen(constantUsersPerSec(250).during(2)))
                .protocols(localProtocol);
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

    @Override
    public void before() {
        System.out.println("Simulation is about to start!");
    }

    @Override
    public void after() {
        System.out.println("Simulation is finished!");
    }
}
