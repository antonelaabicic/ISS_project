package hr.algebra.keywordsuggestionsapi.repository;

import hr.algebra.keywordsuggestionsapi.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
