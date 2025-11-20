package com.TPB.team_portal_backend.controller;

import com.TPB.team_portal_backend.dto.IdeaRequestDTO;
import com.TPB.team_portal_backend.dto.IdeaResponseDTO;
import com.TPB.team_portal_backend.mapper.IdeaMapper;
import com.TPB.team_portal_backend.model.Idea;
import com.TPB.team_portal_backend.service.IdeaService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ideas")
@CrossOrigin(origins = "http://localhost:4200")
public class AllIdeasController {

    private final IdeaService service;

    public AllIdeasController(IdeaService service) {
        this.service = service;
    }

    // Get all ideas
    @GetMapping("/getAll")
    public List<IdeaResponseDTO> getAllIdeas() {
        return service.getAllItems().stream()
                .map(IdeaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Get paginated ideas
    @GetMapping("/paged")
    public Page<IdeaResponseDTO> getPagedIdeas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Page<Idea> pageResults = service.getItemsPaged(page, size, sortBy, direction);
        return pageResults.map(IdeaMapper::toResponseDTO);
    }

    // Update idea
    @PutMapping("/update/{id}")
    public IdeaResponseDTO updateIdea(@PathVariable Long id, @RequestBody @Valid IdeaRequestDTO updatedIdea) {
        Idea updated = service.updateItem(id, IdeaMapper.toEntity(updatedIdea));
        return IdeaMapper.toResponseDTO(updated);
    }

    // Delete idea
    @DeleteMapping("/delete/{id}")
    public void deleteIdea(@PathVariable Long id) {
        service.deleteItem(id);
    }

    // Like idea
    @PutMapping("/{id}/like")
    public IdeaResponseDTO likeIdea(@PathVariable Long id) {
        Idea updated = service.incrementLike(id);
        return IdeaMapper.toResponseDTO(updated);
    }

    // Export to Excel
    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> exportIdeas() throws IOException {
        return service.exportIdeasToExcel();
    }
}
