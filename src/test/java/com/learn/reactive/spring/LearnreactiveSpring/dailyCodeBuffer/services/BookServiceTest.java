package com.learn.reactive.spring.LearnreactiveSpring.dailyCodeBuffer.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.learn.reactive.spring.LearnreactiveSpring.dailyCodeBuffer.domain.Book;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class BookServiceTest {

	private BookInfoService bookInfoService = new BookInfoService();
	private ReviewService reviewService = new ReviewService();
	private BookService bookService = new BookService(bookInfoService, reviewService);

	@Test
	void getBooks() {
		Flux<Book> books = bookService.getBooks();
		StepVerifier.create(books).assertNext(book -> {
			assertEquals("Book One", book.getBookInfo().getTitle());
			assertEquals(2, book.getReviews().size());
		}).assertNext(book -> {
			assertEquals("Book Two", book.getBookInfo().getTitle());
			assertEquals(2, book.getReviews().size());
		}).assertNext(book -> {
			assertEquals("Book Three", book.getBookInfo().getTitle());
			assertEquals(2, book.getReviews().size());
		}).assertNext(book -> {
			assertEquals("Book Four", book.getBookInfo().getTitle());
			assertEquals(2, book.getReviews().size());
		}).verifyComplete();
	}

	@Test
	void getBookById() {
		var book = bookService.getBookById(1);
		StepVerifier.create(book).assertNext(b -> {
			assertEquals("Book One", b.getBookInfo().getTitle());
			assertEquals(2, b.getReviews().size());
		}).verifyComplete();
	}
}
