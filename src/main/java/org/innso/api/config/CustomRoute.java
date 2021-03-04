package org.innso.api.config;

import io.vertx.ext.web.Router;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped 
public class CustomRoute {

    public void init(@Observes Router router) {

        router.get("/").handler(rc -> rc.response().setStatusCode(302).putHeader("Location", "/api/swagger-ui").end());
    }
}