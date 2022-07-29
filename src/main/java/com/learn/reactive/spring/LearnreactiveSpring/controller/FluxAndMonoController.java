package com.learn.reactive.spring.LearnreactiveSpring.controller;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class FluxAndMonoController {

	@GetMapping("/flux")
	public Flux<Integer> getFlux(){
		return Flux.just(1,2,3,4,5).log();
	}
	
	@GetMapping("/fluxdelay")
	public Flux<Integer> getDelayFlux(){
		return Flux.just(11,22,33,44)
					.delayElements(Duration.ofSeconds(1))
					.log();
	}
	
	@SuppressWarnings("deprecation")
	@GetMapping(value="/fluxdelayStream",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Integer> getDelayFluxWithStreamJson(){
		return Flux.just(10,20,30,40,50)
					.delayElements(Duration.ofSeconds(1))
					.log();
	}
	
	@SuppressWarnings("deprecation")
	@GetMapping(value="/fluxDelayInfiniteStream",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Long> fluxDelayInfiniteStream(){
		return Flux.interval(Duration.ofSeconds(1))
				.log();
	}
	
	@GetMapping("/mono")
	public Mono<String> getMono(){
		return Mono.just("Anupam Guin").log();
	}
}
