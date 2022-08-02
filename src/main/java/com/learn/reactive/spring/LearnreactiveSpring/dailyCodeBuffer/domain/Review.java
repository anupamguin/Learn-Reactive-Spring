package com.learn.reactive.spring.LearnreactiveSpring.dailyCodeBuffer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Review {

	private long reviewId;
	private long bookId;
	private double ratings;
	private String comments;

	public Review() {
		super();
	}

	public Review(long reviewId, long bookId, double ratings, String comments) {
		super();
		this.reviewId = reviewId;
		this.bookId = bookId;
		this.ratings = ratings;
		this.comments = comments;
	}

	public long getReviewId() {
		return reviewId;
	}

	public long getBookId() {
		return bookId;
	}

	public double getRatings() {
		return ratings;
	}

	public String getComments() {
		return comments;
	}

	public void setReviewId(long reviewId) {
		this.reviewId = reviewId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public void setRatings(double ratings) {
		this.ratings = ratings;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
