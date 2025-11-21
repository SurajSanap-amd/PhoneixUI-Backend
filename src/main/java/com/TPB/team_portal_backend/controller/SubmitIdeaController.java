package com.TPB.team_portal_backend.controller;

import com.TPB.team_portal_backend.dto.IdeaRequestDTO;
import com.TPB.team_portal_backend.dto.IdeaResponseDTO;
import com.TPB.team_portal_backend.mapper.IdeaMapper;
import com.TPB.team_portal_backend.model.Idea;
import com.TPB.team_portal_backend.service.IdeaService;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/submit")
@CrossOrigin(origins = {
    "http://localhost:4200",
    "https://phoneixui-amdocs.netlify.app"
})public class SubmitIdeaController {

    private final IdeaService service;

    public SubmitIdeaController(IdeaService service) {
        this.service = service;
    }

    @PostMapping("/idea")
    public IdeaResponseDTO submitIdea(@RequestBody @Valid IdeaRequestDTO requestDTO) {
        Idea createdIdea = service.saveItem(IdeaMapper.toEntity(requestDTO));
        return IdeaMapper.toResponseDTO(createdIdea);
    }
}
