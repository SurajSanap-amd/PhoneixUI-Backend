package com.TPB.team_portal_backend.controller;

import com.TPB.team_portal_backend.dto.IdeaResponseDTO;
import com.TPB.team_portal_backend.mapper.IdeaMapper;
import com.TPB.team_portal_backend.service.IdeaService;
import org.springframework.web.bind.annotation.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/leaderboard")
@CrossOrigin(origins = {
    "http://localhost:4200",
    "https://phoneixui-amdocs.netlify.app"
})public class LeaderboardController {

    private final IdeaService service;

    public LeaderboardController(IdeaService service) {
        this.service = service;
    }

    @GetMapping
    public List<IdeaResponseDTO> getTopIdeas() {
        return service.getAllItems().stream()
                .sorted(Comparator.comparingInt(i -> -1 * (i.getLikes() == null ? 0 : i.getLikes())))
                .limit(10)
                .map(IdeaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
