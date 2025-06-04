package hr.algebra.keywordsuggestionsapi.repository;

import hr.algebra.keywordsuggestionsapi.model.KeywordSuggestions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordSuggestionsRepository extends JpaRepository<KeywordSuggestions, Integer> {
}
