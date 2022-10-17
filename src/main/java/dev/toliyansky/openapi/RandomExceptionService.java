package dev.toliyansky.openapi;

import org.springframework.stereotype.Service;

@Service
public class RandomExceptionService {

    public void generateException50percentChance() {
        if (Math.random() > 0.5) {
            throw new RuntimeException("Random exception");
        }
    }
}
