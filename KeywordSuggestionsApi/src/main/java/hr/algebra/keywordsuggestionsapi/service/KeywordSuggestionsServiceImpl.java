package hr.algebra.keywordsuggestionsapi.service;

import hr.algebra.keywordsuggestionsapi.dto.KeywordSuggestionsDto;
import hr.algebra.keywordsuggestionsapi.model.KeywordSuggestions;
import hr.algebra.keywordsuggestionsapi.repository.KeywordSuggestionsRepository;
import hr.algebra.keywordsuggestionsapi.util.KeywordSuggestionsMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class KeywordSuggestionsServiceImpl implements KeywordSuggestionsService {

    private final KeywordSuggestionsRepository keywordSuggestionsRepository;

    @Override
    public List<KeywordSuggestionsDto> findAll() {
        return keywordSuggestionsRepository.findAll().stream()
                .map(KeywordSuggestionsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<KeywordSuggestionsDto> findById(Integer id) {
        return keywordSuggestionsRepository.findById(id)
                .map(KeywordSuggestionsMapper::toDto);
    }

    @Override
    public void save(KeywordSuggestionsDto dto) {
        KeywordSuggestions entity = KeywordSuggestionsMapper.toEntity(dto);

        entity.getIdeas().forEach(i -> i.setKeywordSuggestions(entity));
        entity.getQuestions().forEach(q -> q.setKeywordSuggestions(entity));

        keywordSuggestionsRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        keywordSuggestionsRepository.deleteById(id);
    }
}
