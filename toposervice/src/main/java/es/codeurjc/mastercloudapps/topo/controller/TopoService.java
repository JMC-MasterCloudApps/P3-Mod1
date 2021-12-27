package es.codeurjc.mastercloudapps.topo.controller;

import static java.time.Duration.ofMillis;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static reactor.core.publisher.Mono.error;

import es.codeurjc.mastercloudapps.topo.model.City;
import es.codeurjc.mastercloudapps.topo.view.repository.CityRepository;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Random;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TopoService {

    private static final long MIN_DURATION_MS = 1000;
    private static final int MAX_EXTENSION_MS = 2000;

    private final CityRepository cityRepository;

    public Mono<City> getCity(String id) throws NoSuchAlgorithmException {

        final Random rand = SecureRandom.getInstanceStrong();
        final Duration delay = ofMillis(MIN_DURATION_MS + rand.nextInt(MAX_EXTENSION_MS));

        return cityRepository.findByIdIgnoreCase(id)
            .delayElement(delay)
            .switchIfEmpty(error(new ResponseStatusException(NOT_FOUND, "City with id " + id + " not found")));
    }
}
