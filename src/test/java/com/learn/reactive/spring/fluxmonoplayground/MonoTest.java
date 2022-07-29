package com.learn.reactive.spring.fluxmonoplayground;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MonoTest {

	@Test
	public void monotest() {
		Mono<String> monoString = Mono.just("Kolkata");
		
		StepVerifier.create(monoString.log())
			.expectNext("Kolkata")
			.verifyComplete();
		
		System.err.println("---------------------------------------------");
	}
	
	
	@Test
	public void monoTest_Error() {
		
		StepVerifier.create(Mono.error(new RuntimeException("Exception Occured.")).log())
			.expectError(RuntimeException.class)
			.verify();
	}
}
