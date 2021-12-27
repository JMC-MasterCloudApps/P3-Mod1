package es.codeurjc.mastercloudapps.topo.view.rest;

import es.codeurjc.mastercloudapps.topo.model.City;
import es.codeurjc.mastercloudapps.topo.controller.TopoService;
import java.security.NoSuchAlgorithmException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/topographicdetails")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TopoController {

    private final TopoService topoService;

    @GetMapping("/{city}")
    public Mono<CityDTO> getCity(@PathVariable String city) throws NoSuchAlgorithmException {
        return topoService.getCity(city).map(this::toCityDTO);
    }

    private CityDTO toCityDTO(City city) {
        return new CityDTO(city.getId(), city.getLandscape());
    }

}
