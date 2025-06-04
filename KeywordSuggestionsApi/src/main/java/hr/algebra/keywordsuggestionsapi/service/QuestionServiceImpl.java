package hr.algebra.keywordsuggestionsapi.service;

import hr.algebra.keywordsuggestionsapi.dto.QuestionDto;
import hr.algebra.keywordsuggestionsapi.model.KeywordSuggestions;
import hr.algebra.keywordsuggestionsapi.model.Question;
import hr.algebra.keywordsuggestionsapi.repository.KeywordSuggestionsRepository;
import hr.algebra.keywordsuggestionsapi.repository.QuestionRepository;
import hr.algebra.keywordsuggestionsapi.util.KeywordSuggestionsMapper;
import hr.algebra.keywordsuggestionsapi.util.QuestionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public List<QuestionDto> findAll() {
        return questionRepository.findAll().stream()
                .map(QuestionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<QuestionDto> findById(Integer id) {
        return questionRepository.findById(id)
                .map(QuestionMapper::toDto);
    }

    @Override
    public void save(QuestionDto dto) {
        questionRepository.save(QuestionMapper.toEntity(dto));
    }

    @Override
    public void deleteById(Integer id) {
        questionRepository.deleteById(id);
    }

    @Override
    public void update(Integer id, QuestionDto dto) {
        Question existing = questionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Question not found."));

        existing.setKeyword(dto.getKeyword());
        existing.setDifficulty(dto.getDifficulty());
        existing.setVolume(dto.getVolume());
        existing.setLastUpdated(dto.getLastUpdated());

        questionRepository.save(existing);
    }
}
