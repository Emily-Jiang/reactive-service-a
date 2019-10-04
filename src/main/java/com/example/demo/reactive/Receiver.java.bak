package com.example.demo.reactive;

import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class Receiver {

    private Map<String, String> queue = new ConcurrentHashMap<>();

    @Incoming("output")
    public void consume(JsonObject json) {
        System.out.println("Receiving " + json);
        queue.put(json.getString("id"), json.getString("result"));
    }

    public String retrieve(String id) {
        String value = queue.remove(id);
        if (value == null) {
            return "No result yet, please come back later...";
        } else {
            return value;
        }
    }

}
