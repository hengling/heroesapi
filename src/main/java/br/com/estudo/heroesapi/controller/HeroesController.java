package br.com.estudo.heroesapi.controller;

import br.com.estudo.heroesapi.document.Heroes;
import br.com.estudo.heroesapi.service.HeroesService;
import br.com.estudo.heroesapi.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HeroesController {

    private final HeroesService heroesService;

    @GetMapping(Constants.Heroes.ENDPOINT)
    public Flux<Heroes> getAllItems() {
        log.info("requesting the list of all heroes");
        return heroesService.findAll();
    }

    @GetMapping(Constants.Heroes.ENDPOINT + "/{id}")
    public Mono<ResponseEntity<Heroes>> findById(@PathVariable String id) {
        log.info("requesting the hero with id {}", id);
        return heroesService.findById(id)
                .map(item -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(Constants.Heroes.ENDPOINT)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Heroes> createHero(@RequestBody Heroes heroes) {
        log.info("creating a new hero");
        return heroesService.save(heroes);
    }

    @DeleteMapping(Constants.Heroes.ENDPOINT + "/{id}")
    public Mono<HttpStatus> deleteById(@PathVariable String id) {
        log.info("deleting a hero with id {}", id);
        heroesService.deleteById(id);

        return Mono.just(HttpStatus.CONTINUE);
    }
}
