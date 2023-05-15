package com.example.transactionslocks;

import io.gatling.javaapi.core.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.http.HttpDsl.http;

public class KafkaVotesSimulation extends Simulation {

    public KafkaVotesSimulation() {
        ScenarioBuilder scn = scenario("Kafka Load Test to Add Votes")
                .exec(http("kafka message 0")
                        .post("/votetask")
                        .header("Content-Type", "application/json")
                        .body(StringBody(postBodyJson_0())))
                .exec(http("kafka message 1")
                        .post("/votetask")
                        .header("Content-Type", "application/json")
                        .body(StringBody(postBodyJson_1())));


        ProtocolBuilder localProtocol = http.baseUrl("http://localhost:8080")
                .acceptHeader("*/*")
                .acceptEncodingHeader("gzip, deflate, br")
                .maxConnectionsPerHost(10)
                .userAgentHeader("Add vote to send kafka message");

        setUp(scn.injectOpen(constantUsersPerSec(250).during(2)))
                .protocols(localProtocol);
    }

    private String postBodyJson_0() {
        return """
                {
                "code": "WC123",
                "technology": "C#",
                "votes": 1
                }
                """;
    }

    private String postBodyJson_1() {
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
