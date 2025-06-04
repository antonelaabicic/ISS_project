package hr.algebra.keywordsuggestionsapi.controller.rest;

import hr.algebra.keywordsuggestionsapi.dto.KeywordSuggestionsDto;
import hr.algebra.keywordsuggestionsapi.service.KeywordSuggestionsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/keyword-suggestions")
@AllArgsConstructor
public class KeywordSuggestionsController {

    private final KeywordSuggestionsService keywordSuggestionsService;

    @GetMapping
    public ResponseEntity<List<KeywordSuggestionsDto>> getAll() {
        List<KeywordSuggestionsDto> suggestions = keywordSuggestionsService.findAll();
        return ResponseEntity.ok(suggestions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KeywordSuggestionsDto> getById(@PathVariable Integer id) {
        return keywordSuggestionsService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody KeywordSuggestionsDto dto) {
        keywordSuggestionsService.save(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        keywordSuggestionsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
