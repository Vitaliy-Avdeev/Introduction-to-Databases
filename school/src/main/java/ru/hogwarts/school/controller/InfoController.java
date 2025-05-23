package ru.hogwarts.school.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.stream.Stream;

@RestController
@Profile("production")
public class InfoController {
    private final Logger logger = LoggerFactory.getLogger(InfoController.class);
    @Value("${server.port}")
    String port;

    @GetMapping("/port")
    public String getPort() {
        return "port = " + port;
    }


    @GetMapping("/calculate")
    public void calculate() {
        int maxSize = 1_000_000;
        long startTime = System.currentTimeMillis();
        final Integer reduce = Stream.iterate(1, a -> a + 1).limit(maxSize)

                .reduce(0, (a, b) -> a + b);
        long timeConsumed = System.currentTimeMillis() - startTime;
        logger.info("Время работы{}", timeConsumed);

        startTime = System.currentTimeMillis();
        final Integer reduce1 = Stream.iterate(1, a -> a + 1).limit(maxSize)
                .reduce(0, Integer::sum);
        timeConsumed = System.currentTimeMillis() - startTime;
        logger.info("Время работы после оптимизации{}", timeConsumed);


    }

}



