package com.example.demo.reactive;

import io.smallrye.reactive.messaging.annotations.Emitter;
import io.smallrye.reactive.messaging.annotations.Stream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.UUID;

@Path("/reactive/emitter")
@ApplicationScoped
public class EmitterController {

    @Inject @Stream("input")
    Emitter<String> emitter;

    @GET
    @Path("/{param}")
    public String emit(@PathParam("param") String param) {
        String id = UUID.randomUUID().toString();
        emitter.send(toJson(param, id));
        return id;
    }

    private String toJson(String param, String id) {
        return String.format("{\"input\":\"%s\", \"id\":\"%s\"}", param, id);
    }

}
