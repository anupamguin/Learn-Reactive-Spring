package com.learn.reactive.spring.LearnreactiveSpring.dailyCodeBuffer.services;

import java.util.List;

import com.learn.reactive.spring.LearnreactiveSpring.dailyCodeBuffer.domain.Book;
import com.learn.reactive.spring.LearnreactiveSpring.dailyCodeBuffer.domain.BookInfo;
import com.learn.reactive.spring.LearnreactiveSpring.dailyCodeBuffer.domain.Review;
import com.learn.reactive.spring.LearnreactiveSpring.dailyCodeBuffer.exception.BookException;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class BookService {

	private BookInfoService bookInfoService;
	private ReviewService reviewService;

	public BookService(BookInfoService bookInfoService, ReviewService reviewService) {
		super();
		this.bookInfoService = bookInfoService;
		this.reviewService = reviewService;
	}

	public Flux<Book> getBooks() {

		Flux<BookInfo> allBooks = bookInfoService.getBooks();
		return allBooks.flatMap(bookInfo -> {
			Mono<List<Review>> reviews = reviewService.getReviews(bookInfo.getBookId()).collectList();
			return reviews.map(review -> new Book(bookInfo, review));
		})
		.onErrorMap(throwable ->{
			return new BookException("Exception occurred while fetching Books");
		})
		.log();
	}

	public Mono<Book> getBookById(long bookId) {
		var book = bookInfoService.getBookById(bookId);
		var review = reviewService.getReviews(bookId).collectList();
		return book.zipWith(review, (b, r) -> new Book(b, r));
	}
	
	public Flux<Book> getBooksRetry() {
        var allBooks = bookInfoService.getBooks();
        return allBooks
                .flatMap(bookInfo -> {
                    Mono<List<Review>> reviews =
                            reviewService.getReviews(bookInfo.getBookId()).collectList();
                    return reviews
                            .map(review -> new Book(bookInfo,review));
                })
                .onErrorMap(throwable -> {
                    return new BookException("Exception occurred while fetching Books");
                })
                .retry(3)
                .log();
    }

}
