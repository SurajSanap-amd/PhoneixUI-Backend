package com.TPB.team_portal_backend.service;

import com.TPB.team_portal_backend.model.Idea;
import com.TPB.team_portal_backend.repository.IdeaRepository;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaderboardService {

    private final IdeaRepository repository;

    public LeaderboardService(IdeaRepository repository) {
        this.repository = repository;
    }

    public List<Idea> getTopIdeas(int limit) {
        return repository.findAll().stream()
                .sorted(Comparator.comparingInt(i -> -1 * (i.getLikes() == null ? 0 : i.getLikes())))
                .limit(limit)
                .collect(Collectors.toList());
    }
}
