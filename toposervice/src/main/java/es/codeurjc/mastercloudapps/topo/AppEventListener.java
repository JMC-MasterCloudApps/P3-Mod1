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
        new City("Madrid", "Flat"),
        new City("Barcelona", "Flat"),
        new City("Jaca", "Mountain"),
        new City("Andorra", "Mountain"),
        new City("Valencia", "Flat"),
        new City("Sevilla", "Mountain"),
        new City("Zaragoza", "Flat"),
        new City("Málaga", "Mountain"),
        new City("Murcia", "Flat"),
        new City("Palma", "Mountain"),
        new City("Bilbao", "Flat"),
        new City("Alicante", "Mountain"),
        new City("Córdoba", "Flat"),
        new City("Valladolid", "Mountain"),
        new City("Vigo", "Flat"),
        new City("Gijón", "Mountain"),
        new City("Vitoria", "Flat")
    );

    cities.flatMap(this.cityRepository::save).blockLast();
  }
}
