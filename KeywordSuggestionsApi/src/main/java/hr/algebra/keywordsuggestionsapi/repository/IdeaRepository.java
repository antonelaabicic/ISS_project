package hr.algebra.keywordsuggestionsapi.repository;

import hr.algebra.keywordsuggestionsapi.model.Idea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdeaRepository extends JpaRepository<Idea, Integer> {
}
