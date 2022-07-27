package com.learn.reactive.spring.fluxmonoplayground;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;

public class FluxMonoTest {

	@Test
	public void fluxTest() {

		Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring"); // create flux of string

		stringFlux.subscribe(System.out::println);
		// after subscribe we can activiely attaching subscriber which is going to read
		// all the values from the flux.

		System.err.println("-----------------------------------------------------------");

		Flux<String> anupamFlux = Flux.just("Anupam", "eTrans", "Arambagh").log();
		anupamFlux.subscribe(System.out::println);

		System.err.println("-----------------------------------------------------------");

		Flux<String> myFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
				.concatWith(Flux.error(new RuntimeException("Anupam Exception Occured"))) // create exception & add with
																							// flux
				.log(); // log is going to log all that events that happened behinds the scene when we
						// call subscribe method.

		myFlux.subscribe(System.out::println, (e) -> System.err.println(e));

		System.err.println("-----------------------------------------------------------");

		Flux<String> durgaFlux = Flux.just("Durga", "Soft", "Technologies")
				.concatWith(Flux.error(new Exception("Durga Exception")))
				.concatWith(Flux.just("After Error"))  // This not printed because once error occured in flux that not send any more data after that 
				.log(); // 

		durgaFlux.subscribe(System.out::println, exception-> System.err.println(exception));
		
		
		System.err.println("-----------------------------------------------------------");
		
		Flux<String> newFlux = Flux.just("One","Two","Three","Four")
				 .concatWith(Flux.just("Five"))
				 .log();
		
		newFlux.subscribe(response -> System.out.println(response)    // First for get flux response
			             ,exception -> System.err.println(exception) // this if exception occured
				         ,()-> System.err.println("Completed...")); // this for successful completation
		
	}
}
