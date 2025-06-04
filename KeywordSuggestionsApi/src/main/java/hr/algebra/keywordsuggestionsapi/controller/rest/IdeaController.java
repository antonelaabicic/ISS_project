package hr.algebra.keywordsuggestionsapi.controller.rest;

import hr.algebra.keywordsuggestionsapi.dto.IdeaDto;
import hr.algebra.keywordsuggestionsapi.service.IdeaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ideas")
@RequiredArgsConstructor
public class IdeaController {

    private final IdeaService ideaService;

    @GetMapping
    public ResponseEntity<List<IdeaDto>> getAll() {
        return ResponseEntity.ok(ideaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IdeaDto> getById(@PathVariable Integer id) {
        return ideaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody IdeaDto dto) {
        ideaService.save(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody IdeaDto dto) {
        ideaService.update(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        ideaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
