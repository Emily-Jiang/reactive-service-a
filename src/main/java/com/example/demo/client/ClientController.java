package com.example.demo.client;

import org.eclipse.microprofile.context.ManagedExecutor;
import org.eclipse.microprofile.context.ThreadContext;
import org.eclipse.microprofile.faulttolerance.Asynchronous;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/client")
@ApplicationScoped
public class ClientController {

    @Inject
    @RestClient
    private Service service;

    ManagedExecutor executor = ManagedExecutor.builder()
    .maxAsync(10)
    .propagated(ThreadContext.APPLICATION, ThreadContext.SECURITY)
    .cleared(ThreadContext.ALL_REMAINING)
    .build();


    @GET
    @Path("/test/{parameter}")
    @Timeout(1000)
    @Asynchronous
    @Fallback(fallbackMethod = "myFallback")
    public CompletionStage<String> onClientSide(@PathParam("parameter") String parameter) {
        return executor.supplyAsync(() -> service.doSomething(parameter));
    }

    public CompletionStage<String> myFallback(@PathParam("parameter") String parameter) {
        return CompletableFuture.completedFuture("oops. You are on fallback");
    }
}
