package com.learn.reactive.spring.LearnreactiveSpring.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ListBodySpec;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@WebFluxTest
public class FluxAndMonoControllerTest {

	@Autowired
	WebTestClient webTestClient;
	
	@Test
	public void flux_approach1() {
		Flux<Integer> intFlux = webTestClient.get()
			.uri("/flux")
			.accept(MediaType.APPLICATION_JSON)
			.exchange() // that invoke the endpoint
			.expectStatus().isOk()
			.returnResult(Integer.class)
			.getResponseBody();
		
		StepVerifier.create(intFlux)
				.expectSubscription()
				.expectNext(1)
				.expectNext(2)
				.expectNext(3,4,5)
				.verifyComplete();
	}
	
	
	@Test
	public void flux_approach2() {
		
		ListBodySpec<Integer> expectBodyList = webTestClient.get()
		  .uri("/flux")
		  .accept(MediaType.APPLICATION_JSON)
		  .exchange()
		  .expectStatus().isOk()
		  .expectHeader().contentType(MediaType.APPLICATION_JSON)
		  .expectBodyList(Integer.class)
		  .hasSize(5);
	}
	
	@Test 
	public void flux_approach3() {
		
		List<Integer> expectedIntegerList = Arrays.asList(1,2,3,4,5);
		
		EntityExchangeResult<List<Integer>> returnResult = webTestClient.get()
			.uri("/flux")
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus().isOk()
			.expectBodyList(Integer.class)
			.returnResult();
		
		assertEquals(expectedIntegerList, returnResult.getResponseBody());
	}
	
	
	@Test
	public void flux_infinite() {
		Flux<Long> responseBody = webTestClient.get().uri("/fluxDelayInfiniteStream")
			  .accept(MediaType.APPLICATION_JSON)
			  .exchange()
			  .expectStatus().isOk()
			  .returnResult(Long.class)
			  .getResponseBody();
		
		StepVerifier.create(responseBody)
				.expectNext(0l)
				.expectNext(1l)
				.expectNext(3l)
				.thenCancel()
				.verify();
				
	}
	
	@Test
	public void mono_test() {
		   webTestClient.get().uri("/mono")
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus().isOk()
			.expectBody(String.class)
			.consumeWith( response -> {
				assertEquals("Anupam Guin", response.getResponseBody());
			});
		
	}
}
