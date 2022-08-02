package com.learn.reactive.spring.LearnreactiveSpring.dailyCodeBuffer.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Book {

	private BookInfo bookInfo;
	private List<Review> reviews;

	public Book() {
		super();
	}

	public Book(BookInfo bookInfo, List<Review> reviews) {
		super();
		this.bookInfo = bookInfo;
		this.reviews = reviews;
	}

	public BookInfo getBookInfo() {
		return bookInfo;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setBookInfo(BookInfo bookInfo) {
		this.bookInfo = bookInfo;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

}
