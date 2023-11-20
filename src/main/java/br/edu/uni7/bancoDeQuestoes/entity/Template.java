package br.edu.uni7.bancoDeQuestoes.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Template {
	@Id
	@GeneratedValue
	private Long id;
	private String text;
	private String imageUrl;
	
	public Template() {}
	
	public Template(Long id, String text, String imageUrl) {
		this.id = id;
		this.text = text;
		this.imageUrl = imageUrl;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
		return "Template [id=" + id + ", text=" + text + ", imageUrl=" + imageUrl + "]";
	}
}
