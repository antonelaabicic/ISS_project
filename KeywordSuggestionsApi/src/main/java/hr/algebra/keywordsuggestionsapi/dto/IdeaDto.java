package hr.algebra.keywordsuggestionsapi.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IdeaDto {
    private Integer id;
    private String keyword;
    private String difficulty;
    private String volume;
    private LocalDateTime lastUpdated;

    private Integer keywordSuggestionsId;
}
