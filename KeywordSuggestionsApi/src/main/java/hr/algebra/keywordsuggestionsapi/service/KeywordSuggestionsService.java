package hr.algebra.keywordsuggestionsapi.service;

import hr.algebra.keywordsuggestionsapi.dto.KeywordSuggestionsDto;

import java.util.List;
import java.util.Optional;

public interface KeywordSuggestionsService {
    List<KeywordSuggestionsDto> findAll();
    Optional<KeywordSuggestionsDto> findById(Integer id);
    void save(KeywordSuggestionsDto dto);
    void deleteById(Integer id);
}
