package com.learn.reactive.spring.LearnreactiveSpring.dailyCodeBuffer.services;

import java.util.Arrays;
import java.util.List;

import com.learn.reactive.spring.LearnreactiveSpring.dailyCodeBuffer.domain.BookInfo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BookInfoService {

	public Flux<BookInfo> getBooks() {
		List<BookInfo> books = Arrays.asList(new BookInfo(1, "Book One", "Author One", "78675"),
				new BookInfo(2, "Book Two", "Author Two", "4534"),
				new BookInfo(3, "Book Three", "Author Three", "3453"),
				new BookInfo(4, "Book Four", "Author Four", "434533"));
		return Flux.fromIterable(books);
	}

	public Mono<BookInfo> getBookById(long bookid) {

		BookInfo bookInfo = new BookInfo(bookid, "Book One", "Author One", "78675");
		return Mono.just(bookInfo);
	}
}
