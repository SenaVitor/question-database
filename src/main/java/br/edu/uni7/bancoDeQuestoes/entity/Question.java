package br.edu.uni7.bancoDeQuestoes.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Question {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private Statement statement; //Enunciado
	@ManyToOne
	private Template template; //Resposta
	@ManyToOne
	private Subject subject; //Assunto/disciplina
	
	public Question() {}
	
	public Question(Statement statement, Template template, Subject subject) {
		this.statement = statement;
		this.template = template;
		this.subject = subject;
	}

	public Question(Long id, String title, String enunciado, String enunciadoImage, 
			String gabarito, String gabaritoImage, String name, String topic) {
			this.id = id;
			this.statement = new Statement(null, title, enunciado, enunciadoImage);
			this.template = new Template(null, gabarito, gabaritoImage);
			this.subject = new Subject(null, name, topic);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", statement=" + statement + ", template=" + template + ", subject=" + subject
				+ "]";
	}
	
}
