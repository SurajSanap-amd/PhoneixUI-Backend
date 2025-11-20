package com.TPB.team_portal_backend.service;

import com.TPB.team_portal_backend.model.Idea;
import com.TPB.team_portal_backend.repository.IdeaRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class DashboardService {

    private final IdeaRepository repository;

    public DashboardService(IdeaRepository repository) {
        this.repository = repository;
    }

    public Map<String, Object> getDashboardStats() {
        List<Idea> ideas = repository.findAll();
        int totalIdeas = ideas.size();
        int totalLikes = ideas.stream()
                .mapToInt(i -> i.getLikes() == null ? 0 : i.getLikes())
                .sum();

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalIdeas", totalIdeas);
        stats.put("totalLikes", totalLikes);
        return stats;
    }
}
