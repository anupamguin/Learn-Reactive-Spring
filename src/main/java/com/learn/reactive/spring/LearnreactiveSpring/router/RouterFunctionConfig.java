package com.learn.reactive.spring.LearnreactiveSpring.router;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.learn.reactive.spring.LearnreactiveSpring.handler.SampleHandlerFunction;

@Configuration
public class RouterFunctionConfig {

	@Bean
	RouterFunction<ServerResponse> home() {
		return route(GET("/functional/flux"), request -> ok().body(fromValue("Home page")));
	}

	@Bean
	RouterFunction<ServerResponse> route1(SampleHandlerFunction handlerFunction) {

		return route(GET("/one").and(accept(MediaType.APPLICATION_JSON)), req -> ok().body(fromValue("HI")));
	}

//	@Bean
//	public RouterFunction<ServerResponse> route(SampleHandlerFunction handlerFunction){
//
//	    return RouterFunctions
//	            .route(GET("/functional/flux").and(accept(MediaType.APPLICATION_JSON)),handlerFunction::flux)
//	            .andRoute(GET("/functional/mono").and(accept(MediaType.APPLICATION_JSON)),handlerFunction::mono);
//
//	}

//	@Bean 
//	public RouterFunction<ServerResponse> route(SampleHandlerFunction handlerFunction){
//		return RouterFunctions.route().GET("/hi/flux"), request -> ok().body(fromValue("Home Page")));
//	}

}
