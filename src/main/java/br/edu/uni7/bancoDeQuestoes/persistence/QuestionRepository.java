package br.edu.uni7.bancoDeQuestoes.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.uni7.bancoDeQuestoes.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

}
