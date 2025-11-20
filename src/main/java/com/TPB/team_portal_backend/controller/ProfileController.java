package com.TPB.team_portal_backend.controller;

import com.TPB.team_portal_backend.dto.IdeaResponseDTO;
import com.TPB.team_portal_backend.mapper.IdeaMapper;
import com.TPB.team_portal_backend.service.IdeaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = "http://localhost:4200")
public class ProfileController {

    private final IdeaService service;

    public ProfileController(IdeaService service) {
        this.service = service;
    }

    // Later, you can add authentication and filter by user
    @GetMapping("/ideas")
    public List<IdeaResponseDTO> getMyIdeas() {
        return service.getAllItems().stream()
                .map(IdeaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
