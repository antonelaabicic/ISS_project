package hr.algebra.keywordsuggestionsapi.service;

import hr.algebra.keywordsuggestionsapi.dto.IdeaDto;
import hr.algebra.keywordsuggestionsapi.model.Idea;
import hr.algebra.keywordsuggestionsapi.model.Question;
import hr.algebra.keywordsuggestionsapi.repository.IdeaRepository;
import hr.algebra.keywordsuggestionsapi.util.IdeaMapper;
import hr.algebra.keywordsuggestionsapi.util.KeywordSuggestionsMapper;
import hr.algebra.keywordsuggestionsapi.util.QuestionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class IdeaServiceImpl implements IdeaService {

    private final IdeaRepository ideaRepository;

    @Override
    public List<IdeaDto> findAll() {
        return ideaRepository.findAll().stream()
                .map(IdeaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<IdeaDto> findById(Integer id) {
        return ideaRepository.findById(id)
                .map(IdeaMapper::toDto);
    }

    @Override
    public void save(IdeaDto dto) {
        ideaRepository.save(IdeaMapper.toEntity(dto));
    }

    @Override
    public void deleteById(Integer id) {
        ideaRepository.deleteById(id);
    }

    @Override
    public void update(Integer id, IdeaDto dto) {
        Idea existing = ideaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Idea not found."));

        existing.setKeyword(dto.getKeyword());
        existing.setDifficulty(dto.getDifficulty());
        existing.setVolume(dto.getVolume());
        existing.setLastUpdated(dto.getLastUpdated());

        ideaRepository.save(existing);
    }
}
