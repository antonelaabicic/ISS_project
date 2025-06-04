package hr.algebra.keywordsuggestionsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestionDto {
    private Integer id;
    private String keyword;
    private String difficulty;
    private String volume;
    private LocalDateTime lastUpdated;

    private Integer keywordSuggestionsId;
}
