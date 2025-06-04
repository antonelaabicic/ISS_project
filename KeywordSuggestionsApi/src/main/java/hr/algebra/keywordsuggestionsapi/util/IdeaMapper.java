package hr.algebra.keywordsuggestionsapi.util;

import hr.algebra.keywordsuggestionsapi.dto.IdeaDto;
import hr.algebra.keywordsuggestionsapi.model.Idea;
import hr.algebra.keywordsuggestionsapi.model.KeywordSuggestions;

public class IdeaMapper {

    private IdeaMapper() {
        throw new UnsupportedOperationException("IdeaMapper is a utility class.");
    }

    public static IdeaDto toDto(Idea idea) {
        return new IdeaDto(
                idea.getId(),
                idea.getKeyword(),
                idea.getDifficulty(),
                idea.getVolume(),
                idea.getLastUpdated(),
                idea.getKeywordSuggestions() != null ? idea.getKeywordSuggestions().getId() : null
        );
    }

    public static Idea toEntity(IdeaDto dto, KeywordSuggestions parent) {
        return Idea.builder()
                .id(dto.getId())
                .keyword(dto.getKeyword())
                .difficulty(dto.getDifficulty())
                .volume(dto.getVolume())
                .lastUpdated(dto.getLastUpdated())
                .keywordSuggestions(parent)
                .build();
    }

    public static Idea toEntity(IdeaDto dto) {
        KeywordSuggestions parent = new KeywordSuggestions();
        parent.setId(dto.getKeywordSuggestionsId());

        return Idea.builder()
                .id(dto.getId())
                .keyword(dto.getKeyword())
                .difficulty(dto.getDifficulty())
                .volume(dto.getVolume())
                .lastUpdated(dto.getLastUpdated())
                .keywordSuggestions(parent)
                .build();
    }

}
