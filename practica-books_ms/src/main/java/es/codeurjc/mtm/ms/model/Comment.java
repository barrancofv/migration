package es.codeurjc.mtm.ms.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;

public class Comment {

	public interface Basic {}
	public interface WithBook {}
	public interface WithUser {}


	private Long id;


	private String publishtext;


	private int punctuation;
	

	private Book book;
	

	private User user;

	public Comment() {
	}

	public Comment(User user, int punctuation, String publishtext) {

		this.user = user;
		this.publishtext = publishtext;
		this.punctuation = punctuation;
	}

	public Comment(Comment comment) {
		this.id = comment.id;
		this.user = comment.user;
		this.publishtext = comment.publishtext;
		this.punctuation = comment.punctuation;
		this.book = comment.book;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public void setName(User user) {
		this.user = user;
	}

	public String getPublishtext() {
		return publishtext;
	}

	public void setPublishtext(String publishtext) {
		this.publishtext = publishtext;
	}

	public int getPunctuation() {
		return punctuation;
	}

	public void setPunctuation(int punctuation) {
		this.punctuation = punctuation;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}	
}
