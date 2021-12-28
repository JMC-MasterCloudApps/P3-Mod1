package es.codeurjc.mastercloudapps.topo;

import es.codeurjc.mastercloudapps.topo.model.City;
import es.codeurjc.mastercloudapps.topo.view.repository.CityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AppEventListener {

  private final CityRepository cityRepository;

  @EventListener
  public void onApplicationEvent(ContextRefreshedEvent event) {
    log.info("AppEventListener 'RefreshedEvent");

    this.cityRepository.deleteAll();

    Flux<City> cities = Flux.just(
        new City("Almería", "Mountain"),
        new City("Granada", "Mountain"),
        new City("Sevilla", "Flat"),
        new City("Málaga", "Flat"),
        new City("Córdoba", "Flat"),
        new City("Huelva", "Flat"),
        new City("Cádiz", "Mountain")
    );

    cities.flatMap(this.cityRepository::save).blockLast();
  }
}
