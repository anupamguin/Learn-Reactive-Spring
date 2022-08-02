package com.learn.reactive.spring.LearnreactiveSpring.dailyCodeBuffer.services;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FluxAndMonoServices {

	public Flux<String> fruitsFlux() {
		return Flux.fromIterable(Arrays.asList("Mango", "Banana", "Orange")).log();
	}

	public Mono<String> politicianMono() {
		return Mono.just("Arvind Kejriwal").log();
	}

	public Flux<String> fruitFluxMap() {
		return Flux.fromIterable(Arrays.asList("Mango", "Banana", "Orange")).map(String::toUpperCase);
	}

	public Flux<String> fruitsFluxFilter(int number) {
		return Flux.fromIterable(Arrays.asList("Mango", "Banana", "Orange")).filter(s -> s.length() > number);
	}

	public Flux<String> fruitFilterMap(int number) {
		return Flux.fromIterable(Arrays.asList("Mango", "Banana", "Orange")).filter(i -> i.length() > number)
				.map(j -> j.concat(" Anupam"));

	}

	public Flux<String> fruitFluxFlatMap() {

		return Flux.fromIterable(Arrays.asList("Mango", "Banana", "Orange")).flatMap(s -> Flux.just(s.split(""))).log();
	}
	
	public Flux<String> fruitsFluxFlatMapAsync(){
		return Flux.fromIterable(Arrays.asList("Mango", "Banana", "Orange"))
				.flatMap(i -> Flux.just(i.split("")))
				.delayElements(Duration.ofMillis(
						new Random().nextInt(1000)))
				.log();
	}
	
	public Mono<List<String>> fruitMonoFlatMap(){
		return Mono.just("Mango")
				.flatMap(s -> Mono.just(Arrays.asList(s.split(""))))
				.log();	
	}

	public Flux<String> fruitFluxConcatMap(){
		return Flux.fromIterable(Arrays.asList("Mango", "Banana", "Orange"))
					.concatMap(i -> Flux.just(i.split("")))
					.delayElements(Duration.ofMillis(new Random().nextInt(1000)))
					.log();
	}
	
	public Flux<String> fruitFluxFlatMapMany(){
		return Mono.just("Anupam")
				.flatMapMany(i -> Flux.just(i.split("")))
				.log();
	}
	
	public Flux<String> fruitsFluxTransform(int number){
		
		Function<Flux<String>,Flux<String>> filterData = 
				data -> data.filter(i -> i.length() > number);
				
		return Flux.fromIterable(Arrays.asList("Mango", "Banana", "Orange"))
				.transform(filterData)
				.defaultIfEmpty("Default")
				.switchIfEmpty(Flux.just("NUNU","PUPU"))
				.log();
	}
	
	public Flux<String> fruitFluxZip(){
		Flux<String> fruits = Flux.just("Mango","Banana");
		Flux<String> veggies = Flux.just("Tomato","Lemon");
		
		return Flux.zip(fruits, veggies , (first,second) -> first+second).log();
	}
	
	public Flux<String> fruitFluxZipTuple(){
		Flux<String> fruits = Flux.just("Mango","Banana");
		Flux<String> veggies = Flux.just("Tomato","Lemon");
		Flux<String> moreVeggies = Flux.just("Potato","Beans");
		
		return Flux.zip(fruits,veggies,moreVeggies)
					.map(objects -> objects.getT1()+objects.getT2()+objects.getT3());
	}
	
	public Flux<String> FruitFluxFilterDoOn(int number){
		return Flux.fromIterable(Arrays.asList("Mango", "Banana", "Orange"))
					.filter(s -> s.length() > number)
					.doOnNext(s -> {
						System.err.println("s = "+s);
					})
					.doOnSubscribe(subscription ->{
						System.out.println("subscription = "+subscription.toString());
					})
					.doOnComplete(()-> System.err.println("Complete...!!!!"));
	}
	
	public Flux<String> fruitFluxOnErrorReturn(){
		return Flux.just("Apple","Mango")
				.concatWith(Flux.error(new RuntimeException()))
				.onErrorReturn("Orange");
	}
	
	public Flux<String> fruitFluxOnErrorContinue(){
		return Flux.just("Apple","Mango","Orange")
				.map(s -> {
					if(s.equalsIgnoreCase("Mango"))
						throw new RuntimeException("Exception Occured");
					return s.toUpperCase();
				})
				.onErrorContinue((e,f)->{
					System.out.println("e -> "+e);
					System.out.println("f -> "+f);
				});
	}
	
	public static void main(String[] args) {
		
		FluxAndMonoServices fluxAndMonoServices = new FluxAndMonoServices();

		fluxAndMonoServices.fruitsFlux().subscribe(f -> System.out.println(f));

		fluxAndMonoServices.politicianMono().subscribe(System.out::println);

		fluxAndMonoServices.fruitFluxMap().subscribe(System.out::println);

		fluxAndMonoServices.fruitsFluxFilter(5).subscribe(System.err::println);

		fluxAndMonoServices.fruitFilterMap(5).subscribe(System.out::println);

		fluxAndMonoServices.fruitFluxFlatMap().subscribe(System.err::println);

		fluxAndMonoServices.fruitsFluxFlatMapAsync().subscribe(i -> System.out.println("Async -> "+i));
		
		fluxAndMonoServices.fruitMonoFlatMap().subscribe(i -> System.out.println("FLat Map -> "+i));

		fluxAndMonoServices.fruitFluxConcatMap().subscribe(i -> System.err.println("concat Map -> "+i));

		fluxAndMonoServices.fruitFluxFlatMapMany().subscribe(i -> System.err.println("Flat Map Many -> "+i));

		fluxAndMonoServices.fruitsFluxTransform(5).subscribe(i -> System.err.println("Transform -> "+i));
		
		fluxAndMonoServices.fruitFluxZip().subscribe(i -> System.err.println("Zip -> "+i));
		
		fluxAndMonoServices.fruitFluxZipTuple().subscribe(i -> System.err.println("Tuple -> "+i));
		
		fluxAndMonoServices.FruitFluxFilterDoOn(5).subscribe(i -> System.err.println("Do ON -> "+i));
		
		fluxAndMonoServices.fruitFluxOnErrorReturn().subscribe(i -> System.err.println("Error on returen -> "+i));

		fluxAndMonoServices.fruitFluxOnErrorContinue().subscribe(i -> System.err.println("Error on Continue -> "+i));

	}
}
