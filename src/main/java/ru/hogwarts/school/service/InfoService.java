package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
public class InfoService {
    @Value("${server.port}")
    private int port;
    private static final Logger log = LoggerFactory.getLogger(InfoService.class);

    public InfoService() {
    }

    public int getPort() {
        log.debug("Вызван метод getPort");
        return port;
    }

    public int getSum() {
        log.debug("Вызван метод getSum");
        int num = 1_000_000;
        return IntStream.range(1, num + 1).sum();
    }
}