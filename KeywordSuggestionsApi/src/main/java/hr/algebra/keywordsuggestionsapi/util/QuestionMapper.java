package hr.algebra.keywordsuggestionsapi.util;

import hr.algebra.keywordsuggestionsapi.dto.QuestionDto;
import hr.algebra.keywordsuggestionsapi.model.Idea;
import hr.algebra.keywordsuggestionsapi.model.KeywordSuggestions;
import hr.algebra.keywordsuggestionsapi.model.Question;

public class QuestionMapper {

    private QuestionMapper() {
        throw new UnsupportedOperationException("QuestionMapper is a utility class.");
    }

    public static QuestionDto toDto(Question question) {
        return new QuestionDto(
                question.getId(),
                question.getKeyword(),
                question.getDifficulty(),
                question.getVolume(),
                question.getLastUpdated(),
                question.getKeywordSuggestions() != null ? question.getKeywordSuggestions().getId() : null
        );
    }

    public static Question toEntity(QuestionDto dto, KeywordSuggestions parent) {
        return Question.builder()
                .id(dto.getId())
                .keyword(dto.getKeyword())
                .difficulty(dto.getDifficulty())
                .volume(dto.getVolume())
                .lastUpdated(dto.getLastUpdated())
                .keywordSuggestions(parent)
                .build();
    }

    public static Question toEntity(QuestionDto dto) {
        KeywordSuggestions parent = new KeywordSuggestions();
        parent.setId(dto.getKeywordSuggestionsId());

        return Question.builder()
                .id(dto.getId())
                .keyword(dto.getKeyword())
                .difficulty(dto.getDifficulty())
                .volume(dto.getVolume())
                .lastUpdated(dto.getLastUpdated())
                .keywordSuggestions(parent)
                .build();
    }
}
