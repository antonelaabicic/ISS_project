package hr.algebra.keywordsuggestionsapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "questions")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String keyword;
    private String difficulty;
    private String volume;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @ManyToOne
    @JoinColumn(name = "keyword_suggestions_id")
    private KeywordSuggestions keywordSuggestions;
}

