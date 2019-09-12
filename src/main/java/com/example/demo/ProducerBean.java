package com.example.demo;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.reactivex.Flowable;
@ApplicationScoped
public class ProducerBean {

    Random random = new Random();
    @Outgoing("initial-prices")
    public Flowable<Integer> generatePrice() {
        System.out.println("Calling generatePrice()");
        return Flowable.interval(5, TimeUnit.SECONDS)
            .map(tick ->
                {
                    int price = random.nextInt(1000);
                    System.out.println("Generating price: " + price);
                    return price;

                });

    }
}