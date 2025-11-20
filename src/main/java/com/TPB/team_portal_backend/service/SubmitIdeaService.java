package com.TPB.team_portal_backend.service;

import com.TPB.team_portal_backend.model.Idea;
import com.TPB.team_portal_backend.repository.IdeaRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class SubmitIdeaService {

    private final IdeaRepository repository;

    public SubmitIdeaService(IdeaRepository repository) {
        this.repository = repository;
    }

    public Idea submitIdea(Idea idea) {
        idea.setStartTime(LocalDateTime.now());
        return repository.save(idea);
    }
}
