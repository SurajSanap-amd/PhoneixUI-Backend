package com.TPB.team_portal_backend.service;

import com.TPB.team_portal_backend.model.Idea;
import com.TPB.team_portal_backend.repository.IdeaRepository;
import com.TPB.team_portal_backend.util.ExcelExporter;

import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.io.*;
import java.util.List;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class AllIdeasService {

    private final IdeaRepository repository;

    public AllIdeasService(IdeaRepository repository) {
        this.repository = repository;
    }

    public List<Idea> getAllIdeas() {
        List<Idea> ideas = repository.findAll();
        ExcelExporter.writeIdeasSnapshotWithBackup(ideas);
        return ideas;
    }

    public Page<Idea> getPagedIdeas(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return repository.findAll(pageable);
    }

    public Idea updateIdea(Long id, Idea updatedIdea) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setName(updatedIdea.getName());
                    existing.setDescription(updatedIdea.getDescription());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Idea not found"));
    }

    public void deleteIdea(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Idea not found");
        }
        repository.deleteById(id);
    }

    public Idea likeIdea(Long id) {
        Idea idea = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Idea not found"));
        idea.setLikes((idea.getLikes() == null ? 0 : idea.getLikes()) + 1);
        return repository.save(idea);
    }

    public ResponseEntity<InputStreamResource> exportIdeas() throws IOException {
        File file = new File("src/main/resources/ideas_data.xlsx");
        if (!file.exists()) throw new IOException("File not found");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ideas_data.xlsx");
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType(
                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(resource);
    }
}
