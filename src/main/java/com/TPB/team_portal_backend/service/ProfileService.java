package com.TPB.team_portal_backend.service;

import com.TPB.team_portal_backend.model.Idea;
import com.TPB.team_portal_backend.repository.IdeaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProfileService {

    private final IdeaRepository repository;

    public ProfileService(IdeaRepository repository) {
        this.repository = repository;
    }

    public List<Idea> getUserIdeas(String username) {
        // In future: repository.findByCreatedBy(username);
        return repository.findAll();
    }
}
