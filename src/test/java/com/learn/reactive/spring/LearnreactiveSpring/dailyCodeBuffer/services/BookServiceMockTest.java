package com.learn.reactive.spring.LearnreactiveSpring.dailyCodeBuffer.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.learn.reactive.spring.LearnreactiveSpring.dailyCodeBuffer.exception.BookException;

import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class BookServiceMockTest {

	@Mock
	private BookInfoService bookInfoService;
	@Mock
	private ReviewService reviewService;
	@InjectMocks
	private BookService bookService;
	
	@Test
	void getBooksMock() {
		
		Mockito.when(bookInfoService.getBooks()).thenCallRealMethod();
		
		Mockito.when(reviewService.getReviews(Mockito.anyLong())).thenCallRealMethod();
		
		var books = bookService.getBooks();
		
		StepVerifier.create(books)
			.expectNextCount(4)
			.verifyComplete();
	}

	@Test 
	void getBooksMockOnError(){
		
		Mockito.when(bookInfoService.getBooks()).thenCallRealMethod();
		
		Mockito.when(reviewService.getReviews(Mockito.anyLong()))
		 .thenThrow(new IllegalStateException("Excption Anupam"));
		
		var books = bookService.getBooks();
		
		StepVerifier.create(books)
			.expectError(BookException.class)
			.verify();
	}
	
	  @Test
	    void getBooksMockOnErrorRetry() {

	        Mockito.when(bookInfoService.getBooks())
	                .thenCallRealMethod();

	        Mockito.when(reviewService.getReviews(Mockito.anyLong()))
	                .thenThrow(new IllegalStateException("exception using test"));

	        var books = bookService.getBooksRetry();

	        StepVerifier.create(books)
	                .expectError(BookException.class)
	                .verify();
	  }
}
