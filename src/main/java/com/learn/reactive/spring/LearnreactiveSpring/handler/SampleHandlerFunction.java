package com.learn.reactive.spring.LearnreactiveSpring.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component  // This class scanned as bean
public class SampleHandlerFunction {
	
	public Mono<ServerResponse> flux(ServerRequest serverRequest){
		return ServerResponse.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(
							Flux.just(90,100,110).log(),Integer.class
						 );
	}
	
	public Mono<ServerResponse> mono(ServerRequest serverRequest){
		return ServerResponse.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(
							Mono.just(3333).log(), Integer.class
						 );
	}
}
