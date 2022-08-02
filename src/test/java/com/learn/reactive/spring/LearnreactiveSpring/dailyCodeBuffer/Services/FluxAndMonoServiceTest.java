package com.learn.reactive.spring.LearnreactiveSpring.dailyCodeBuffer.Services;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.learn.reactive.spring.LearnreactiveSpring.dailyCodeBuffer.services.FluxAndMonoServices;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxAndMonoServiceTest {

	@Autowired
	 FluxAndMonoServices services = new FluxAndMonoServices();
	
	@Test
	void fruitsFlux() {
		Flux<String> fruitFlux =  services.fruitsFlux();
				
		StepVerifier.create(fruitFlux)
			.expectNext("Mango","Banana")
			.expectNext("Orange")
			.verifyComplete(); 
	}
	
	@Test
	void politicianMono() {
		Mono<String> politicalMono = services.politicianMono();
		
		StepVerifier.create(politicalMono)
			.expectNextCount(1)
			.verifyComplete();
	}
	
	@Test
	void fruitFluxMap() {
		Flux<String> fruitMap = services.fruitFluxMap();
		
		fruitMap.subscribe(System.err::println);
		
		StepVerifier.create(fruitMap)
			.expectNext("MANGO","BANANA","ORANGE")
			.verifyComplete();
	}
	
	@Test
	void fruitsFluxFilter() {
		Flux<String> fruitFilter = services.fruitsFluxFilter(5).log();
		
		fruitFilter.subscribe(System.err::print);
		
		StepVerifier.create(fruitFilter)
			.expectNext("Banana","Orange")
			.verifyComplete();
	}
	
	@Test
	void fruitFilterMap() {
		Flux<String> fruitFilterMap =  services.fruitFilterMap(5);
		fruitFilterMap.subscribe(System.out::println);
		
		StepVerifier.create(fruitFilterMap)
			.expectNext("Banana Anupam","Orange Anupam")
			.verifyComplete();
	}
	
	@Test
	void fruitFluxFlatMap() {
		Flux<String> fruitFluxFlatMap = services.fruitFluxFlatMap();
		
		StepVerifier.create(fruitFluxFlatMap)
			.expectNextCount(17)
			.verifyComplete();
	}
	
	@Test
	void fruitsFluxFlatMapAsync() {
		Flux<String> fruitsFluxFlatMapAsync = services.fruitsFluxFlatMapAsync();
		
		fruitsFluxFlatMapAsync.subscribe(System.out::println);
		StepVerifier.create(fruitsFluxFlatMapAsync)
			.expectNextCount(17)
			.verifyComplete();
	}
	
	@Test
	void fruitMonoFlatMap() {
		Mono<List<String>> fruitMonoFlatMap = services.fruitMonoFlatMap();
		
		StepVerifier.create(fruitMonoFlatMap)
			.expectNextCount(1)
			.verifyComplete();
			
	}
	
	@Test 
	void fruitFluxConcatMap() {
		Flux<String> fruitFluxConcatMap = services.fruitFluxConcatMap();
		
		StepVerifier.create(fruitFluxConcatMap)
		 	.expectNextCount(17)
		 	.verifyComplete();
	}
	
	@Test
	void fruitFluxFlatMapMany() {
		Flux<String> fruitFluxFlatMapMany = services.fruitFluxFlatMapMany();
		
		StepVerifier.create(fruitFluxFlatMapMany)
			.expectNextCount(6 )
			.verifyComplete();
	}
	
	@Test
	void fruitsFluxTransform() {
		Flux<String> fruitsFluxTransform = services.fruitsFluxTransform(5);
		
		StepVerifier.create(fruitsFluxTransform)
			.expectNext("Banana","Orange")
			.verifyComplete(); 
	}
	
	@Test
	void fruitFluxZip() {
		Flux<String> fruitFluxZip = services.fruitFluxZip();
		
		StepVerifier.create(fruitFluxZip)
			.expectNext("MangoTomato","BananaLemon")
			.verifyComplete();
	}
	
	@Test
	void fruitFluxZipTuple() {
		Flux<String> fruitFluxZipTuple = services.fruitFluxZipTuple();
		
		StepVerifier.create(fruitFluxZipTuple)
			.expectNextCount(2)
			.verifyComplete(); 
	}
	
	@Test
	void FruitFluxFilterDoOn() {
		Flux<String> FruitFluxFilterDoOn = services.FruitFluxFilterDoOn(5);
		
		StepVerifier.create(FruitFluxFilterDoOn)
			.expectNext("Banana","Orange")
			.verifyComplete();
	}
	
	@Test
	void fruitFluxOnErrorReturn() {
		Flux<String> fruitFluxOnErrorReturn = services.fruitFluxOnErrorReturn();
		
		StepVerifier.create(fruitFluxOnErrorReturn)
			.expectNextCount(3)
			.verifyComplete();
	}
	
	@Test
	void fruitFluxOnErrorContinue() {
		Flux<String> fruitFluxOnErrorContinue = services.fruitFluxOnErrorContinue();
		
		StepVerifier.create(fruitFluxOnErrorContinue)
			.expectNextCount(1)
			.verifyComplete();
	}
	
}
