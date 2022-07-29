package com.learn.reactive.spring.fluxmonoplayground;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxTest {

	@Test
	public void fluxTestElements_WithError() {
		
		Flux<String> firstFlux = Flux.just("West Bengal","Odisha","Delhi").log();
		
		StepVerifier.create(firstFlux)
		   .expectNext("West Bengal")
		   .expectNext("Odisha","Delhi")
		   .verifyComplete();
		
		System.err.println("-----------------------------------------------------------");
		
		
		Flux<String> secondFlux = Flux.just("one","two","three")
				.concatWith(Flux.error(new RuntimeException("Exception Created ..")))
				.log();
		StepVerifier.create(secondFlux)
			.expectNext("one")
			.expectNext("two")
			.expectNext("three")
			.expectError(RuntimeException.class)
			.verify();
		
		System.err.println("-----------------------------------------------------------");
		
		Flux<String> threeFlux = Flux.just("four","five","six")
				.concatWith(Flux.error(new RuntimeException("Exception Created ..")))
				.log();
		StepVerifier.create(threeFlux)
		     .expectNextCount(3)
		     .expectErrorMessage("Exception Created ..")
		     .verify();
	}
	
}
