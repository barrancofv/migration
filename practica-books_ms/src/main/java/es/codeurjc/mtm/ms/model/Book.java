package es.codeurjc.mtm.ms.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class Book {

	public interface WithId {}
	public interface Basic extends WithId {}
	public interface WithComment {}
	

	private Long id;


	private String title;


	private String summary;


	private String author;


	private String editorial;


	private int publishYear;


	private List<Comment> comments;

	public Book() {
	}

	public Book(String title, String summary, String author, String editorial, int publishYear) {
		this(title, summary, author, editorial, publishYear, new ArrayList<>());
	}
	
	public Book(String title, String summary, String author, String editorial, int publishYear, List<Comment> comments) {
		this.title = title;
		this.summary = summary;
		this.author = author;
		this.editorial = editorial;
		this.publishYear = publishYear;		
		this.comments = comments;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public int getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(int publishYear) {
		this.publishYear = publishYear;
	}

	public void addComment(Comment comment) {
		comments.add(comment);
		comment.setBook(this);
	}
	
	public void removeComment(Comment comment) {
		boolean removed = comments.remove(comment);
		if(!removed) {
			throw new NoSuchElementException();
		}
		comment.setBook(null);
	}

	@Override
	public String toString() {
		return String.format("Book [id=%s, title=%s, summary=%s, author=%s, editorial=%s, publishYear=%d", id, title,
				summary, author, editorial, publishYear);
	}

}
