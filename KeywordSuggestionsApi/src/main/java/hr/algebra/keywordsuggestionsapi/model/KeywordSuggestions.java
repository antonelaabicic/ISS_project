package hr.algebra.keywordsuggestionsapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "keyword_suggestions")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeywordSuggestions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "keywordSuggestions", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Idea> ideas = new ArrayList<>();

    @OneToMany(mappedBy = "keywordSuggestions", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

    public KeywordSuggestions(List<Idea> ideas, List<Question> questions) {
        this.ideas = ideas;
        this.questions = questions;
    }
}
