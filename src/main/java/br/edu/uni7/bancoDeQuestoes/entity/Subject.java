package br.edu.uni7.bancoDeQuestoes.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Subject {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String topic;
	
	public Subject() {}
	
	public Subject(Long id, String name, String topic) {
		this.id = id;
		this.name = name;
		this.topic = topic;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", topic=" + topic + "]";
	}
}
