package hr.algebra.keywordsuggestionsapi.service;

import hr.algebra.keywordsuggestionsapi.dto.IdeaDto;
import hr.algebra.keywordsuggestionsapi.dto.QuestionDto;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    List<QuestionDto> findAll();
    Optional<QuestionDto> findById(Integer id);
    void save(QuestionDto dto);
    void deleteById(Integer id);
    void update(Integer id, QuestionDto dto);
}
