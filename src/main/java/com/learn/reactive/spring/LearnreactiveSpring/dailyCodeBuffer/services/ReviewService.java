package com.learn.reactive.spring.LearnreactiveSpring.dailyCodeBuffer.services;

import java.util.Arrays;
import java.util.List;

import com.learn.reactive.spring.LearnreactiveSpring.dailyCodeBuffer.domain.Review;

import reactor.core.publisher.Flux;

public class ReviewService {

	public Flux<Review> getReviews(long bookId) {
		List<Review> list = Arrays.asList(new Review(1, bookId, 9.1, "Good Book"),
				new Review(2, bookId, 8.6, "Worth Reading"));
		
		return Flux.fromIterable(list);
	}
}
