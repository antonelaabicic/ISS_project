package hr.algebra.keywordsuggestionsapi.util;

import hr.algebra.keywordsuggestionsapi.dto.*;
import hr.algebra.keywordsuggestionsapi.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class KeywordSuggestionsMapper {

    private KeywordSuggestionsMapper() {
        throw new UnsupportedOperationException("KeywordSuggestionsMapper is a utility class.");
    }

    public static KeywordSuggestionsDto toDto(KeywordSuggestions entity) {
        List<IdeaDto> ideas = entity.getIdeas().stream()
                .map(IdeaMapper::toDto)
                .collect(Collectors.toList());

        List<QuestionDto> questions = entity.getQuestions().stream()
                .map(QuestionMapper::toDto)
                .collect(Collectors.toList());

        return new KeywordSuggestionsDto(entity.getId(), ideas, questions);
    }

    public static KeywordSuggestions toEntity(KeywordSuggestionsDto dto) {
        KeywordSuggestions temp = new KeywordSuggestions();
        temp.setId(dto.getId());

        List<Idea> ideas = dto.getIdeas().stream()
                .map(i -> IdeaMapper.toEntity(i, temp))
                .collect(Collectors.toList());

        List<Question> questions = dto.getQuestions().stream()
                .map(q -> QuestionMapper.toEntity(q, temp))
                .collect(Collectors.toList());

        return KeywordSuggestions.builder()
                .id(dto.getId())
                .ideas(ideas)
                .questions(questions)
                .build();
    }
}
