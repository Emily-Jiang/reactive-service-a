package com.example.demo.reactive;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@ApplicationScoped
@Path("/reactive/reception")
public class ReceiverController {

    @Inject Receiver receiver;

    @GET
    @Path("/{id}")
    public String get(@PathParam("id") String id) {
        return receiver.retrieve(id);
    }

}
