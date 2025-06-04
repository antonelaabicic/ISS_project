package hr.algebra.keywordsuggestionsapi.service;

import hr.algebra.keywordsuggestionsapi.dto.IdeaDto;

import java.util.List;
import java.util.Optional;

public interface IdeaService {
    List<IdeaDto> findAll();
    Optional<IdeaDto> findById(Integer id);
    void save(IdeaDto dto);
    void deleteById(Integer id);
    void update(Integer id, IdeaDto dto);
}
