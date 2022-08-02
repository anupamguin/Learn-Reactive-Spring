package com.learn.reactive.spring.LearnreactiveSpring.dailyCodeBuffer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class BookInfo {

	private long bookId;
	private String title;
	private String author;
	private String ISBN;

	public long getBookId() {
		return bookId;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public BookInfo() {
		super();
	}

	public BookInfo(long bookId, String title, String author, String iSBN) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		ISBN = iSBN;
	}

}
