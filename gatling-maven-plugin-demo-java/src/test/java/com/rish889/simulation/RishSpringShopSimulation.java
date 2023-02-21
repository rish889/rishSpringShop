package com.rish889.simulation;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class RishSpringShopSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8080");
    ScenarioBuilder users = scenario("Users")
            .during(40)
            .on(exec(http("request_1").get("/")));

    {
        setUp(
                users.injectOpen(rampUsers(10).during(10))
        ).protocols(httpProtocol);
    }
}
