package br.edu.uni7.bancoDeQuestoes.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Statement {
	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private String text;
	private String imageUrl;
	
	public Statement() {}
	
	public Statement(Long id, String title, String text, String imageUrl) {
		this.id = id;
		this.title = title;
		this.text = text;
		this.imageUrl = imageUrl;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "Statement [id=" + id + ", title=" + title + ", text=" + text + ", imageUrl=" + imageUrl + "]";
	}
}
