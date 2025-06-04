package hr.algebra.keywordsuggestionsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class KeywordSuggestionsDto {
    private Integer id;
    private List<IdeaDto> ideas;
    private List<QuestionDto> questions;
}
