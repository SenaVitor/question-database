package br.edu.uni7.bancoDeQuestoes.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.uni7.bancoDeQuestoes.entity.Question;
import br.edu.uni7.bancoDeQuestoes.entity.Statement;
import br.edu.uni7.bancoDeQuestoes.entity.Subject;
import br.edu.uni7.bancoDeQuestoes.entity.Template;
import br.edu.uni7.bancoDeQuestoes.persistence.QuestionRepository;
import br.edu.uni7.bancoDeQuestoes.persistence.StatementRepository;
import br.edu.uni7.bancoDeQuestoes.persistence.SubjectRepository;
import br.edu.uni7.bancoDeQuestoes.persistence.TemplateRepository;

@RestController
public class QuestionController {
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private StatementRepository statementRepository;
	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private TemplateRepository templateRepository;
	
	@GetMapping(path = "/statements")
	public ResponseEntity<List<Statement>> findAllStatements(){
		ResponseEntity<List<Statement>> response = null;
		
		List<Statement> statements = statementRepository.findAll();
		if(statements == null || statements.isEmpty()) {
			response = ResponseEntity.noContent().build();
		}else {
			response = ResponseEntity.ok(statements);
		}
		
		return response;
	}

	@GetMapping(path = "statements/{id}")
	public ResponseEntity<Statement> findStatementById(@PathVariable(name = "id") Long id){
		Statement statement = null;
		ResponseEntity<Statement> response = null;
		
		Optional<Statement> optional = statementRepository.findById(id);
		if(optional.isPresent()) {
			statement = optional.get();
			response = new ResponseEntity<Statement>(statement, HttpStatus.OK);
		}else {
			response = new ResponseEntity<Statement>(statement, HttpStatus.NOT_FOUND);
		}
		
		return response;
	}
	
	@PostMapping(path = "/statements")
	public ResponseEntity<Statement> create(
			@RequestBody(required = true) Statement statement) {
		ResponseEntity<Statement> response = null;
		
		try {
			statement = statementRepository.save(statement);
			response = new ResponseEntity<>(statement, HttpStatus.CREATED);
		} catch (Exception e) {
			response = ResponseEntity.badRequest().build();
		}
		
		return response;
	}
	
	@PutMapping(path = "/statements")
	public ResponseEntity<Statement> updateStatement(@RequestBody Statement statement){
		ResponseEntity<Statement> response = null;
		Statement fromDB = null;
		
		Optional<Statement> optional = statementRepository.findById(statement.getId());
		
		if(optional.isPresent()) {
			fromDB = optional.get();
			
			if(statement.getImageUrl() != null) {
				fromDB.setImageUrl(statement.getImageUrl());
			}
				
			if(statement.getText() != null) {
				fromDB.setText(statement.getText());
			}
			
			if(statement.getTitle() != null) {
				fromDB.setTitle(statement.getTitle());
			}
				
			try {
				statementRepository.save(fromDB);
				response = new ResponseEntity<Statement>(statement, HttpStatus.OK);
			} catch(Exception e) {
				response = ResponseEntity.badRequest().build();
			}
		}else {
			response = new ResponseEntity<Statement>(statement, HttpStatus.NOT_FOUND);
		}			
		
		return response;
	}

	@DeleteMapping(path = "/statements")
	public ResponseEntity<Boolean> removeAllStatements(){
		ResponseEntity<Boolean> response = null;
		
		try {
			statementRepository.deleteAll();
			response = ResponseEntity.ok(true);
		}catch (Exception e) {
			response = ResponseEntity.ok(false);
		}
		
		return response;
	}
	
	@DeleteMapping(path = "statements/{id}")
	public ResponseEntity<Statement> deleteQuestions(@PathVariable(name = "id") Long id){
		Statement statement = null;
		ResponseEntity<Statement> response = null;

		Optional<Statement> optional = statementRepository.findById(id);
		
		try {
			if(optional.isPresent()) {
				statement = optional.get();
				statementRepository.delete(statement);
				response = new ResponseEntity<Statement>(statement, HttpStatus.OK);
			}else {
				response = new ResponseEntity<Statement>(statement, HttpStatus.NOT_FOUND);
			}			
		} catch(Exception e) {
			response = ResponseEntity.badRequest().build();
		}
		
		return response;
	}
	
	@GetMapping(path = "/subjects")
	public ResponseEntity<List<Subject>> findAllSubjects(){
		ResponseEntity<List<Subject>> response = null;
		
		List<Subject> subjects = subjectRepository.findAll();
		if(subjects == null || subjects.isEmpty()) {
			response = ResponseEntity.noContent().build();
		}else {
			response = ResponseEntity.ok(subjects);
		}
		
		return response;
	}

	@GetMapping(path = "subjects/{id}")
	public ResponseEntity<Subject> findSubjectById(@PathVariable(name = "id") Long id){
		Subject subject = null;
		ResponseEntity<Subject> response = null;
		
		Optional<Subject> optional = subjectRepository.findById(id);
		if(optional.isPresent()) {
			subject = optional.get();
			response = new ResponseEntity<Subject>(subject, HttpStatus.OK);
		}else {
			response = new ResponseEntity<Subject>(subject, HttpStatus.NOT_FOUND);
		}
		
		return response;
	}
	
	@PostMapping(path = "/subjects")
	public ResponseEntity<Subject> create(
			@RequestBody(required = true) Subject subject) {
		ResponseEntity<Subject> response = null;
		
		try {
			subject = subjectRepository.save(subject);
			response = new ResponseEntity<>(subject, HttpStatus.CREATED);
		} catch (Exception e) {
			response = ResponseEntity.badRequest().build();
		}
		
		return response;
	}

	@PutMapping(path = "/subjects")
	public ResponseEntity<Subject> updateSubject(@RequestBody Subject subject){
		ResponseEntity<Subject> response = null;
		Subject fromDB = null;
		
		Optional<Subject> optional = subjectRepository.findById(subject.getId());
		
		if(optional.isPresent()) {
			fromDB = optional.get();
			
			if(subject.getName() != null) {
				fromDB.setName(subject.getName());					
			}
				
			try {
				subjectRepository.save(fromDB);
				response = new ResponseEntity<Subject>(subject, HttpStatus.OK);
			} catch(Exception e) {
				response = ResponseEntity.badRequest().build();
			}
		}else {
			response = new ResponseEntity<Subject>(subject, HttpStatus.NOT_FOUND);
		}			
		
		return response;
	}

	@DeleteMapping(path = "/subjects")
	public ResponseEntity<Boolean> removeAllsubjects(){
		ResponseEntity<Boolean> response = null;
		
		try {
			subjectRepository.deleteAll();
			response = ResponseEntity.ok(true);
		}catch (Exception e) {
			response = ResponseEntity.ok(false);
		}
		
		return response;
	}
	
	@DeleteMapping(path = "subjects/{id}")
	public ResponseEntity<Subject> deleteSubject(@PathVariable(name = "id") Long id){
		Subject subject = null;
		ResponseEntity<Subject> response = null;

		Optional<Subject> optional = subjectRepository.findById(id);
		
		try {
			if(optional.isPresent()) {
				subject = optional.get();
				subjectRepository.delete(subject);
				response = new ResponseEntity<Subject>(subject, HttpStatus.OK);
			}else {
				response = new ResponseEntity<Subject>(subject, HttpStatus.NOT_FOUND);
			}			
		} catch(Exception e) {
			response = ResponseEntity.badRequest().build();
		}
		
		return response;
	}
	
	@GetMapping(path = "/templates")
	public ResponseEntity<List<Template>> findAllTemplates(){
		ResponseEntity<List<Template>> response = null;
		
		List<Template> templates = templateRepository.findAll();
		if(templates == null || templates.isEmpty()) {
			response = ResponseEntity.noContent().build();
		}else {
			response = ResponseEntity.ok(templates);
		}
		
		return response;
	}
	
	@GetMapping(path = "templates/{id}")
	public ResponseEntity<Template> findTemplateById(@PathVariable(name = "id") Long id){
		Template template = null;
		ResponseEntity<Template> response = null;
		
		Optional<Template> optional = templateRepository.findById(id);
		if(optional.isPresent()) {
			template = optional.get();
			response = new ResponseEntity<Template>(template, HttpStatus.OK);
		}else {
			response = new ResponseEntity<Template>(template, HttpStatus.NOT_FOUND);
		}
		
		return response;
	}
	
	@PostMapping(path = "/templates")
	public ResponseEntity<Template> createTemplate(
			@RequestBody(required = true) Template template) {
		ResponseEntity<Template> response = null;
		
		try {
			template = templateRepository.save(template);
			response = new ResponseEntity<>(template, HttpStatus.CREATED);
		} catch (Exception e) {
			response = ResponseEntity.badRequest().build();
		}
		
		return response;
	}
	
	@PutMapping(path = "/templates")
	public ResponseEntity<Template> updateTemplate(@RequestBody Template template){
		ResponseEntity<Template> response = null;
		Template fromDB = null;
		
		Optional<Template> optional = templateRepository.findById(template.getId());
		
		if(optional.isPresent()) {
			fromDB = optional.get();
			
			if(fromDB.getImageUrl() != null) {
				fromDB.setImageUrl(template.getImageUrl());
				
				if(template.getText() != null) {
					fromDB.setText(template.getText());
				}
			}
				
			try {
				templateRepository.save(fromDB);
				response = new ResponseEntity<Template>(template, HttpStatus.OK);
			} catch(Exception e) {
				response = ResponseEntity.badRequest().build();
			}
		}else {
			response = new ResponseEntity<Template>(template, HttpStatus.NOT_FOUND);
		}			
		
		return response;
	}

	@DeleteMapping(path = "/templates")
	public ResponseEntity<Boolean> removeAllTemplates(){
		ResponseEntity<Boolean> response = null;
		
		try {
			templateRepository.deleteAll();
			response = ResponseEntity.ok(true);
		}catch (Exception e) {
			response = ResponseEntity.ok(false);
		}
		
		return response;
	}
	
	@DeleteMapping(path = "templates/{id}")
	public ResponseEntity<Template> deleteTemplate(@PathVariable(name = "id") Long id){
		Template template = null;
		ResponseEntity<Template> response = null;

		Optional<Template> optional = templateRepository.findById(id);
		
		try {
			if(optional.isPresent()) {
				template = optional.get();
				templateRepository.delete(template);
				response = new ResponseEntity<Template>(template, HttpStatus.OK);
			}else {
				response = new ResponseEntity<Template>(template, HttpStatus.NOT_FOUND);
			}			
		} catch(Exception e) {
			response = ResponseEntity.badRequest().build();
		}
		
		return response;
	}
	
	@GetMapping(path = "/questions")
	public ResponseEntity<List<Question>> findAllQuestions(){
		ResponseEntity<List<Question>> response = null;
		
		List<Question> questions = questionRepository.findAll();
		if(questions == null || questions.isEmpty()) {
			response = ResponseEntity.noContent().build();
		}else {
			System.out.println(questions.get(0));
			response = ResponseEntity.ok(questions);
		}
		
		return response;
	}

	@GetMapping(path = "questions/{id}")
	public ResponseEntity<Question> findQuestionById(@PathVariable(name = "id") Long id){
		Question question = null;
		ResponseEntity<Question> response = null;
		
		Optional<Question> optional = questionRepository.findById(id);
		if(optional.isPresent()) {
			question = optional.get();
			response = new ResponseEntity<Question>(question, HttpStatus.OK);
		}else {
			response = new ResponseEntity<Question>(question, HttpStatus.NOT_FOUND);
		}
		
		return response;
	}
	
	@PostMapping(path = "/questions")
	public ResponseEntity<Question> createQuestion(
			@RequestBody(required = true) Question question) {
		ResponseEntity<Question> response = null;
		
		try {
			question = questionRepository.save(question);
			response = new ResponseEntity<>(question, HttpStatus.CREATED);
		} catch (Exception e) {
			response = ResponseEntity.badRequest().build();
		}
		
		return response;
	}
	
	@PutMapping(path = "/questions")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
		ResponseEntity<Question> response = null;
		Question fromDB = null;
		
		Optional<Question> optional = questionRepository.findById(question.getId());
		
		if(optional.isPresent()) {
			fromDB = optional.get();
			
			if(fromDB.getStatement() != null) {
				if(question.getStatement().getImageUrl() != null) {
					fromDB.getStatement().setImageUrl(question.getStatement().getImageUrl());
				}
				
				if(question.getStatement().getText() != null) {
					fromDB.getStatement().setText(question.getStatement().getText());
				}
				
				if(question.getStatement().getTitle() != null) {
					fromDB.getStatement().setTitle(question.getStatement().getTitle());
				}
			}
			
			if(fromDB.getSubject() != null) {
				if(question.getSubject().getName() != null) {
					fromDB.getSubject().setName(question.getSubject().getName());					
				}
			}
			
			if(fromDB.getTemplate() != null) {
				if(question.getTemplate().getImageUrl() != null) {
					fromDB.getTemplate().setImageUrl(question.getTemplate().getImageUrl());	
				}
				
				if(question.getTemplate().getText() != null) {
					fromDB.getTemplate().setText(question.getTemplate().getText());	
				}
			}
				
			try {
				questionRepository.save(fromDB);
				response = new ResponseEntity<Question>(question, HttpStatus.OK);
			} catch(Exception e) {
				response = ResponseEntity.badRequest().build();
			}
		}else {
			response = new ResponseEntity<Question>(question, HttpStatus.NOT_FOUND);
		}			
		
		return response;
	}

	@DeleteMapping(path = "/questions")
	public ResponseEntity<Boolean> removeAllQuestions(){
		ResponseEntity<Boolean> response = null;
		
		try {
			questionRepository.deleteAll();
			response = ResponseEntity.ok(true);
		}catch (Exception e) {
			response = ResponseEntity.ok(false);
		}
		
		return response;
	}
	
	@DeleteMapping(path = "questions/{id}")
	public ResponseEntity<Question> deleteQuestion(@PathVariable(name = "id") Long id){
		Question question = null;
		ResponseEntity<Question> response = null;

		Optional<Question> optional = questionRepository.findById(id);
		
		try {
			if(optional.isPresent()) {
				question = optional.get();
				questionRepository.delete(question);
				response = new ResponseEntity<Question>(question, HttpStatus.OK);
			}else {
				response = new ResponseEntity<Question>(question, HttpStatus.NOT_FOUND);
			}			
		} catch(Exception e) {
			response = ResponseEntity.badRequest().build();
		}
		
		return response;
	}
}
